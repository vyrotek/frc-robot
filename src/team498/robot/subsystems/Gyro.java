package team498.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;

public class Gyro extends Subsystem {
    
    private static Gyro gyro = null;
    
    /**
     * Provides singleton access to the gyro subsystem
     * @return Gyro instance
     */
    public static Gyro getGyro() {
        gyro = gyro == null ? new Gyro() : gyro;
        return gyro;
    }
    
    private ADXRS450_Gyro sensor = new ADXRS450_Gyro();

    private Gyro() {
        super("Gyro");
        
        sensor.calibrate();
    }

    @Override
    protected void initDefaultCommand() {
        
    }

    public double getAngle(){
        return gyro.getAngle();
    }
    
    public void resetAngle(){
        sensor.reset();
    }

    /**
     * Updates the SmartDashboard with Gyro data
     */
    public void updateDashboard() {

        SmartDashboard.putNumber(Dashboard.GyroAngle, getAngle());

    }

}
