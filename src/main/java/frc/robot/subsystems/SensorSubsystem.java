package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SensorSubsystem extends SubsystemBase {
    // Change component values from here anytime
    private final int switchChannel = 0;
    private final int analogChannel = 1;

    DigitalInput limitSwitch = new DigitalInput(switchChannel);
    AnalogInput potmeter = new AnalogInput(analogChannel);
    Encoder motorEncoder;

    // public SensorSubsystem() {}

    public boolean getSwitchValue() {
        return limitSwitch.get();
    }

    public double getAnalogVoltage() {
        return potmeter.getVoltage();
    }
}
