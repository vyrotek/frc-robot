package team498.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

/**
 * This class binds the physical operator controls to robot commands
 */
public class Operator {

	public static Controller controller;

	public static void init() {

		controller = new Controller(Mapping.JOYSTICK_PORT);

		// controller.buttonA.whileHeld(new Example());
		// controller.buttonB.whileHeld(new Example());

	}

	/**
	 * Updates the SmartDashboard with Operator data
	 */
	public void updateDashboard() {

		// Buttons
		SmartDashboard.putBoolean(Dashboard.OperatorButtonA, controller.buttonA.get());
		SmartDashboard.putBoolean(Dashboard.OperatorButtonB, controller.buttonB.get());
		SmartDashboard.putBoolean(Dashboard.OperatorButtonX, controller.buttonX.get());
		SmartDashboard.putBoolean(Dashboard.OperatorButtonY, controller.buttonY.get());
		SmartDashboard.putBoolean(Dashboard.OperatorButtonLeftBumper, controller.buttonLeftBumper.get());
		SmartDashboard.putBoolean(Dashboard.OperatorButtonRightBumper, controller.buttonRightBumper.get());

		// Axes
		SmartDashboard.putNumber(Dashboard.OperatorAxisLeftX, controller.axisLeftX.getRawAxisValue());
		SmartDashboard.putNumber(Dashboard.OperatorAxisLeftY, controller.axisLeftY.getRawAxisValue());
		SmartDashboard.putNumber(Dashboard.OperatorAxisRightX, controller.axisRightX.getRawAxisValue());
		SmartDashboard.putNumber(Dashboard.OperatorAxisRightY, controller.axisRightY.getRawAxisValue());
		SmartDashboard.putNumber(Dashboard.OperatorAxisLeftTrigger, controller.axisLeftTrigger.getRawAxisValue());
		SmartDashboard.putNumber(Dashboard.OperatorAxisRightTrigger, controller.axisRightTrigger.getRawAxisValue());

	}

}
