package team498.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import team498.robot.Dashboard;
import team498.robot.Mapping;
import team498.robot.Pipeline;

public class Vision extends Subsystem {

    // TODO: Consider putting these in SmartDashboard Prefs
    private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 240;
    private static final int CAMERA_FPS = 30;
    private static final int CAMERA_EXPOSURE = 0;
    private static final int CAMERA_BRIGHTNESS = 50;
    private static final PixelFormat CAMERA_PIXEL_FORMAT = PixelFormat.kMJPEG;

    private static Vision vision = null;
    
    /**
     * Provides singleton access to the vision subsystem
     * @return Vision instance
     */
    public static Vision getVision() {
        vision = vision == null ? new Vision() : vision;
        return vision;
    }
    
    private UsbCamera camera;
    private CvSink sink;
    private CvSource output;
    private MjpegServer server;
    private Thread thread;
    private Pipeline pipeline;

    private long currentFrameTime;
    private long prevFrameTime;

    private Vision() {
        super("Vision");

        // Intialize generated pipeline
        pipeline = new Pipeline();

        // Intialize USB camera
        camera = new UsbCamera("UsbCamera", Mapping.CAMERA);
        camera.setVideoMode(CAMERA_PIXEL_FORMAT, CAMERA_WIDTH, CAMERA_HEIGHT, CAMERA_FPS);
        camera.setExposureManual(CAMERA_EXPOSURE);
        camera.setBrightness(CAMERA_BRIGHTNESS);

        // Intialize sink
        sink = new CvSink("Sink");
        sink.setEnabled(true);
        sink.setSource(camera);

        // Intialize output
        output = new CvSource("CameraSource", CAMERA_PIXEL_FORMAT, CAMERA_WIDTH, CAMERA_HEIGHT, CAMERA_FPS);
        server = new MjpegServer("OutputServer", Mapping.MJPEG_SERVER);
        server.setSource(output);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void startThread() {

        if (thread == null) {
            // Create a new thread that will constantly evaluate new camera frames
            thread = new Thread(() -> {

                Mat image = new Mat(CAMERA_HEIGHT, CAMERA_WIDTH, 6);

                while (!Thread.interrupted()) {

                    // Check whether it's time to evaluate a new rame
                    currentFrameTime = sink.grabFrameNoTimeout(image);
                    if (currentFrameTime == 0 || currentFrameTime == prevFrameTime) {
                        continue;
                    }
                    prevFrameTime = currentFrameTime;

                    // Process image using generated pipeline
                    pipeline.process(image);

                    // Get pipeline objects
                    ArrayList<MatOfPoint> mops = pipeline.filterContoursOutput();

                    // Evaluate objects
                    for (MatOfPoint mop : mops) {
                        Rect bRect = Imgproc.boundingRect(mop);
                        Imgproc.rectangle(image, bRect.tl(), bRect.br(), new Scalar(255, 0, 255));
                        // TODO: Evaluate objects
                    }

                    // Send the evaluated image to the output stream
                    output.putFrame(image);

                }
            });
            
            thread.start();
        }
    }

    public String getThreadState() {
        if (thread == null)
            return "NO THREAD";
        else
            return thread.getState().toString();
    }

    public void stopThread() {
        if (thread != null && thread.getState() == Thread.State.RUNNABLE) {
            thread.interrupt();
        }
    }

    /**
     * Updates the SmartDashboard with Vision data
     */
    public void updateDashboard() {

        SmartDashboard.putString(Dashboard.VisionThreadState, getThreadState());

    }
}
