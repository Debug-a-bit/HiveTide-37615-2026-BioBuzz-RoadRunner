package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
// Import the extended motor blueprint for velocity tools
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp (name = "Dual Motor RPM Test (No Gamepad)", group = "Testing")
public class InitialTesting extends LinearOpMode {

    // 1. DECLARATION: Create the empty containers for your extended motor clones
    private DcMotorEx testMotor1 = null;
    private DcMotorEx testMotor2 = null;

    // Change this decimal if you know your exact Yellow Jacket ratio's TPR count!
    // (537.7 is the standard placeholder for the popular 435 RPM model)
    private final double TICKS_PER_REV = 537.7;

    @Override
    public void runOpMode() {

        // 2. THE HANDSHAKE: Pair your code variables to your screen configuration names
        testMotor1 = hardwareMap.get(DcMotorEx.class, "test_motor1");
        testMotor2 = hardwareMap.get(DcMotorEx.class, "test_motor2");

        // Clear out old encoder tick counters and boot them into raw power mode
        testMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        testMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        testMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        testMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Status", "Both Handshakes Passed. Ready.");
        telemetry.update();

        // The Red Light: Pause the script right here until you tap PLAY on the screen
        waitForStart();

        // 3. THE HEARTBEAT LOOP (Runs 50+ times a second once you hit PLAY)
        while (opModeIsActive()) {

            // AUTOMATIC ACTION: Energize both motors to a safe 30% speed constant
            testMotor1.setPower(0.30);
            testMotor2.setPower(0.30);

            // 🧮 MOTOR 1 VELOCITY MATH: getVelocity() reads (Ticks / Second)
            double tps1 = testMotor1.getVelocity();
            double rpm1 = (tps1 / TICKS_PER_REV) * 60.0;

            // 🧮 MOTOR 2 VELOCITY MATH
            double tps2 = testMotor2.getVelocity();
            double rpm2 = (tps2 / TICKS_PER_REV) * 60.0;

            // 📊 LIVE DASHBOARD ANALYTICS (Beamed to your Driver Hub screen over Wi-Fi)
            telemetry.addLine("--- MOTOR 1 SPEEDWAY ---");
            telemetry.addData("Ticks per Second (M1)", tps1);
            telemetry.addData("🎯 REAL-TIME RPM 1", rpm1);

            telemetry.addLine("--- MOTOR 2 SPEEDWAY ---");
            telemetry.addData("Ticks per Second (M2)", tps2);
            telemetry.addData("🎯 REAL-TIME RPM 2", rpm2);

            telemetry.update();
        }

        // When you hit the red STOP button, the loop breaks,
        // and FIRST's background manager forcefully cuts all power to zero!
    }
}
