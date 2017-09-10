package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;

public class Drive extends Command {

    private Operator operator = Operator.getOperator();
    
    private Drivetrain drivetrain;
    
    public Drive() {
        super("Drive");

        // Acquire control of subsystems
        requires(this.drivetrain = Drivetrain.getDrivetrain());
    }

    protected void initialize() {
    }

    protected void execute() {

        // Get move and rotate input values from the controller
        double moveValue = operator.getRightTriggerRawAxis();
        double rotateValue = operator.getLeftXRawAxis();

        // TODO: Use PID to ramp power

        // Drive robot
        drivetrain.drive(moveValue, rotateValue);

    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        drivetrain.stop();
    }

}