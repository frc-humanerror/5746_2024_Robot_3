// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveAutoCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_Drivetrain;
  private double distanceMeters;
  private double encoderSetpoint;
  private double motorSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveAutoCommand(Drivetrain drivetrain, double distanceMeters, double motorSpeed) {
    m_Drivetrain = drivetrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoderSetpoint = m_Drivetrain.getMetersDriven() + distanceMeters;
    m_Drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Drivetrain.move(motorSpeed, motorSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.move(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_Drivetrain.getMetersDriven() > encoderSetpoint) {
      return true;
    }
    else {
    return false;
    }
  }
}