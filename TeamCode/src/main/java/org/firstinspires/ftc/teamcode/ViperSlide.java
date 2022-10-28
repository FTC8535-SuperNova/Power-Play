package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class ViperSlide extends LinearOpMode {

    // Declare Motors
    private DcMotor linearSlide = null;

    int level=1;
    double INCHES_OF_LINEAR_SLIDE = 38.4;
    double TOTAL_REVOLUTIONS = 8.7;
    double COUNTS_PER_REVOLUTION = 384.5;
    double TOTAL_COUNTS_FOR_SLIDE= TOTAL_REVOLUTIONS/COUNTS_PER_REVOLUTION;
    double COUNTS_PER_INCH = TOTAL_COUNTS_FOR_SLIDE/INCHES_OF_LINEAR_SLIDE;

    double targetCounts;
    final double COUNTS_FOR_NEXT_LEVEL = 10.0 * COUNTS_PER_INCH;
    final double BASE_REVOLUTION_EXTRA_HEIGHT = 3.3 * COUNTS_PER_INCH;

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


        //find out which button is being pressed then pass in the



        waitForStart();

        while(opModeIsActive()){

            if (gamepad1.x == true){
                targetCounts = changeLevel(level, 1);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.y == true) {
                targetCounts = changeLevel(level, 2);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.b == true){
                targetCounts = changeLevel(level, 3);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }
            else if(gamepad1.a == true){
                targetCounts = changeLevel(level, 4);
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
            }

        }
    }

    private int changeLevel(int currentLevel, int newLevel) {
        //level diff is the newlevel minus the original level
        int levelDiff = newLevel - currentLevel;

        int totalCounts = 0;

        //calculate how many revolutions ndd in FORWARD direction to goto next level
        if (levelDiff > 0) {
            while (levelDiff > 0) {
                linearSlide.setDirection(DcMotor.Direction.FORWARD);
                totalCounts += COUNTS_FOR_NEXT_LEVEL;
                levelDiff--;
            }
            if (currentLevel == 1) {
                totalCounts += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
        } else if (levelDiff < 0) {
            while (levelDiff < 0) {
                linearSlide.setDirection(DcMotor.Direction.REVERSE);
                totalCounts += COUNTS_FOR_NEXT_LEVEL;
                levelDiff++;
            }
            if (currentLevel == 2) {
                totalCounts += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
        }
        return totalCounts;
    }
        }