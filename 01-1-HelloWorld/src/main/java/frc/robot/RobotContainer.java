// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.FakeMotor;

public class RobotContainer {
  private FakeMotor fakeMotorSubsystem;
  private CommandXboxController joystick = new CommandXboxController(0);

  public RobotContainer() {
    fakeMotorSubsystem = new FakeMotor();
    SmartDashboard.putData(fakeMotorSubsystem);

    configureBindings();
  }

  private void configureBindings() {
    joystick.a().onTrue(fakeMotorSubsystem.toggle());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
