/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

/**
 * An example command. You can replace me with your own command.
 */
public class DriveStraight extends Command {
    private final double distance;

    public DriveStraight(double distance, DriveTrain driveTrain) {
        // Use requires() here to declare subsystem dependencies
        this.distance = distance;
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.resetEncoders();
        double encoderValue = Robot.driveTrain.getEncoderValue();
        while (distance - encoderValue > 0) {
            encoderValue = Robot.driveTrain.getEncoderValue();
            Robot.driveTrain.tankDrive(0.4, 0.4);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        Robot.driveTrain.tankDrive(0, 0);
        cancel();
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
