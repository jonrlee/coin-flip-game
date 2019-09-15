/*
 * File: CoinFlipGame.java
 *
 * Copyright 2002-2019, DeltaSoft, Inc. All rights reserved.
 * This code is PROPRIETARY and CONFIDENTIAL to DeltaSoft, Inc.
 * Use is subject to license terms.
 */
package coinflip;

import javax.swing.SwingUtilities;



/**
 * The Class CoinFlipGame.
 *
 * @author jonl
 * @since version 1
 */
public class CoinFlipGame
{
   private static void createAndShowGUI()
   {
      GameController controller = new GameController();
      controller.createAndShowGUI();
   }

   /**
    * The main method.
    *
    * @param args the arguments
    */
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            createAndShowGUI();
         }

      });
   }
}
