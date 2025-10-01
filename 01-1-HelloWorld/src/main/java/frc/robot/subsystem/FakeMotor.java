package frc.robot.subsystem;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FakeMotor extends SubsystemBase {
    /**
     * Different states that the motor can be in.
     * @return
     */
    public enum MotorStatus {
        HIGH(1),
        LOW(0.5),
        OFF(0);

        private final double power;

        private MotorStatus(double power) {
            this.power = power;
        }

        /**
         * Returns the power of the motor
         * @return power
         */
        public double getPower() {
            return power;
        }
    }

    private MotorStatus status;
    private double realMotorOutput = 0;

    /**
     * Creates an instance of the FakeMotor that is off by default.
     */
    public FakeMotor() {
        this(MotorStatus.OFF);
    }

    /**
     * Creates an instance of the FakeMotor where you can define its default status.
     * @param status Default status
     */
    public FakeMotor(MotorStatus status) {
        this.status = status;
        this.setDefaultCommand(this.runMotor());
    }

    /**
     * Returns a command that cycles the motor status.
     * @return
     */
    public Command toggle() {
        return this.runOnce(() -> {
            if (status == MotorStatus.OFF) {
                status = MotorStatus.HIGH;
            } else if (status == MotorStatus.HIGH) {
                status = MotorStatus.LOW;
            } else if (status == MotorStatus.LOW) {
                status = MotorStatus.OFF;
            }
        });
    }

    /**
     * Returns a command that sets the motor output based on the status given.
     * @return
     */
    public Command runMotor() {
        return this.run(() -> {
            realMotorOutput = status.getPower();
        });
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        super.initSendable(builder);

        builder.addDoubleProperty("RealMotorOutput", () -> this.realMotorOutput, null);
    }

    @Override
    public void periodic() {
        System.out.println("Motor status is currently: " + this.status);
    }
}
