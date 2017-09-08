package team498.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;

public class Gyro extends Subsystem {
    
    private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public Gyro() {
        super("Gyro");
        
        gyro.calibrate();
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public double getAngle(){
        return gyro.getAngle();
    }
    
    public void resetAngle(){
        gyro.reset();
    }

    /**
     * Updates the SmartDashboard with Gyro data
     */
    public void updateDashboard() {

        SmartDashboard.putNumber(Dashboard.GyroAngle, getAngle());

    }

}
