package com.philips.lighting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import com.philips.lighting.model.PHLightState;

public class LightColorChangeFrame extends JFrame {

  /**
   * Serial Version UID
   */
  private static final long serialVersionUID = 1807091262601041714L;

  private final DefaultListModel<PHLightState> lightStates;
  private final JList<PHLightState> colorList;

  public LightColorChangeFrame() {
    super("Lights color change");
    lightStates = new DefaultListModel<>();

    JPanel buttonPanel = new JPanel();

    JButton addColorButton = new JButton("Add Bulb Color");
    ColorChanger color = new ColorChanger(getContentPane(), getBackground(),
        lightStates);
    addColorButton.addActionListener(color);
    buttonPanel.add(addColorButton);

    JButton setColorButton = new JButton("Start Colors");
    final LightChangeTimer lightChangeTimer = new LightChangeTimer(lightStates);
    setColorButton.addActionListener(lightChangeTimer);
    buttonPanel.add(setColorButton);

    JButton stopButton = new JButton("Stop Colors");
    stopButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lightChangeTimer.stopTimer();
      }
    });
    buttonPanel.add(stopButton);

    Container content = getContentPane();
    content.add(buttonPanel);

    colorList = new JList<>(lightStates);
    colorList.setVisibleRowCount(4);
    colorList.setSelectedIndex(0);
    JScrollPane listPane = new JScrollPane(colorList);
    listPane.setPreferredSize(new Dimension(300, 100));

    JButton removeColorButton = new JButton("Add Bulb Color");
    removeColorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (colorList.getSelectedIndex() != -1) {
          colorList.remove(colorList.getSelectedIndex());
        }
      }
    });
    buttonPanel.add(removeColorButton);

    JPanel listPanel = new JPanel();
    listPanel.setBackground(Color.white);

    Border listPanelBorder = BorderFactory.createTitledBorder("Added colors");
    listPanel.setBorder(listPanelBorder);
    listPanel.add(listPane);
    content.add(listPanel, BorderLayout.CENTER);

    Border buttonPanelBorder = BorderFactory
        .createTitledBorder("Change Selected");

    buttonPanel.setBackground(Color.white);
    buttonPanel.setBorder(buttonPanelBorder);
    buttonPanel.add(addColorButton);

    content.add(buttonPanel, BorderLayout.SOUTH);

    setPreferredSize(new Dimension(400, 250));
    pack();
    setVisible(true);
  }
}
