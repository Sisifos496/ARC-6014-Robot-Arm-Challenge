// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Arm extends SubsystemBase {

  private static Arm m_Instance;

  private static final double REDUCTION_RATIO = 10.0;
  private static final double TICKS_PER_ROTATION = 2048.0;

  private final TalonFX firstMotor = new TalonFX(1);
  private final TalonFX secondMotor = new TalonFX(2);

  public static final double kP = 1.2;
  public static final double kD = 0.0;
  public static final double kI = 0;

  final PositionVoltage m_request = new PositionVoltage(0).withSlot(0);

  public Arm() {
    TalonFXConfiguration configs = new TalonFXConfiguration();

    configs.Slot0.kP=kP;
    configs.Slot0.kI=kI;
    configs.Slot0.kD=kD;

    firstMotor.getConfigurator().apply(configs);
    secondMotor.getConfigurator().apply(configs);

    firstMotor.setControl(m_request.withVelocity(8).withFeedForward(0.5));
    secondMotor.setControl(m_request.withVelocity(8).withFeedForward(0.5));
  }

  public void setAngle(double angle) {
    double rotation = degreesToRotation(angle);
    firstMotor.setControl(m_request.withPosition(rotation));
    secondMotor.setControl(m_request.withPosition(rotation));
  }

  public double getAngle() {
    double angle = rotationsToDegrees(m_request.Position);
    return angle;
  }

  public double rotationsToDegrees(double rotation) {
    return (rotation / (TICKS_PER_ROTATION * REDUCTION_RATIO)) * 360.0;
  }

  public double degreesToRotation(double angle) {
    return (angle / 360.0) * TICKS_PER_ROTATION * REDUCTION_RATIO;
  }

  public static Arm getInstance() {
    if (m_Instance == null) {
      m_Instance = new Arm();
    }
    return m_Instance;
  }
}