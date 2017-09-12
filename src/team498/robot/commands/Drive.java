package team498.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import team498.robot.Helpers;
import team498.robot.Operator;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Gyro;

public class Drive extends Command {

    private static final double CORRECTION_GAIN = 0.03;
    
    private Operator operator = Operator.getOperator();
    
    private Drivetrain drivetrain;
    private Gyro gyro;
    
    public Drive() {
        super("Drive");

        // Acquire control of subsystems
        requires(this.drivetrain = Drivetrain.getDrivetrain());
        requires(this.gyro = Gyro.getGyro());
        
    }

    protected void initialize() {
        gyro.resetAngle();
    }

    protected void execute() {

        // Get move and rotate input values from the controller
        double move = operator.getRightTriggerAxis();
        double rotate = operator.getLeftXAxis();
        
        // Apply drift correction when going straight 
        if(rotate == 0) {
           // Calculate how much correction is need to move straight
            rotate = Helpers.rotateToTarget(gyro.getAngle(), 0, 0, CORRECTION_GAIN);
        }

        // Drive robot
        drivetrain.drive(move, rotate);

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