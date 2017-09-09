package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

public class AutoDriveStraight extends Command {

    private Drivetrain drivetrain;
    private Gyro gyro;
    
    private double power;
    private double targetDistance;

    public AutoDriveStraight(double power, double targetDistance) {
        super("AutoDrive");

        requires(this.drivetrain = Drivetrain.getDrivetrain());
        requires(this.gyro = Gyro.getGyro());
        
        this.power = power;
        this.targetDistance = targetDistance;
    }

    protected void initialize() {
        gyro.resetAngle();
    }

    protected void execute() {

        // Get current angle
        double currentAngle = gyro.getAngle();

        // Calculate how much correction is need to move straight
        double rotateValue = Helpers.range(-currentAngle * 0.03, -1, 1);
        // double rotateValue = Helpers.range(-((currentAngle - 0) / 100), -1, 1);
                        
        // Drive robot
        drivetrain.drive(power, rotateValue);
    }

    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(drivetrain.getDistance()) > Math.abs(targetDistance);
    }

    protected void end() {
        drivetrain.stop();
    }

}