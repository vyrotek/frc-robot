package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

public class AutoRotate extends Command {

    private Drivetrain drivetrain;
    private Gyro gyro;
    
    private double targetAngle;
    private double currentAngle;

    public AutoRotate(double targetAngle) {
        super("AutoRotate");

        requires(this.drivetrain = Drivetrain.getDrivetrain());
        requires(this.gyro = Gyro.getGyro());

        this.targetAngle = targetAngle;
    }

    protected void initialize() {
        gyro.resetAngle();
    }

    protected void execute() {

        // Get current angle
        currentAngle = gyro.getAngle();

        // TODO: Use PID with rotate value

        // Calculate how much to turn
        double rotateValue = Helpers.range(-((currentAngle - targetAngle) / 100), -1, 1);

        // Turn robot
        drivetrain.drive(0, rotateValue);

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
        drivetrain.stop();
    }

}