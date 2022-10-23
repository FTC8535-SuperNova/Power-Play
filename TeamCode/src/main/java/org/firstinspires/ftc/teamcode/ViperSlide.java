package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

        int level=1;
        int totalRevolutions=0;

        //find out which button is being pressed then pass in the



        waitForStart();

        while(opModeIsActive()){

            if (gamepad1.x == true){
                totalRevolutions = changeLevel(level, 1);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.y == true) {
                totalRevolutions = changeLevel(level, 2);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.b == true){
                totalRevolutions = changeLevel(level, 3);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.a == true){
                totalRevolutions = changeLevel(level, 4);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }

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