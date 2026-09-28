package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorSubsystem extends SubsystemBase {
    TalonFX motor = new TalonFX(0);

    public MotorSubsystem() {
        TalonFXConfiguration motorConfig = new TalonFXConfiguration();
        
        CurrentLimitsConfigs limits = new CurrentLimitsConfigs();
        limits.StatorCurrentLimit = 40;

        motorConfig.withCurrentLimits(limits);

        motor.getConfigurator().apply(motorConfig);
    }

    public void motorSetSpeed(double speed) {
        motor.set(speed);
    }
}
