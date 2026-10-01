package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PotentioSubsytem extends SubsystemBase {
    private final int analogChannel = 1;

    AnalogInput pot = new AnalogInput(analogChannel);

    public double getVoltage() {
        return pot.getVoltage();
    }

    public void outputVoltage() {
        double voltage = getVoltage();
        SmartDashboard.putNumber("Potentiometer Voltage", voltage);
    }
}
