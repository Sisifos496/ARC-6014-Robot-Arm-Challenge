// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.Arm;

public class RobotContainer {
  private final Arm m_armSubsystem = new Arm();

  public RobotContainer() {

    /*
      An Example Of How Can This Subsystem Be Used
      
        m_armSubsystem.setAngle(90);
        m_armSubsystem.getAngle();
    */
  }
}
