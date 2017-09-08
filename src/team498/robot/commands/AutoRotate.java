package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.Robot;

public class AutoRotate extends Command {

    private double targetAngle;
    private double currentAngle;

    public AutoRotate(double targetAngle) {
        super("AutoRotate");

        requires(Robot.drivetrain);
        requires(Robot.gyro);

        this.targetAngle = targetAngle;
    }

    protected void initialize() {
        Robot.gyro.resetAngle();
    }

    protected void execute() {

        // Get current angle
        currentAngle = Robot.gyro.getAngle();

        // TODO: Use PID with turn value

        // Calculate how much to turn
        double rotateValue = Helpers.range(-((currentAngle - targetAngle) / 100), -1, 1);

        // Turn robot
        Robot.drivetrain.drive(0, rotateValue);

    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        // TODO: Calibrate target angle threshold
        return Math.abs(currentAngle - targetAngle) <= 2;
    }

    protected void end() {
        Robot.drivetrain.stop();
    }

}