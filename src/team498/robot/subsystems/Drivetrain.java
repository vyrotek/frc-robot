package team498.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team498.robot.Dashboard;
import team498.robot.Mapping;
import team498.robot.commands.Drive;

public class Drivetrain extends Subsystem {

    // Drive
    private RobotDrive drive;

    // Encoders
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private final double wheelDiameter = 0.1016;
    private final double pulsePerRevolution = 256;
    private final double wheelCircumference = wheelDiameter * Math.PI;
    private final double meterPerPulse = wheelCircumference / pulsePerRevolution;

    private double currentMoveValue = 0;
    private double currentRotateValue = 0;

    public Drivetrain() {
        super("Drivetrain");

        // Initialize drive
        drive = new RobotDrive(Mapping.LEFT_FRONT_MOTOR, Mapping.LEFT_BACK_MOTOR, Mapping.RIGHT_FRONT_MOTOR,
                Mapping.RIGHT_BACK_MOTOR);

        // Intialize encoders
        leftEncoder = new Encoder(Mapping.LEFT_ENCODER_A, Mapping.LEFT_ENCODER_B);
        rightEncoder = new Encoder(Mapping.RIGHT_ENCODER_A, Mapping.RIGHT_ENCODER_B);

        // Configure encoders
        leftEncoder.setDistancePerPulse(meterPerPulse);
        rightEncoder.setDistancePerPulse(meterPerPulse);
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

        // Apply motor power based on aracade drive mode
        drive.arcadeDrive(moveValue, rotateValue);
    }

    public void stop() {
        drive.stopMotor();
    }

    public double getLeftRPM() {
        return leftEncoder.getRate() * 60 / (2 * Math.PI * (wheelDiameter / 2));
    }

    public double getRightRPM() {
        return rightEncoder.getRate() * 60 / (2 * Math.PI * (wheelDiameter / 2));
    }

    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
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

        SmartDashboard.putNumber(Dashboard.DrivetrainLeftEncoderRPM, getLeftRPM());
        SmartDashboard.putNumber(Dashboard.DrivetrainRightEncoderRPM, getRightRPM());

        SmartDashboard.putNumber(Dashboard.DrivetrainLeftEncoderDistance, getLeftDistance());
        SmartDashboard.putNumber(Dashboard.DrivetrainRightEncoderDistance, getRightDistance());

    }

}
