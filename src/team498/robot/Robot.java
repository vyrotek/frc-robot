
package team498.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import team498.robot.commands.AutoCrossLine;
import team498.robot.subsystems.Drivetrain;
import team498.robot.subsystems.Vision;

public class Robot extends IterativeRobot {

	// Intialize network table for robot data
	public static NetworkTable networkTable = NetworkTable.getTable("robot");

	// Initialize operator controls
	public static Operator operator = new Operator();

	// Initialize subsystems
	public static Drivetrain drivetrain = new Drivetrain();
	public static Vision vision = new Vision();
	// TODO: Add subsystems

	// Autonomous selections
	SendableChooser<Command> chooser = new SendableChooser<>();
	Command autonomousCommand;

	@Override
	public void robotInit() {
		
		// Start vision processing thread
		vision.startThread();
		
		// Add autonomous choices to SmartDashboard
		addAutonomousChoices();		
		
		// Init SmartDashboard
		updateDashboard();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

		// Find selected autonomous command
		autonomousCommand = chooser.getSelected();

		// Start autonomous command
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// Stop the autonomous command when teleop starts
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		updateDashboard();
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void addAutonomousChoices() {

		// Add available autonomous commands with the SmartDashboard
		chooser.addDefault("None", null);
		chooser.addObject("AutoCrossLine", new AutoCrossLine());

		SmartDashboard.putData(Dashboard.AutonomousChooser, chooser);
	}

	/**
	 * Update the SmartDashboard with data from all robot systems
	 */
	private void updateDashboard() {
		
		operator.updateDashboard();
		drivetrain.updateDashboard();
		vision.updateDashboard();
		
	}
}
