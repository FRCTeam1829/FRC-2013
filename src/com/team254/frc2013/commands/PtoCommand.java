package com.team254.frc2013.commands;

/**
 * Enables or disables PTO in the drive gearboxes.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PtoCommand extends CommandBase{
  public PtoCommand() {
    requires(hanger);
    requires(drive);
  }

  protected void initialize() {
  }

  protected void execute() {
    hanger.setPto(true);
    //motors.set(controlBoard.leftStick.getY());
    motors.set(0);  // Disabled until needed, for safety.
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    hanger.setPto(false);
  }

  protected void interrupted() {
    hanger.setPto(false);
  }
}