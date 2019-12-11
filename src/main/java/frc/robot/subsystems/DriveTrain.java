
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.commands.Drive;
import frc.robot.utilities.FunctionCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private CANSparkMax l1SparkMax = new CANSparkMax(CANSparkMaxID.kLeft1.id, MotorType.kBrushless);
  private CANSparkMax l2SparkMax = new CANSparkMax(CANSparkMaxID.kLeft2.id, MotorType.kBrushless);
  private CANSparkMax r1SparkMax = new CANSparkMax(CANSparkMaxID.kRight1.id, MotorType.kBrushless);
  private CANSparkMax r2SparkMax = new CANSparkMax(CANSparkMaxID.kRight2.id, MotorType.kBrushless);

  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(l1SparkMax, l2SparkMax);
  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(r1SparkMax, r2SparkMax);

  private DifferentialDrive drive = new DifferentialDrive(leftMotors, rightMotors);

  // public FunctionCommand toggleDriveTrain = new
  // FunctionCommand(this::toggleDriveTrain);

  // public boolean arcadeDrive = false;

  public DriveTrain() {
    super();
    l1SparkMax.setIdleMode(IdleMode.kBrake);
    l2SparkMax.setIdleMode(IdleMode.kBrake);
    r1SparkMax.setIdleMode(IdleMode.kBrake);
    r2SparkMax.setIdleMode(IdleMode.kBrake);
    drive.setDeadband(0.02);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive(this));
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void arcadeDrive(double y, double r) {
    drive.arcadeDrive(y, r, true);
  }

  // public void tankDrive(double r, double l) {
  // drive.tankDrive(l, r, true);
  // }

  // public void toggleDriveTrain() {
  // if (arcadeDrive) {
  // arcadeDrive = false;
  // } else {
  // arcadeDrive = true;
  // }
  // }
}