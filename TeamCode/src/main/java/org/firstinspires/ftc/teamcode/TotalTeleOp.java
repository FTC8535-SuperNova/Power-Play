package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TotalTeleOp extends LinearOpMode {
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    private DcMotor linearSlide = null;
    public Servo Claw = null;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Init");
        telemetry.update();

        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        Claw = hardwareMap.get(Servo.class, "Claw");

        waitForStart();

        while (opModeIsActive()) {
            double speed = 0.5;
            double SPEED_INCREMENT = 0.1;

            if(gamepad1.right_bumper == true && gamepad1.y){
                speed = speed+SPEED_INCREMENT;
                sleep(2000); //To avoid the variable speed from increasing more than 0.1 each time the button is pressed

            }
            else if(gamepad1.right_bumper == true && gamepad1.a){
                speed = speed-SPEED_INCREMENT;
                sleep(2000);
            }

            if (gamepad1.a == true){
                bottomLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                topLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                bottomRightMotor.setDirection(DcMotor.Direction.REVERSE);
                topRightMotor.setDirection(DcMotor.Direction.REVERSE);

                bottomLeftMotor.setPower(speed);
                topLeftMotor.setPower(speed);
                bottomRightMotor.setPower(speed);
                topRightMotor.setPower(speed);
            }
            else if (gamepad1.y == true){
                bottomLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                topLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                bottomRightMotor.setDirection(DcMotor.Direction.FORWARD);
                topRightMotor.setDirection(DcMotor.Direction.FORWARD);

                bottomLeftMotor.setPower(speed);
                topLeftMotor.setPower(speed);
                bottomRightMotor.setPower(speed);
                topRightMotor.setPower(speed);
            }
            else if (gamepad1.b == true){
                bottomLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                topLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                bottomRightMotor.setDirection(DcMotor.Direction.FORWARD);
                topRightMotor.setDirection(DcMotor.Direction.REVERSE);

                bottomLeftMotor.setPower(speed);
                topLeftMotor.setPower(speed);
                bottomRightMotor.setPower(speed);
                topRightMotor.setPower(speed);
            }
            else if (gamepad1.x == true){
                bottomLeftMotor.setDirection(DcMotor.Direction.FORWARD);
                topLeftMotor.setDirection(DcMotor.Direction.REVERSE);
                bottomRightMotor.setDirection(DcMotor.Direction.REVERSE);
                topRightMotor.setDirection(DcMotor.Direction.FORWARD);

                bottomLeftMotor.setPower(speed);
                topLeftMotor.setPower(speed);
                bottomRightMotor.setPower(speed);
                topRightMotor.setPower(speed);
            }
            else{
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

//            double y = gamepad1.left_stick_y; // Remember, this is reversed!
//            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
//            double rx = gamepad1.right_stick_x;
//
//            //Denominator is the largest motor power (absolute value) or 1
//            // This ensures all the powers maintain the same ratio, but only when
//            // at least one is out of the range [-1, 1]
//            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
//
//            double frontLeftPower = (y - x - rx) / denominator;
//            double backLeftPower = (y + x - rx) / denominator;
//            double frontRightPower = (y + x + rx) / denominator;
//            double backRightPower = (y - x + rx) / denominator;
//
//            bottomLeftMotor.setPower(frontLeftPower);
//            topLeftMotor.setPower(backLeftPower);
//            bottomRightMotor.setPower(frontRightPower);
//            topRightMotor.setPower(backRightPower);

            double slide = gamepad2.left_stick_y;

            linearSlide.setDirection(DcMotor.Direction.FORWARD);
            linearSlide.setPower(slide);

            if (gamepad2.x) {
                Claw.setPosition(0.30);                // Close Claw, goes inward
            }
            else if (gamepad2.y) {
                Claw.setPosition(0.40);
            }
            else if (gamepad2.b) {
                Claw.setPosition(0.50); // Opens Claw, goes outward
            }

            telemetry.addData("slide", slide);

            telemetry.addData("Close", gamepad2.x);
            telemetry.addData("Mid", gamepad2.y);
            telemetry.addData("Open", gamepad1.b);
            telemetry.update();
        }

    }
}