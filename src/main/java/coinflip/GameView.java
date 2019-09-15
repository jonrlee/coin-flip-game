/*
 * File: GameView.java
 *
 * Copyright 2002-2019, DeltaSoft, Inc. All rights reserved.
 * This code is PROPRIETARY and CONFIDENTIAL to DeltaSoft, Inc.
 * Use is subject to license terms.
 */
package coinflip;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



/**
 * The Class GameView as part of the MVC pattern.
 *
 * @author jonl
 * @since version 1
 */
public class GameView implements ActionListener
{

   /** The main JFrame. */
   private JFrame frame_;

   /** The Game controller. */
   GameController controller_;

   /** The Result Display */
   private JTextArea resultDisplay_;

   /** The Action Command string for the flip action. */
   private String flipCommand_ = "GameView.flip";

   /** The Action Command string for the flip action. */
   private String flip10Command_ = "GameView.flip10";

   /** The current average value. */
   private JLabel averageValue_;

   /**
    * Instantiates a new game view.
    *
    * @param controller the controller
    */
   public GameView(GameController controller)
   {
      controller_ = controller;
   }

   /**
    * Creates and shows GUI.
    */
   public void createAndShowGUI()
   {
      frame_ = new JFrame("CoinFlipGame");
      frame_.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel contentPane = new JPanel(new BorderLayout());

      JPanel northPanel = createNorthPanel();
      contentPane.add(northPanel, BorderLayout.NORTH);

      JPanel centerPanel = createCenterPanel();
      contentPane.add(centerPanel, BorderLayout.CENTER);

      JPanel southPanel = createSouthPanel();
      contentPane.add(southPanel, BorderLayout.SOUTH);

      //Display the window.
      frame_.setContentPane(contentPane);
      frame_.pack();
      frame_.setLocationRelativeTo(null);
      frame_.setVisible(true);

   }

   private JPanel createNorthPanel()
   {
      JPanel northPanel = new JPanel(new BorderLayout());
      JLabel label = new JLabel("Heads you win, tails you lose.");
      JPanel buttonPanel = createButtonPanel();
      northPanel.add(label, BorderLayout.NORTH);
      northPanel.add(buttonPanel, BorderLayout.SOUTH);
      return northPanel;
   }

   private JPanel createCenterPanel()
   {
      JPanel centerPanel = new JPanel(new BorderLayout());
      centerPanel.setPreferredSize(new Dimension(450, 50));
      JScrollPane scrollPane = createOutcomePane();
      centerPanel.add(scrollPane, BorderLayout.CENTER);
      return centerPanel;
   }

   private JPanel createSouthPanel()
   {
      JPanel southPanel = new JPanel(new BorderLayout());

      JPanel statsPanel = createStatsPanel();
      southPanel.add(statsPanel, BorderLayout.SOUTH);
      return southPanel;
   }

   private JScrollPane createOutcomePane()
   {
      resultDisplay_ = new JTextArea();
      JScrollPane scrollPane = new JScrollPane(resultDisplay_);
      return scrollPane;
   }

   private JPanel createStatsPanel()
   {
      JPanel statsPanel = new JPanel();
      JLabel averageLabel = new JLabel("Average");
      averageValue_ = new JLabel("0%");
      statsPanel.add(averageLabel);
      statsPanel.add(averageValue_);
      return statsPanel;
   }

   private JPanel createButtonPanel()
   {
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
      JButton flipButton = new JButton("Flip Coin");
      flipButton.setActionCommand(flipCommand_);
      flipButton.addActionListener(this);

      JButton flip10Button = new JButton("Flip 10 Coins");
      flip10Button.setActionCommand(flip10Command_);
      flip10Button.addActionListener(this);

      buttonPanel.add(flipButton);
      buttonPanel.add(flip10Button);
      buttonPanel.add(Box.createHorizontalGlue());
      return buttonPanel;
   }

   /**
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
    */
   @Override
   public void actionPerformed(ActionEvent e)
   {
      String command = e.getActionCommand();
      if (flipCommand_.equals(command))
      {
         processFlip();
      }
      else if (flip10Command_.equals(command))
      {
         for (int i = 0; i < 10; i++)
         {
            processFlip();
         }
      }
   }

   /**
    * Process a flip action
    */
   private void processFlip()
   {
      controller_.performFlip();
   }

   /**
    * Register an outcome on the display.
    *
    * @param outcome the outcome
    */
   public void registerOutcome(boolean outcome, int total)
   {
      String outcomeValue = outcome ? "T" : "F";
      //      JLabel label = new JLabel(outcomeValue);
      resultDisplay_.append(outcomeValue);
      if (total % 10 == 0)
      {
         resultDisplay_.append(" ");
      }
      resultDisplay_.revalidate();
   }

   /**
    * Update average.
    *
    * @param average the average
    */
   public void updateAverage(double average)
   {
      double percentage = average * 100;
      String percentageString = String.format("%.0f%%", percentage);
      averageValue_.setText(percentageString);
      averageValue_.revalidate();
   }
}
