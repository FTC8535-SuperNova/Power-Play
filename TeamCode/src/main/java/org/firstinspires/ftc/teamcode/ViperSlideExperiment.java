package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
    public class ViperSlideExperiment extends LinearOpMode {

        // Declare Motors
        private DcMotor linearSlide = null;
        final int REVOLUTION_FOR_NEXT_LEVEL = 10;
        final float BASE_REVOLUTION_EXTRA_HEIGHT = 5;

        //Variables ^

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");
            linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            telemetry.addData("Starting at",  "%7",
                    linearSlide.getCurrentPosition());
            double speed;

            double pulses = 384.5;

            int totalRevolutions=0;

            //find out which button is being pressed



            waitForStart();

            while(opModeIsActive()){
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
                int linearSlideTarget = 0;

                if (gamepad1.a) {
                    linearSlideTarget = linearSlide.getCurrentPosition() + (int)(pulses * 5);
                    linearSlide.setTargetPosition(385);

                    linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    linearSlide.setPower(0.5);
                }
            }
        }
    }
