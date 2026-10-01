package frc.robot.commands;

import java.util.function.Supplier;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.PotentioSubsytem;
import frc.robot.subsystems.SensorSubsystem;

public class ExtendedMotorCommand extends Command {
    MotorSubsystem motorSubsystem;
    TalonFX motor;
    SensorSubsystem sensorSubsystem;
    PotentioSubsytem potentioSubsytem;
    Supplier<Double> motorJoystick;

    public ExtendedMotorCommand(MotorSubsystem motorSubsystem, SensorSubsystem sensorSubsystem, PotentioSubsytem potentioSubsytem, Supplier<Double> motorJoystick) {
        this.motorSubsystem = motorSubsystem;
        this.motor = motorSubsystem.getMotor();
        this.sensorSubsystem = sensorSubsystem;
        this.potentioSubsytem = potentioSubsytem;
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
        new RunCommand(() -> potentioSubsytem.outputVoltage(), potentioSubsytem);

        // Switch logic
        if (!sensorSubsystem.getSwitchValue()) {
            SmartDashboard.putBoolean("Motor running", true);
            // motorSubsystem.motorSetSpeed(motorJoystick.get());
            safeSetSpeed(motorJoystick.get());
        } else {
            // motorSubsystem.motorSetSpeed(0);
            safeSetSpeed(0);
        }

        // Note: For some reason, encoders don't exist in Talon motors???

        // TODO: Implement command groups & button bindings
        /*
         * Bind B button to toggle joystick movement for motor
         * If joystick is off, use buttons (Y/A) to increase/decrease motor speed
         */
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
