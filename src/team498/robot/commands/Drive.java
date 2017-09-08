package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Operator;
import team498.robot.Robot;

public class Drive extends Command {

    public Drive() {
        super("Drive");

        // Acquire control of subsystems
        requires(Robot.drivetrain);
        requires(Robot.gyro);

    }

    protected void initialize() {
    }

    protected void execute() {

        // Get power and turn input values from the controller
        double power = Operator.controller.axisRightTrigger.getRawAxisValue();
        double turn = Operator.controller.axisLeftX.getRawAxisValue();

        // TODO: Use PID to ramp power
        // TODO: Use gyro to adjust turn for any mechanical drift

        // Drive robot
        Robot.drivetrain.drive(power, turn);

    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.drivetrain.stop();
    }

}