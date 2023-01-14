package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
public class MecanumDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor motorBottomLeft = hardwareMap.dcMotor.get("motorBottomLeft");
        DcMotor motorTopLeft = hardwareMap.dcMotor.get("motorTopLeft");
        DcMotor motorBottomRight = hardwareMap.dcMotor.get("motorBottomRight");
        DcMotor motorTopRight = hardwareMap.dcMotor.get("motorTopRight");
        Servo Claw = hardwareMap.get(Servo.class, "Claw");

        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        motorBottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorTopRight.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        double position = 0.0;

        while (opModeIsActive()) {
            Claw.setPosition(position);

            if (gamepad1.b) {
                position += 0.1;
                Claw.setPosition(position);

            } else if (gamepad1.a) {
                position -= 0.1;
                Claw.setPosition(position);

                if (isStopRequested()) return;

                while (opModeIsActive()) {
                    double y = gamepad1.left_stick_y; // Remember, this is reversed!
                    double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
                    double rx = gamepad1.right_stick_x;

                    while (opModeIsActive()) {
                        Claw.setPosition(position);

                        if (gamepad1.b) {
                            position += 0.1;
                            Claw.setPosition(position);

                        } else if (gamepad1.a) {
                            position -= 0.1;
                            Claw.setPosition(position);

                            // Denominator is the largest motor power (absolute value) or 1
                            // This ensures all the powers maintain the same ratio, but only when
                            // at least one is out of the range [-1, 1]
                            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

                            double frontLeftPower = (y - x - rx) / denominator;
                            double backLeftPower = (y + x - rx) / denominator;
                            double frontRightPower = (y + x + rx) / denominator;
                            double backRightPower = (y - x + rx) / denominator;

                            motorBottomLeft.setPower(frontLeftPower);
                            motorTopLeft.setPower(backLeftPower);
                            motorBottomRight.setPower(frontRightPower);
                            motorTopRight.setPower(backRightPower);
                        }
                    }
                }
            }
        }
    }
}
