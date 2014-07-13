package com.philips.lighting.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

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

  private DefaultListModel<PHLightState> lightStates;
  private JList<PHLightState> colorList;
  
  public LightColorChangeFrame() {
    super("Lights color change");
    lightStates = new DefaultListModel<>();
    
    JPanel buttonPanel = new JPanel();
    JButton changeColorButton = new JButton("Add Bulb Color");
    ColorChanger color = new ColorChanger(getContentPane(), getBackground(), lightStates);
    changeColorButton.addActionListener(color);
    buttonPanel.add(changeColorButton);
    
    JButton setColorButton = new JButton("Set Bulb Colors");
    setColorButton.addActionListener(new LightChangeTimer(lightStates));
    buttonPanel.add(setColorButton);
    
    Container content = getContentPane();
    content.add(buttonPanel);
    
    colorList = new JList<>(lightStates);
    colorList.setVisibleRowCount(4);
    colorList.setSelectedIndex(0);
    JScrollPane listPane = new JScrollPane(colorList);
    listPane.setPreferredSize(new Dimension(300,100));
    
    JPanel listPanel = new JPanel();
    listPanel.setBackground(Color.white);
    
    Border listPanelBorder = BorderFactory.createTitledBorder("Added colors");
    listPanel.setBorder(listPanelBorder);
    listPanel.add(listPane);
    content.add(listPanel, BorderLayout.CENTER);
    
    
    Border buttonPanelBorder = BorderFactory.createTitledBorder("Change Selected");

    buttonPanel.setBackground(Color.white);
    buttonPanel.setBorder(buttonPanelBorder);
    buttonPanel.add(changeColorButton);
    
    content.add(buttonPanel, BorderLayout.SOUTH);
    
    setPreferredSize(new Dimension(400,250));
    pack();
    setVisible(true);
  }
}
