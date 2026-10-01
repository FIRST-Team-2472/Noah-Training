package frc.robot.commands;

import java.util.function.Supplier;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SensorSubsystem;

public class ExtendedMotorCommand extends Command {
    MotorSubsystem motorSubsystem;
    TalonFX motor;
    SensorSubsystem sensorSubsystem;
    Supplier<Double> motorJoystick;

    public ExtendedMotorCommand(MotorSubsystem motorSubsystem, SensorSubsystem sensorSubsystem, Supplier<Double> motorJoystick) {
        this.motorSubsystem = motorSubsystem;
        this.motor = motorSubsystem.getMotor();
        this.sensorSubsystem = sensorSubsystem;
        this.motorJoystick = motorJoystick;

        addRequirements(motorSubsystem);
    }

    // experimenting some stuff
    private void safeSetSpeed(double speed) {
        // prevents errors
        if (motor == null) return;

        if (motor.get() != speed) {
            motorSubsystem.motorSetSpeed(speed);
        }
    }

    @Override
    public void initialize() {
        motorSubsystem.motorSetSpeed(0);
    }

    @Override
    public void execute() {
        // Potentiometer output
        double voltage = sensorSubsystem.getAnalogVoltage();
        SmartDashboard.putNumber("Potentiometer Voltage", voltage);

        // Switch logic
        if (!sensorSubsystem.getSwitchValue()) {
            SmartDashboard.putBoolean("Motor running", true);
            motorSubsystem.motorSetSpeed(motorJoystick.get());
            // safeSetSpeed(motorJoystick.get());
        } else {
            motorSubsystem.motorSetSpeed(0);
            // safeSetSpeed(0);
        }

        // Note: For some reason, encoders don't exist in Talon motors???
    }

    @Override
    public void end(boolean interrupted) {
        motorSubsystem.motorSetSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
