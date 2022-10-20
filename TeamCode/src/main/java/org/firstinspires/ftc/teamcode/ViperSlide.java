package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ViperSlide extends LinearOpMode {

    // Declare Motors
    private DcMotor linearSlide = null;
    final int REVOLUTION_FOR_NEXT_LEVEL = 10;
    final float BASE_REVOLUTION_EXTRA_HEIGHT = 5;

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
        int ONE_TWO = 10;
        int TWO_THREE = 10;
        int THREE_FOUR = 10;

        int totalRevolutions=0;

        //find out which button is being pressed then pass in the
        if (gamepad1.x == true){
            levelChange1(level, 1, ONE_TWO, TWO_THREE,THREE_FOUR, totalRevolutions);
            totalRevolutions = changeLevel(level, 1);
        }
        else if(gamepad1.y == true) {
            levelChange2(level, 2, ONE_TWO, TWO_THREE,THREE_FOUR, totalRevolutions);
        }
        else if(gamepad1.b == true){
            levelChange3(level, 3,ONE_TWO,TWO_THREE, THREE_FOUR, totalRevolutions);
        }
        else if(gamepad1.a == true){
            levelChange4(level, 4,ONE_TWO,TWO_THREE, THREE_FOUR, totalRevolutions);
        }


        waitForStart();

        while(opModeIsActive()){
            linearSlide.setDirection(DcMotor.Direction.FORWARD);

        }
    }

    private int changeLevel(int currentLevel, int newLevel) {
        //level diff is the newlevel minus the original level
        int levelDiff = newLevel - currentLevel;

        int totalRevolutions = 0;

        //calculate how many revolutions ndd in FORWARD direction to goto next level
        if (levelDiff > 0) {
            while (levelDiff > 0) {
                totalRevolutions += REVOLUTION_FOR_NEXT_LEVEL;
                levelDiff--;
            }
            if (currentLevel == 1) {
                totalRevolutions += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
            linearSlide.setDirection(DcMotor.Direction.FORWARD);
        } else if (levelDiff < 0) {
            while (levelDiff < 0) {
                totalRevolutions += REVOLUTION_FOR_NEXT_LEVEL;
                levelDiff++;
            }
            linearSlide.setDirection(DcMotor.Direction.REVERSE);
            if (currentLevel == 2) {
                totalRevolutions += BASE_REVOLUTION_EXTRA_HEIGHT;
            }
        }
        return totalRevolutions;
    }

    public void levelChange1(int level, int newLevel, int ONE_TWO, int TWO_THREE, int THREE_FOUR, int countSum){
        //level diff is the newlevel minus the original level
        // so it can go in a seperate if statment to go down
        int levelDiff = newLevel-level;

        if (levelDiff > 0 || levelDiff != 0){
            //if leveldiff is more than 0 it has to go up so its going ot add One_Two regardless
            countSum+=ONE_TWO;
            if (levelDiff==2){
                //if the difference is 2 add another level to the count
                countSum+=TWO_THREE;
            }else if (levelDiff==3) {
                //same thing
                countSum += TWO_THREE;
                countSum += THREE_FOUR;
            }
        }

    }
    public void levelChange2(int level, int newLevel, int ONE_TWO, int TWO_THREE, int THREE_FOUR, int countSum){
        int levelDiff = newLevel-level;

        if (levelDiff > 0 || levelDiff != 0){
            if (level==2) {
                //the level can only go up by one or two levels
                if (levelDiff == 2) {
                    countSum += THREE_FOUR;
                }
                countSum += TWO_THREE;
            }
            //if statment to move down
            // it can only move down to level one so:
        }else if(levelDiff < 0){
            countSum-=ONE_TWO;
        }

    }
    public void levelChange3(int level, int newLevel, int ONE_TWO, int TWO_THREE, int THREE_FOUR, int countSum){
        int levelDiff = newLevel-level;

        if (levelDiff > 0 || levelDiff != 0){
            countSum += THREE_FOUR;
        }else if(levelDiff < 0){
            countSum-=TWO_THREE;
            if(levelDiff==-2){
                countSum-=ONE_TWO;
            }
        }

    }
    public void levelChange4(int level, int newLevel, int ONE_TWO, int TWO_THREE, int THREE_FOUR, int countSum){
        int levelDiff = newLevel-level;

        countSum-=THREE_FOUR;
        if(levelDiff==-2){
            countSum-=TWO_THREE;
        }else if(levelDiff==-3){
            countSum-=ONE_TWO;
        }

    }


}