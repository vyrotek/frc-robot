package team498.robot;

/**
 * Config contains global port, sensor, and hardware values
 */
public class Mapping {

    // Drivetrain Motors
    public static final int LEFT_FRONT_MOTOR = 4;
    public static final int LEFT_BACK_MOTOR = 8;
    public static final int RIGHT_FRONT_MOTOR = 6;
    public static final int RIGHT_BACK_MOTOR = 5;

    // Camera
    public static final int CAMERA_PORT = 0;
    public static final int MJPEG_SERVER_PORT = 0;

    // Encoder Ports
    public static final int LEFT_ENCODER_A = 2;
    public static final int LEFT_ENCODER_B = 3;
    public static final int RIGHT_ENCODER_A = 8;
    public static final int RIGHT_ENCODER_B = 9;

    // Subsystem Motors
    public static final int CONVEYOR_MOTOR = 1;
    public static final int SHOOT_MOTOR_A = 2;
    public static final int SHOOT_MOTOR_B = 3;
    public static final int INDEXER_MOTOR = 7;

    // Compressor
    public static final int COMPRESSOR = 0;

    // Pneumatics
    public static final int GEARDOOR_FORWARD = 3;
    public static final int GEARDOOR_REVERSE = 4;
    public static final int INTAKE_FORWARD = 2;
    public static final int INTAKE_REVERSE = 5;
    public static final int GEARSHIFTER_FORWARD = 1;
    public static final int GEARSHIFTER_REVERSE = 6;
    public static final int CLIMBER_FORWARD = 0;
    public static final int CLIMBER_REVERSE = 7;

    // Digital Input Sensors
    public static final int BUMPER_TOUCH = 0;
    public static final int IR_SENSOR = 5;

    // Joystick Controller
    public static final int JOYSTICK_PORT = 0;

    // Joystick Controller Button Numbers
    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_LEFTBUMPER = 5;
    public static final int BUTTON_RIGHTBUMPER = 6;
    public static final int BUTTON_BACK = 7;
    public static final int BUTTON_START = 8;
    public static final int BUTTON_LEFTJOYSTICK = 9;
    public static final int BUTTON_RIGHTJOYSTICK = 10;

    // Joystick Controller Axis
    public static final int AXIS_LEFT_X = 0;
    public static final int AXIS_LEFT_Y = 1;
    public static final int AXIS_RIGHT_X = 4;
    public static final int AXIS_RIGHT_Y = 5;
    public static final int AXIS_LEFT_TRIGGER = 2;
    public static final int AXIS_RIGHT_TRIGGER = 3;

}
