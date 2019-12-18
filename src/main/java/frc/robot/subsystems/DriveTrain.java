
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap.CANSparkMaxID;
import frc.robot.commands.Drive;
import frc.robot.utilities.FunctionCommand;

import com.revrobotics.CANEncoder;
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

  public FunctionCommand toggleDriveTrain = new FunctionCommand(this::toggleDriveTrain);

  private CANEncoder l1Encoder = new CANEncoder(l1SparkMax);
  private CANEncoder l2Encoder = new CANEncoder(l2SparkMax);
  private CANEncoder r1Encoder = new CANEncoder(r1SparkMax);
  private CANEncoder r2Encoder = new CANEncoder(r2SparkMax);

  public boolean arcadeDrive = false;

  public DriveTrain() {
    l1SparkMax.setIdleMode(IdleMode.kBrake);
    l2SparkMax.setIdleMode(IdleMode.kBrake);
    r1SparkMax.setIdleMode(IdleMode.kBrake);
    r2SparkMax.setIdleMode(IdleMode.kBrake);
    l1Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    l2Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    r1Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
    r2Encoder.setPositionConversionFactor((6 * Math.PI) / 7);
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

  public void tankDrive(double r, double l) {
    drive.tankDrive(l, r, true);
  }

  public void toggleDriveTrain() {
    if (arcadeDrive) {
      arcadeDrive = false;
    } else {
      arcadeDrive = true;
    }
  }

  public double getEncoderValue() {
    return l1Encoder.getPosition();
  }

  public void showDistance() {
    SmartDashboard.putNumber("Distance Moved L1", l1Encoder.getPosition());
    SmartDashboard.putNumber("Distance Moved L2", l2Encoder.getPosition());
    SmartDashboard.putNumber("Distance Moved R1", r1Encoder.getPosition());
    SmartDashboard.putNumber("Distance Moved R2", r2Encoder.getPosition());
  }

  public void resetEncoders() {
    l1Encoder.setPosition(0);
    l2Encoder.setPosition(0);
    r1Encoder.setPosition(0);
    r2Encoder.setPosition(0);
  }
}