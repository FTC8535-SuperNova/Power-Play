package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Testforautnomous", group="Linear Opmode")

public class testforthingsinauto extends LinearOpMode {

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

        linearSlide.setPower(-0.5);
        sleep(3000);

        linearSlide.setPower(1);
        sleep(3000);

        linearSlide.setPower(0.5);
        sleep(3000);

        linearSlide.setPower(-1);
        sleep(3000);

    }
}

