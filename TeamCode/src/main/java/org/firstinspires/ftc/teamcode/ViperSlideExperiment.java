package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ViperSlideExperiment {

    @TeleOp
    public class ViperSlide extends LinearOpMode {

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

            //find out which button is being pressed then pass in the



            waitForStart();

            while(opModeIsActive()){
                int linearSlideTarget = 0;

                linearSlideTarget = linearSlide.getCurrentPosition() + (int)(pulses);
                linearSlide.setTargetPosition(385);

                linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            }
        }
    }
}
