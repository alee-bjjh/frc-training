package frc.robot.subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HelloWorld extends SubsystemBase {
    private String message = "Hello World";

    @Override
    public void periodic() {
        System.out.println message
    }
}
