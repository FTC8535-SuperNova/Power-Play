package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TotalTeleOp extends LinearOpMode {
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    private DcMotor linearSlide = null;

    final int REVOLUTION_FOR_NEXT_LEVEL = 10;
    final float BASE_REVOLUTION_EXTRA_HEIGHT = 5;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");

        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Starting at",  "%7",
                linearSlide.getCurrentPosition());
        double speed;

        int level=1;
        int totalRevolutions=0;


        waitForStart();

        while (opModeIsActive()) {
            speed = 0.5;
            double SPEED_INCREMENT = 0.1;

            if (gamepad1.right_bumper == true && gamepad1.y) {
                speed = speed + SPEED_INCREMENT;
                sleep(2000); //To avoid the variable speed from increasing more than 0.1 each time the button is pressed

            } else if (gamepad1.right_bumper == true && gamepad1.a) {
                speed = speed - SPEED_INCREMENT;
                sleep(2000);
            }

//            if (gamepad1.x == true){
//                totalRevolutions = changeLevel(level, 1);
//                linearSlide.setDirection(DcMotor.Direction.REVERSE);
//            }
//            else if(gamepad1.y == true) {
//                totalRevolutions = changeLevel(level, 2);
//                linearSlide.setDirection(DcMotor.Direction.REVERSE);
//            }
//            else if(gamepad1.b == true){
//                totalRevolutions = changeLevel(level, 3);
//                linearSlide.setDirection(DcMotor.Direction.REVERSE);
//            }
//            else if(gamepad1.a == true){
//                totalRevolutions = changeLevel(level, 4);
//                linearSlide.setDirection(DcMotor.Direction.REVERSE);
//            }

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

            if (gamepad2.a == true){

            } else{
                double slide = gamepad2.left_stick_y;

                linearSlide.setDirection(DcMotor.Direction.FORWARD);
                linearSlide.setPower(slide);
            }
            double slide = gamepad2.left_stick_y;


            telemetry.addData("bottomLeftMotor", "Forward/Backwards Power (%.2f)", leftPower);
            telemetry.addData("topLeftMotor", "Forward/Backwards Power (%.2f)", leftPower);
            telemetry.addData("bottomRightMotor", "Forward/Backwards Power (%.2f)", rightPower);
            telemetry.addData("topRightMotor", "Forward/Backwards Power (%.2f)", rightPower);
            telemetry.addData("linearSlide", "Forward/Backwards Power (%.2f)", slide);

            telemetry.addData("mecanumSpeed", speed);

            telemetry.addData("Up", gamepad1.y);
            telemetry.addData("Down", gamepad1.a);
            telemetry.addData("Left", gamepad1.x);
            telemetry.addData("Right", gamepad1.b);
            telemetry.update();

        }
    }
    private int changeLevel(int currentLevel, int newLevel) {
        //level diff is the newlevel minus the original level
        int levelDiff = newLevel - currentLevel;

        int totalRevolutions = 0;

        //calculate how many revolutions ndd in FORWARD direction to goto next level
        if (levelDiff > 0) {
            while (levelDiff > 0) {
                linearSlide.setDirection(DcMotor.Direction.FORWARD);
                totalRevolutions += REVOLUTION_FOR_NEXT_LEVEL;
                levelDiff--;
            }
            if (currentLevel == 1) {
                totalRevolutions += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
        } else if (levelDiff < 0) {
            while (levelDiff < 0) {
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
                totalRevolutions += REVOLUTION_FOR_NEXT_LEVEL;
                levelDiff++;
            }
            if (currentLevel == 2) {
                totalRevolutions += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
        }
        return totalRevolutions;
    }
}