package com.philips.lighting.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.Timer;

import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHLightState;

public class LightChangeTimer implements ActionListener {

  private Timer timer;
  private int currentIndex;

  public LightChangeTimer(final DefaultListModel<PHLightState> lightStates) {

    // The the HueSDK singleton.
    final PHHueSDK phHueSDK = PHHueSDK.getInstance();

    this.currentIndex = 0;
    this.timer = new Timer(10000, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        phHueSDK.getSelectedBridge().setLightStateForDefaultGroup(lightStates.get(currentIndex));
        currentIndex = (currentIndex + 1) % lightStates.size();
        timer.restart();
      }
    });
  }

  public void stopTimer() {
    this.timer.stop();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.timer.start();
  }
}
