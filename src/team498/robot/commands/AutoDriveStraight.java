package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.Robot;

public class AutoDriveStraight extends Command {

    private double power;
    private double targetDistance;

    public AutoDriveStraight(double power, double targetDistance) {
        super("AutoDrive");

        requires(Robot.drivetrain);
        requires(Robot.gyro);

        this.power = power;
        this.targetDistance = targetDistance;
    }

    protected void initialize() {
        Robot.gyro.resetAngle();
    }

    protected void execute() {

        // Get current angle
        double currentAngle = Robot.gyro.getAngle();

        // Calculate how much correction is need to move straight
        double rotateValue = Helpers.range(-currentAngle * 0.03, -1, 1);
        // double rotateValue = Helpers.range(-((currentAngle - 0) / 100), -1, 1);
                        
        // Drive robot
        Robot.drivetrain.drive(power, rotateValue);
    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getDistance()) > Math.abs(targetDistance);
    }

    protected void end() {
        Robot.drivetrain.stop();
    }

}