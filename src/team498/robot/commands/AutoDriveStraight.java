package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import team498.robot.Helpers;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

public class AutoDriveStraight extends Command {

    private static final double CORRECTION_GAIN = 0.03;
    
    private Drivetrain drivetrain;
    private Gyro gyro;
    
    private double move;
    private double targetDistance;

    public AutoDriveStraight(double move, double targetDistance) {
        super("AutoDrive");

        requires(this.drivetrain = Drivetrain.getDrivetrain());
        requires(this.gyro = Gyro.getGyro());
        
        this.move = move;
        this.targetDistance = targetDistance;
    }

    protected void initialize() {
        gyro.resetAngle();
    }

    protected void execute() {

        // Calculate how much correction is need to move straight
        double rotate = Helpers.rotateToTarget(gyro.getAngle(), 0, 0, CORRECTION_GAIN);
                        
        // Drive robot straight with gyro correction
        drivetrain.drive(move, rotate);
        
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