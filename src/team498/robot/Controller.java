package team498.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class provides convenient access to controller buttons and axes
 */
public class Controller {

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
		buttonA = new JoystickButton(joystick, Mapping.BUTTON_A);
		buttonB = new JoystickButton(joystick, Mapping.BUTTON_B);
		buttonX = new JoystickButton(joystick, Mapping.BUTTON_X);
		buttonY = new JoystickButton(joystick, Mapping.BUTTON_Y);
		buttonLeftBumper = new JoystickButton(joystick, Mapping.BUTTON_LEFTBUMPER);
		buttonRightBumper = new JoystickButton(joystick, Mapping.BUTTON_RIGHTBUMPER);

		// Axes
		axisLeftX = new JoystickAxis(joystick, Mapping.AXIS_LEFT_X, 0.1);
		axisLeftY = new JoystickAxis(joystick, Mapping.AXIS_LEFT_Y, 0.1);
		axisRightX = new JoystickAxis(joystick, Mapping.AXIS_RIGHT_X, 0.1);
		axisRightY = new JoystickAxis(joystick, Mapping.AXIS_RIGHT_Y, 0.1);
		axisLeftTrigger = new JoystickAxis(joystick, Mapping.AXIS_LEFT_TRIGGER, 0.1);
		axisRightTrigger = new JoystickAxis(joystick, Mapping.AXIS_RIGHT_TRIGGER, 0.1);
	}

	public void setRumble(double value) {
		// Let's get ready to ruuuummmmbllle
		joystick.setRumble(RumbleType.kLeftRumble, value);
		joystick.setRumble(RumbleType.kRightRumble, value);
	}

	public class JoystickAxis {

		private Joystick joystick;
		private int axis;
		private double threshold;

		public JoystickAxis(Joystick joystick, int axis, double threshold) {
			this.axis = axis;
			this.joystick = joystick;
			this.threshold = threshold;
		}

		/**
		 * Get raw axis value. 
		 * @return Returns raw axis value.
		 */
		public double getRawAxisValue() {
			return joystick.getRawAxis(axis);
		}

		/**
		 * Get raw axis value with threshold check.
		 * @return Returns raw axis value when above the specified threshold otherwise returns 0.
		 */
		public double getNormalizedAxisValue() {
			if (Math.abs(joystick.getRawAxis(axis)) < threshold) {
				return 0;
			} else {
				return joystick.getRawAxis(axis);
			}
		}

	}
}
