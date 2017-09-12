package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

public class AutoRotate extends Command {

    private static final double ANGLE_TOLERANCE = 2.0;
    private static final double ROTATION_GAIN = 0.01;
        
    private Drivetrain drivetrain;
    private Gyro gyro;
    
    private double targetAngle;
    private double currentAngle;

    private double rotate;
    
    public AutoRotate(double targetAngle) {
        super("AutoRotate");

        requires(this.drivetrain = Drivetrain.getDrivetrain());
        requires(this.gyro = Gyro.getGyro());

        this.targetAngle = targetAngle;
    }

    protected void initialize() {
        gyro.resetAngle();
    }

    protected void execute() {

        // Get current angle
        currentAngle = gyro.getAngle();

        // Calculate how much to turn - TODO: Calibrate tolerance and gain
        rotate = Helpers.rotateToTarget(currentAngle, targetAngle, ANGLE_TOLERANCE, ROTATION_GAIN);
        
        // Turn robot
        drivetrain.drive(0, rotate);

    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return rotate == 0;
    }

    protected void end() {
        drivetrain.stop();
    }

}