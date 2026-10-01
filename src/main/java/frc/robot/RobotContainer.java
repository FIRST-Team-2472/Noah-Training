// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ExtendedMotorCommand;
import frc.robot.commands.MotorCommand;
import frc.robot.subsystems.MotorSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    MotorSubsystem motorSubsystem = new MotorSubsystem();
    SensorSubsystem sensorSubsystem = new SensorSubsystem();
    CommandXboxController controller = new CommandXboxController(0);
    
    Command motorCmd = new MotorCommand(motorSubsystem, () -> controller.getLeftY());
    Command extendedMotorCmd = new ExtendedMotorCommand(motorSubsystem, sensorSubsystem, () -> controller.getLeftY());

    /*
     * 1 - MotorCommand
     * 2 - ExtendedMotorCommand
     */
    private int mode = 3;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        // Configure the trigger bindings
        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        Command currentCmd = switch (mode) {
            case 1 -> motorCmd;
            case 2 -> extendedMotorCmd;
            default -> null;
        };
        if (currentCmd != null) motorSubsystem.setDefaultCommand(currentCmd);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
