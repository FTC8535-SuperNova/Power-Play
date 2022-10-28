package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Clawstuff extends LinearOpMode {
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    public Servo claw = null;

    void pwmEnable() {
    }

    @Override
    public void runOpMode() throws InterruptedException {
        double MIN_POSITION = 0.0;
        double MAX_POSITION = 1.0;

        telemetry.addData("Status", "Init");
        telemetry.update();

        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        claw = hardwareMap.get(Servo.class, "claw");

        waitForStart();

        double position = 0.0;

        while (opModeIsActive()) {
            claw.setPosition(position);

            if (gamepad1.b) {
                position += 0.1;
                claw.setPosition(position);

            } else if (gamepad1.a) {
                position -= 0.1;
                claw.setPosition(position);

                telemetry.addData("Up", gamepad1.dpad_up);
                telemetry.addData("Down", gamepad1.dpad_down);
                telemetry.addData("Left", gamepad1.dpad_left);
                telemetry.addData("Right", gamepad1.dpad_right);
                telemetry.addData("Close", gamepad1.a);
                telemetry.addData("Open", gamepad1.b);
                telemetry.update();
            }

        }
    }
}