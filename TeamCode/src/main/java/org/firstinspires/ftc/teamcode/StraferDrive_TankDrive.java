package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


// This is code for a strafer drive in the buttons but a tank drive using the joysticks.


@TeleOp
public class StraferDrive_TankDrive extends LinearOpMode {
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    public Servo claw = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        claw = hardwareMap.get(Servo.class, "claw");


        waitForStart();

        while (opModeIsActive()) {
            double speed = 0.5;
            double SPEED_INCREMENT = 0.1;
            double position = 0.0;

            while (opModeIsActive()) {
                claw.setPosition(position);

                if (gamepad1.right_bumper) {
                    position += 0.025;    // Claw stuff i don't remember
                    claw.setPosition(position);

                } else if (gamepad1.left_bumper) {
                    position -= 0.025;
                    claw.setPosition(position);

                    if (gamepad1.right_bumper == true && gamepad1.y) {
                        speed = speed + SPEED_INCREMENT;
                        sleep(2000); //To avoid the variable speed from increasing more than 0.1 each time the button is pressed

                    } else if (gamepad1.right_bumper == true && gamepad1.a) {
                        speed = speed - SPEED_INCREMENT;
                        sleep(2000);
                    }

                    if (gamepad1.a == true) {
                        bottomLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                        topLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                        bottomRightMotor.setDirection(DcMotor.Direction.REVERSE);
                        topRightMotor.setDirection(DcMotor.Direction.REVERSE);

                        bottomLeftMotor.setPower(speed);
                        topLeftMotor.setPower(speed);
                        bottomRightMotor.setPower(speed);
                        topRightMotor.setPower(speed);
                    } else if (gamepad1.y == true) {
                        bottomLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                        topLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                        bottomRightMotor.setDirection(DcMotor.Direction.FORWARD);
                        topRightMotor.setDirection(DcMotor.Direction.FORWARD);

                        bottomLeftMotor.setPower(speed);
                        topLeftMotor.setPower(speed);
                        bottomRightMotor.setPower(speed);
                        topRightMotor.setPower(speed);
                    } else if (gamepad1.b == true) {
                        bottomLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                        topLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                        bottomRightMotor.setDirection(DcMotor.Direction.FORWARD);
                        topRightMotor.setDirection(DcMotor.Direction.REVERSE);

                        bottomLeftMotor.setPower(speed);
                        topLeftMotor.setPower(speed);
                        bottomRightMotor.setPower(speed);
                        topRightMotor.setPower(speed);
                    } else if (gamepad1.x == true) {
                        bottomLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                        topLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                        bottomRightMotor.setDirection(DcMotor.Direction.REVERSE);
                        topRightMotor.setDirection(DcMotor.Direction.FORWARD);

                        bottomLeftMotor.setPower(speed);
                        topLeftMotor.setPower(speed);
                        bottomRightMotor.setPower(speed);
                        topRightMotor.setPower(speed);
                    } else {
                        double leftPower = -gamepad1.left_stick_y;
                        double rightPower = -gamepad1.right_stick_y;

                        bottomLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        topLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
                        bottomRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

                        bottomLeftMotor.setPower(leftPower);
                        topLeftMotor.setPower(leftPower);
                        bottomRightMotor.setPower(rightPower);
                        topRightMotor.setPower(rightPower);
                    }
                    double leftPower = -gamepad1.left_stick_y;
                    double rightPower = -gamepad1.right_stick_y;


                    telemetry.addData("bottomLeftMotor", "Forward/Backwards Power (%.2f)", leftPower);
                    telemetry.addData("topLeftMotor", "Forward/Backwards Power (%.2f)", leftPower);
                    telemetry.addData("bottomRightMotor", "Forward/Backwards Power (%.2f)", rightPower);
                    telemetry.addData("topRightMotor", "Forward/Backwards Power (%.2f)", rightPower);

                    telemetry.addData("mecanumSpeed", speed);

                    telemetry.addData("Up", gamepad1.y);
                    telemetry.addData("Down", gamepad1.a);
                    telemetry.addData("Left", gamepad1.x);
                    telemetry.addData("Right", gamepad1.b);
                    telemetry.addData("Close", gamepad1.right_bumper);
                    telemetry.addData("Open", gamepad1.left_bumper);
                    telemetry.update();
                }
            }
        }
    }
}