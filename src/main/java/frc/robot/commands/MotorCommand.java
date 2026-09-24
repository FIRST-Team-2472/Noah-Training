package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MotorSubsystem;

public class MotorCommand extends Command {
    MotorSubsystem motorSubsystem;
    Supplier<Double> motorJoystick;

    public MotorCommand(MotorSubsystem motorSubsystem, Supplier<Double> motorJoystick) {
        this.motorSubsystem = motorSubsystem;
        this.motorJoystick = motorJoystick;

        addRequirements(motorSubsystem);
    }

    @Override
    public void initialize() {
        motorSubsystem.motorSet(0);
    }

    @Override
    public void execute() {
        motorSubsystem.motorSet(motorJoystick.get());
    }

    @Override
    public void end(boolean interrupted) {
        motorSubsystem.motorSet(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
