// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// added imports below 
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

private Spark leftMotor1 = new Spark (1);
private Spark leftMotor2 = new Spark(2);
private Spark rightMotor1 = new Spark(3);
private Spark rightMotor2 = new Spark(4);
private final XboxController driverController = new XboxController(0);
private final Timer timer1 = new Timer();
/**
 *
 */

private double startTime;
private Spark feedWheel = new Spark(6);
private Spark launchWheel = new Spark(5);

double launchPower = 0;
double feedPower = 0;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
   

    
    
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    startTime = Timer.getFPGATimestamp();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
double time =  Timer.getFPGATimestamp();


if (time - startTime<3){
  leftMotor1.set(0.3);
leftMotor2.set(0.3);
  rightMotor1.set(-0.3);
  rightMotor2.set(-0.3);
}else{
       leftMotor1.set(0.6);
leftMotor2.set(0.6);
  rightMotor1.set(-0.6);
  rightMotor2.set(-0.6);
}
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double speed = -driverController.getRawAxis(1)*0.8;
    double turn = driverController.getRawAxis(4)*0.3;
     double left = speed + turn;
     double right = speed - turn;
leftMotor1.set(left);
leftMotor2.set(left);
  rightMotor1.set(-right);
  rightMotor2.set(-right);
	
  //launcher code 
  if(driverController.getLeftBumper()){//intake code
    launchWheel.set(-1);
    feedWheel.set(-.2); 
  }
  else if 
  (driverController.getRightBumper()){
    launchWheel.set(1);
    feedWheel.set(0); 
    if (driverController.getLeftBumper()){
      launchWheel.set(1); 
      feedWheel.set(1);
    }
  }
else {
  launchWheel.set(0);
  feedWheel.set(0);
}
    
  
  

}

/** */
  
  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
