/*
 * File: GameController.java
 *
 * Copyright 2002-2019, DeltaSoft, Inc. All rights reserved.
 * This code is PROPRIETARY and CONFIDENTIAL to DeltaSoft, Inc.
 * Use is subject to license terms.
 */
package coinflip;

/**
 * The Class GameController as part of the MVC pattern.
 *
 * @author jonl
 * @since version 1
 */
public class GameController
{

   /** The Game view. */
   private GameView gameView_;

   /** The Game model. */
   private GameModel gameModel_;

   /**
    * Instantiates a new game controller.
    */
   public GameController()
   {

      gameModel_ = new GameModel(this);
      gameView_ = new GameView(this);
   }

   /**
    * Creates the and show GUI.
    */
   public void createAndShowGUI()
   {
      gameView_.createAndShowGUI();
   }

   /**
    * Perform flip.
    */
   public void performFlip()
   {
      boolean result = gameModel_.flip();
      gameView_.registerOutcome(result, gameModel_.getTotal());
      double average = gameModel_.getAverageValue();
      gameView_.updateAverage(average);
   }

}
