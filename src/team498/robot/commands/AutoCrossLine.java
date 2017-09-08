package team498.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import team498.robot.commands.AutoDriveStraight;
import team498.robot.commands.AutoRotate;

public class AutoCrossLine extends CommandGroup {

    public AutoCrossLine() {
        super("AutoCrossLine");

        // Add sequential command steps
        addSequential(new AutoDriveStraight(1, 1));
        addSequential(new AutoRotate(90));
    }

}