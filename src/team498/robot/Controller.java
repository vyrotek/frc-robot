package team498.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class provides convenient access to controller buttons and axes
 */
public class Controller {

    private static final double AXIS_THRESHOLD = 0.1;
    
    private Joystick joystick;

    public JoystickButton buttonA;
    public JoystickButton buttonB;
    public JoystickButton buttonX;
    public JoystickButton buttonY;
    public JoystickButton buttonLeftBumper;
    public JoystickButton buttonRightBumper;

    public JoystickAxis axisLeftX;
    public JoystickAxis axisRightX;
    public JoystickAxis axisLeftY;
    public JoystickAxis axisRightY;
    public JoystickAxis axisLeftTrigger;
    public JoystickAxis axisRightTrigger;

    public Controller(int port) {

        // Controller
        joystick = new Joystick(port);

        // Buttons
        buttonA = new JoystickButton(joystick, Mappings.BUTTON_A);
        buttonB = new JoystickButton(joystick, Mappings.BUTTON_B);
        buttonX = new JoystickButton(joystick, Mappings.BUTTON_X);
        buttonY = new JoystickButton(joystick, Mappings.BUTTON_Y);
        buttonLeftBumper = new JoystickButton(joystick, Mappings.BUTTON_LEFTBUMPER);
        buttonRightBumper = new JoystickButton(joystick, Mappings.BUTTON_RIGHTBUMPER);

        // Axes
        axisLeftX = new JoystickAxis(joystick, Mappings.AXIS_LEFT_X, AXIS_THRESHOLD);
        axisLeftY = new JoystickAxis(joystick, Mappings.AXIS_LEFT_Y, AXIS_THRESHOLD);
        axisRightX = new JoystickAxis(joystick, Mappings.AXIS_RIGHT_X, AXIS_THRESHOLD);
        axisRightY = new JoystickAxis(joystick, Mappings.AXIS_RIGHT_Y, AXIS_THRESHOLD);
        axisLeftTrigger = new JoystickAxis(joystick, Mappings.AXIS_LEFT_TRIGGER, AXIS_THRESHOLD);
        axisRightTrigger = new JoystickAxis(joystick, Mappings.AXIS_RIGHT_TRIGGER, AXIS_THRESHOLD);
    }

    public void setRumble(double value) {
        // Let's get ready to ruuuummmmbllle
        joystick.setRumble(RumbleType.kLeftRumble, value);
        joystick.setRumble(RumbleType.kRightRumble, value);
    }

    public class JoystickAxis {

        private Joystick joystick;
        private int axis;
        private double tolerance;

        public JoystickAxis(Joystick joystick, int axis, double tolerance) {
            this.axis = axis;
            this.joystick = joystick;
            this.tolerance = tolerance;
        }

        /**
         * Get normalized axis value. 
         * @return Returns raw axis value.
         */
        public double getAxisValue() {
            return Helpers.normalize(joystick.getRawAxis(axis), tolerance);
        }

    }
}
