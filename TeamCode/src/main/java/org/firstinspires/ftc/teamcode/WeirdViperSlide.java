package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


// This is code for a strafer drive in the buttons but a tank drive using the joysticks.


@TeleOp
public class WeirdViperSlide extends LinearOpMode {
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    private DcMotor linearSlide = null;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status","Initialized" );
        telemetry.update();

        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");



        waitForStart();

        while(opModeIsActive()){
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
                bottomLeftMotor.setPower(0);
                topLeftMotor.setPower(0);
                bottomRightMotor.setPower(0);
                topRightMotor.setPower(0);

                double slide = gamepad1.left_stick_y;

                linearSlide.setDirection(DcMotor.Direction.FORWARD);
                linearSlide.setPower(slide);
            }
            double slide = gamepad1.left_stick_y;




            telemetry.addData("slide", slide);

            telemetry.addData("Up", gamepad1.y);
            telemetry.addData("Down", gamepad1.a);
            telemetry.addData("Left", gamepad1.x);
            telemetry.addData("Right", gamepad1.b);
            telemetry.update();
        }
    }
}