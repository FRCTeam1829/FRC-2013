package com.team254.lib.control;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


/**
 * Keeps track of, updates, and pokes the subsystems and controllers periodically.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ControlUpdater {
  private static ControlUpdater instance = null;
  private Vector systems = new Vector();
  private Timer controlUpdater;
  private double period = 1.0 / 50.0;

  private class UpdaterTask extends TimerTask {
    private ControlUpdater updater;

    public UpdaterTask(ControlUpdater updater) {
      if (updater == null) {
        throw new NullPointerException("Given ControlUpdater was null");
      }
      this.updater = updater;
    }

    public void run() {
      updater.update();
    }
  }

  public void update() {
    //System.out.println("s " + edu.wpi.first.wpilibj.Timer.getFPGATimestamp() + " " + this.hashCode());
    for(int i = 0; i < systems.size(); i++) {
      ((Updatable)systems.elementAt(i)).update();
    }
    //System.out.println("e " + edu.wpi.first.wpilibj.Timer.getFPGATimestamp());
  }

  public static ControlUpdater getInstance() {
    if(instance == null) {
      instance = new ControlUpdater();
    }
    return instance;
  }

  public void start() {
    if(controlUpdater == null) {
      controlUpdater = new Timer();
      controlUpdater.schedule(new UpdaterTask(this), 0L, (long) (this.period * 1000));
    }
  }

  public void stop() {
    if(controlUpdater != null) {
      controlUpdater.cancel();
      controlUpdater = null;
    }
  }

  public void add(Updatable system) {
    systems.addElement(system);
  }

  public void setPeriod(double period) {
    this.period = period;
    stop();
    start();
  }
}
