package team498.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Operator {

    private static Operator operator = null;

    /**
     * Provides singleton access to the operator controls
     * @return Operator instance
     */
    public static Operator getOperator() {
        operator = operator == null ? new Operator() : operator;
        return operator;
    }

    private Controller controller = new Controller(Mappings.JOYSTICK);

    private Operator() {

        // TODO: Map buttons and axes to commands
        // controller.buttonA.whileHeld(new Example());
        // controller.buttonB.whileHeld(new Example());

    }

    /**
     * Get a normalized value for the right trigger axis
     */
    public double getRightTriggerAxis() {
        return operator.controller.axisRightTrigger.getAxisValue();
    }

    /**
     * Get a normalized value for the left joystick X axis
     */
    public double getLeftXAxis() {
        return operator.controller.axisLeftX.getAxisValue();
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
        SmartDashboard.putNumber(Dashboard.OperatorAxisLeftX, controller.axisLeftX.getAxisValue());
        SmartDashboard.putNumber(Dashboard.OperatorAxisLeftY, controller.axisLeftY.getAxisValue());
        SmartDashboard.putNumber(Dashboard.OperatorAxisRightX, controller.axisRightX.getAxisValue());
        SmartDashboard.putNumber(Dashboard.OperatorAxisRightY, controller.axisRightY.getAxisValue());
        SmartDashboard.putNumber(Dashboard.OperatorAxisLeftTrigger, controller.axisLeftTrigger.getAxisValue());
        SmartDashboard.putNumber(Dashboard.OperatorAxisRightTrigger, controller.axisRightTrigger.getAxisValue());

    }

}
