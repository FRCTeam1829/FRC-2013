package com.team254.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains functions for use with the Logitech controller.
 * @author Dorian Chan, Kevin Vincent
 */

public class GameController extends Joystick {
  // Gamepad axis ports
  private static final int kGamepadAxisLeftStickX = 1;
  private static final int kGamepadAxisLeftStickY = 2;
  private static final int kGamepadAxisShoulder = 3;
  private static final int kGamepadAxisRightStickX = 4;
  private static final int kGamepadAxisRightStickY = 5;
  private static final int kGamepadAxisDpad = 6;

  // Gamepad buttons
  private static final int kGamepadButtonA = 1; // Bottom Button
  private static final int kGamepadButtonB = 2; // Right Button
  private static final int kGamepadButtonX = 3; // Left Button
  private static final int kGamepadButtonY = 4; // Top Button
  private static final int kGamepadButtonShoulderL = 5;
  private static final int kGamepadButtonShoulderR = 6;
  private static final int kGamepadButtonBack = 7;
  private static final int kGamepadButtonStart = 8;
  private static final int kGamepadButtonLeftStick = 9;
  private static final int kGamepadButtonRightStick = 10;
  private static final int kGamepadButtonMode = -1;
  private static final int kGamepadButtonLogitech = -1;
  private static final int kDPadXAxisNum = 5;
  private static final int kDPadYAxisNum = 6;
    
    
  /**
   * Constructor that creates a Joystick object.
   */
  public GameController(int gamepadPort) {
    super(gamepadPort);
  }

  /**
   * Returns the X position of the left stick.
   */
  public double getLeftX() {
    return getRawAxis(kGamepadAxisLeftStickX);
  }
   
  /**
   * Returns the X position of the right stick.
   */
  public double getRightX() {
    return getRawAxis(kGamepadAxisRightStickX);
  }

  /**
   * Returns the Y position of the left stick.
   */
  public double getLeftY() {
    return getRawAxis(kGamepadAxisLeftStickY);
  }

  /**
   * Returns the Y position of the right stick.
   */
  public double getRightY(){
    return getRawAxis(kGamepadAxisRightStickY);
  }

  /**
   * Checks whether Button A is being pressed and returns true if it is.
   */
  public boolean getButtonStateA() {
    return getRawButton(kGamepadButtonA);
  }
  
  /**
   * Checks whether Button B is being pressed and returns true if it is.
   */
  public boolean getButtonStateB() {
    return getRawButton(kGamepadButtonB);
  }
  
  /**
   * Checks whether Button X is being pressed and returns true if it is.
	 */
  public boolean getButtonStateX() { 
    return getRawButton(kGamepadButtonX);
  }
  
  /**
   * Checks whether Button Y is being pressed and returns true if it is.
	 */
  public boolean getButtonStateY() {
    return getRawButton(kGamepadButtonY);
  }

    //Buttons
    public boolean getButtonState(String port) {
        if(port.equals("A")) {
            return getRawButton(kGamepadButtonA);
        }
        else if(port.equals("B")) {
            return getRawButton(kGamepadButtonB);
        }
        else if(port.equals("X")) {
            return getRawButton(kGamepadButtonX);
        }
        else if(port.equals("Y")) {
            return getRawButton(kGamepadButtonY);
        }
        else {
            System.out.println("Invalid Button Port!");
            return false;
        }
    }

    //Buttons
    public JoystickButton getButton(String port) {
        if(port.equals("A")) {
            return new JoystickButton(this, kGamepadButtonA);
        }
        else if(port.equals("B")) {
            return new JoystickButton(this, kGamepadButtonB);
        }
        else if(port.equals("X")) {
            return new JoystickButton(this, kGamepadButtonX);
        }
        else if(port.equals("Y")) {
            return new JoystickButton(this, kGamepadButtonY);
        }
        else {
            System.out.println("Invalid Button Port!");
            return null;
        }
    }
    
  /**
   * Returns an object of Button A.
   */
  public JoystickButton getButtonA() {
    return new JoystickButton(this, kGamepadButtonA);
  }
  
  /**
   * Returns an object of Button B.
   */
  public JoystickButton getButtonB() {
    return new JoystickButton(this, kGamepadButtonB);
  }
  
  /**
   * Returns an object of Button X.
   */
  public JoystickButton getButtonX() {
    return new JoystickButton(this, kGamepadButtonX);
  }
  
  /**
   * Returns an object of Button Y.
   */
  public JoystickButton getButtonY() {
    return new JoystickButton(this, kGamepadButtonY);
  }

  //Get the DPad axis' positions
  private double getDPadX() {
    return getRawAxis(kDPadXAxisNum);
  }
    
  private double getDPadY() {
    return getRawAxis(kDPadYAxisNum);
  }
    
  //DPad - In Betweens 
  public boolean getDPadUpLeft() {
    double x = getDPadX();
    double y = getDPadY();
    return (x < -0.5 && y < -0.5);
  }
  public boolean getDPadDownLeft() {
    double x = getDPadX();
    double y = getDPadY();
    return (x < -0.5 && y > 0.5);
  }
  public boolean getDPadDownRight() {
    double x = getDPadX();
    double y = getDPadY();
    return (x > 0.5 && y > 0.5);
  }
  public boolean getDPadUpRight() {
    double x = getDPadX();
    double y = getDPadY();
    return (x > 0.5 && y < -0.5);
  }
    
  //DPad - Cardinal Directions
  public boolean getDPadUp() {
    double y = getDPadY();
    return (y < -0.5);
  }
  public boolean getDPadLeft() {
    double x = getDPadX();
    return (x < -0.5);
  }
  public boolean getDPadDown() {
    double y = getDPadY();
    return (y > 0.5);
  }
  public boolean getDPadRight() {
    double x = getDPadX();
    return (x > 0.5);
  }
 
}