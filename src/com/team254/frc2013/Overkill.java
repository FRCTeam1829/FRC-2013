package com.team254.frc2013;

import com.team254.lib.control.ControlUpdater;
import com.team254.frc2013.auto.FiveDiscAutoMode;
import com.team254.frc2013.auto.SevenDiscAutoMode;
import com.team254.frc2013.commands.CommandBase;
import com.team254.lib.util.PIDTuner;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Main class of the robot.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Overkill extends IterativeRobot {
  private AutoModeSelector autoModeSelector;
  private boolean lastAutonSelectButton;
  private CommandGroup currentAutoMode;
  private Timer shootTimer = new Timer();

  /**
   * Called when the robot is first started up and should be used for any initialization code.
   */
  public void robotInit() {
    // Initialize all subsystems.
    PIDTuner.getInstance().start();
    CommandBase.init();
    ControlUpdater.getInstance().start();

    // Set up autonomous modes.
    autoModeSelector = new AutoModeSelector();
    autoModeSelector.addAutoCommand("7 Disc", SevenDiscAutoMode.class);
    autoModeSelector.addAutoCommand("5 Disc", FiveDiscAutoMode.class);
  }

  public void disabledInit() {
    System.out.println("Disabled init.. reloading constants...");
    Constants.readConstantsFromFile();
  }

  public void disabledPeriodic() {
    boolean autonSelectButton =
        CommandBase.controlBoard.operatorJoystick.getAutonSelectButtonState();
    if (autonSelectButton && !lastAutonSelectButton) {
      autoModeSelector.increment();
    }
    lastAutonSelectButton = autonSelectButton;
    updateLCD();
  }

  /**
   * Called once at the start of the autonomous period.
   */
  public void autonomousInit() {
    currentAutoMode = autoModeSelector.getCurrentAutoModeNewInstance();
    currentAutoMode.start();
  }

  /**
   * Called periodically during the autonomous period.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();
  }

  /**
   * Called once at the start of the teleoperated period.
   */
  public void teleopInit() {
    // Make sure that the autonomous stops running when teleop begins.
    if (currentAutoMode != null) {
      currentAutoMode.cancel();
    }
  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    if (CommandBase.controlBoard.operatorJoystick.getHang30ButtonState()) {
      shootTimer.start();
      CommandBase.shooter.extend();
    } else if (shootTimer.get() > 0.3) {
      CommandBase.shooter.load();
      shootTimer.reset();
    }

    updateLCD();
  }

  private void updateLCD(){
    String driveEncoders = "LE: " + Math.floor(CommandBase.drive.getLeftEncoderDistance());
    driveEncoders += " RE: " + Math.floor(CommandBase.drive.getRightEncoderDistance());
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    lcd.println(DriverStationLCD.Line.kUser2, 1, driveEncoders + "     ");
    lcd.println(DriverStationLCD.Line.kUser3, 1,
                "Gyro: " + Math.floor(CommandBase.drive.getGyroAngle() * 100) / 100);
    lcd.println(DriverStationLCD.Line.kUser4, 1, "IE: " + CommandBase.intake.getEncoderCount());
    lcd.println(DriverStationLCD.Line.kUser5, 1,
                "FS: " + CommandBase.shooter.getFrontCounter() + " BS: " +
                    CommandBase.shooter.getBackCounter());
    lcd.println(DriverStationLCD.Line.kUser6, 1,
                "PSI: " + Math.floor(CommandBase.pressureTransducer.getPsi()) + "     ");
    lcd.updateLCD();
  }
}
