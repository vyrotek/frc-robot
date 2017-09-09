package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mapping;
import team498.robot.commands.Drive;

public class Drivetrain extends Subsystem {

    private static final double WHEEL_DIAMETER = 0.1016;
    private static final double PULSE_PER_REVOLUTION = 256;
    private static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    private static final double METER_PER_PULSE = WHEEL_CIRCUMFERENCE / PULSE_PER_REVOLUTION;

    private static Drivetrain drivetrain = null;
    
    /**
     * Provides singleton access to the drivetrain subsystem
     * @return Drivetrain instance
     */
    public static Drivetrain getDrivetrain() {
        drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
        return drivetrain;
    }
    
    // Drive
    private RobotDrive drive = new RobotDrive(Mapping.LEFT_FRONT_MOTOR, Mapping.LEFT_BACK_MOTOR, Mapping.RIGHT_FRONT_MOTOR, Mapping.RIGHT_BACK_MOTOR);

    // Encoders
    private Encoder leftEncoder = new Encoder(Mapping.LEFT_ENCODER_A, Mapping.LEFT_ENCODER_B);
    private Encoder rightEncoder = new Encoder(Mapping.RIGHT_ENCODER_A, Mapping.RIGHT_ENCODER_B);

    private double currentMoveValue = 0;
    private double currentRotateValue = 0;

    private Drivetrain() {
        super("Drivetrain");

        leftEncoder.setDistancePerPulse(METER_PER_PULSE);
        rightEncoder.setDistancePerPulse(METER_PER_PULSE);

        leftEncoder.reset();
        rightEncoder.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }

    public void drive(double moveValue, double rotateValue) {

        // Temporarily remmber the last values for the dashboard
        currentMoveValue = moveValue;
        currentRotateValue = rotateValue;

        // Apply motor power based on aracade inputs
        drive.arcadeDrive(moveValue, rotateValue);
    }
     
    public void stop() {
        drive.stopMotor();
    }

    public double getLeftRPM() {
        return leftEncoder.getRate() * 60 / (2 * Math.PI * (WHEEL_DIAMETER / 2));
    }

    public double getRightRPM() {
        return rightEncoder.getRate() * 60 / (2 * Math.PI * (WHEEL_DIAMETER / 2));
    }
    
    public double getRPM(){
        return (getLeftRPM() + getRightRPM()) / 2;
    }

    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
    }
    
    public double getDistance() {
        return (getLeftDistance() + getRightDistance()) / 2;
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    /**
     * Updates the SmartDashboard with Drivetrain data
     */
    public void updateDashboard() {

        SmartDashboard.putNumber(Dashboard.DrivetrainMoveValue, currentMoveValue);
        SmartDashboard.putNumber(Dashboard.DrivetrainRotateValue, currentRotateValue);

        SmartDashboard.putNumber(Dashboard.DrivetrainEncoderRPM, getRPM());
        SmartDashboard.putNumber(Dashboard.DrivetrainEncoderDistance, getDistance());

    }
}
