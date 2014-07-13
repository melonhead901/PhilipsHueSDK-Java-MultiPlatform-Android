package com.philips.lighting.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;

import com.philips.lighting.hue.sdk.utilities.PHUtilities;
import com.philips.lighting.model.PHLightState;

public class ColorChanger implements ActionListener {
  private Component contentPane;
  private Color background;
  private DefaultListModel<PHLightState> lightStates;
  
  public ColorChanger(Component contentPane, Color backgroundColor, DefaultListModel<PHLightState> lightStates) {
    this.contentPane = contentPane;
    this.background = backgroundColor;
    this.lightStates = lightStates;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    Color lightColour = JColorChooser.showDialog(contentPane,
        "Choose Bulb Color", background);

    if (lightColour != null) {
      PHLightState lightState = new PHLightState();
      float[] xy = PHUtilities.calculateXYFromRGB(lightColour.getRed(),
          lightColour.getGreen(), lightColour.getBlue(), "LCT001");
      lightState.setX(xy[0]);
      lightState.setY(xy[1]);
      lightStates.addElement(lightState);
    }
  }
}
