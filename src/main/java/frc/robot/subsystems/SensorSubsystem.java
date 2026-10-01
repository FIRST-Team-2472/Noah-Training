package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsystem extends SubsystemBase {
    private final int switchChannel = 0;

    DigitalInput limitSwitch = new DigitalInput(switchChannel);

    public boolean getSwitchValue() {
        return limitSwitch.get();
    }
}
