package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Operator;
import team498.robot.Robot;

public class Drive extends Command {

    private boolean hasFinished;

    public Drive() {
    	super("DriveCommand");
    	
        // Acquire control of the drivetrain subsystem
        requires(Robot.drivetrain);

        hasFinished = false;
    }

    protected void initialize() {
        hasFinished = false;
    }

    protected void execute() {

        // Retrieve move and turn input values from the controller
        double moveValue = Operator.controller.axisRightTrigger.getRawAxisValue();
        double rotateValue = Operator.controller.axisLeftX.getRawAxisValue();

        // Provide drivetrain with move and rotate values
        Robot.drivetrain.drive(moveValue, rotateValue);
    }

    protected void end() {    	
    	Robot.drivetrain.stop();
        hasFinished = true;
    }

    protected void interrupted() {    	
    	Robot.drivetrain.stop();
        hasFinished = true;
    }

    @Override
    protected boolean isFinished() {
        return hasFinished;
    }

}