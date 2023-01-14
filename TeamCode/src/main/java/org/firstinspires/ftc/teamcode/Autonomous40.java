package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Opposite side Garage", group="Linear Opmode")

public class Autonomous40 extends LinearOpMode {

    // Declare OpMode members.
    private DcMotor bottomLeftMotor = null;
    private DcMotor topLeftMotor = null;
    private DcMotor bottomRightMotor = null;
    private DcMotor topRightMotor = null;
    private DcMotor linearSlide = null;
    public Servo Claw = null;
    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        bottomLeftMotor = hardwareMap.get(DcMotor.class, "bottomLeftMotor");
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeftMotor");
        bottomRightMotor = hardwareMap.get(DcMotor.class, "bottomRightMotor");
        topRightMotor = hardwareMap.get(DcMotor.class, "topRightMotor");
        Claw = hardwareMap.get(Servo.class, "Claw");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlide");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        bottomLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        topLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        bottomRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        topRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        // Wait for the game to start (driver presses PLAY)

        waitForStart();

        bottomLeftMotor.setPower(-0.5);   // Robots goes to the garage
        topLeftMotor.setPower(-0.5);
        bottomRightMotor.setPower(0.5);
        topRightMotor.setPower(0.5);
        sleep(1000);

        bottomLeftMotor.setPower(0.25);   // Robots goes to the garage
        topLeftMotor.setPower(0.25);
        bottomRightMotor.setPower(0.25);
        topRightMotor.setPower(0.25);
        sleep(500);

        bottomLeftMotor.setPower(0.5);   // Robots goes to the garage
        topLeftMotor.setPower(0.5);
        bottomRightMotor.setPower(-0.5);
        topRightMotor.setPower(-0.5);
        sleep(1000);

        bottomLeftMotor.setPower(0.25);   // Robots goes to the garage
        topLeftMotor.setPower(0.25);
        bottomRightMotor.setPower(0.25);
        topRightMotor.setPower(0.25);
        sleep(12000);

        bottomLeftMotor.setPower(-0.5);   // Robots goes to the garage
        topLeftMotor.setPower(-0.5);
        bottomRightMotor.setPower(0.5);
        topRightMotor.setPower(0.5);
        sleep(1000);

        bottomLeftMotor.setPower(0);   // Robots goes to the garage
        topLeftMotor.setPower(0);
        bottomRightMotor.setPower(0);
        topRightMotor.setPower(0);
        sleep(1000);

        bottomLeftMotor.setPower(0.25);   // Robots goes to the garage
        topLeftMotor.setPower(0.25);
        bottomRightMotor.setPower(0.25);
        topRightMotor.setPower(0.25);
        sleep(4000);

        linearSlide.setPower(-0.5);    // Linear slide goes up
        sleep(500);

        Claw.setPosition(0.30);     // Claw drops the cone in the garage
        sleep(1000);


    }
}
