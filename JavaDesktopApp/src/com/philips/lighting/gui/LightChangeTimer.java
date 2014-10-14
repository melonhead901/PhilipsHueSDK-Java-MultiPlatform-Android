package com.philips.lighting.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.Timer;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

public class LightChangeTimer implements ActionListener {

  private Timer timer;
  private int currentIndex;

  public LightChangeTimer(final DefaultListModel<PHLightState> lightStates) {

    // The the HueSDK singleton.
    final PHHueSDK phHueSDK = PHHueSDK.getInstance();

    // Get the selected bridge.
    PHBridge bridge = phHueSDK.getSelectedBridge();

    // To get lights use the Resource Cache.
    final List<PHLight> lights = bridge.getResourceCache().getAllLights();

    this.currentIndex = 0;
    this.timer = new Timer(15000, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        currentIndex = (currentIndex + 1) % lightStates.size();
        for (PHLight light : lights) {
          // Null is used here because we don't care about bridge response.
          final PHLightListener nullListener = null;
          phHueSDK.getSelectedBridge().updateLightState(light.getIdentifier(),
              lightStates.get(currentIndex), nullListener);
        }
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
