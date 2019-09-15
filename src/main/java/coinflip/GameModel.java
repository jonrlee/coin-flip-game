/*
 * File: GameModel.java
 *
 * Copyright 2002-2019, DeltaSoft, Inc. All rights reserved.
 * This code is PROPRIETARY and CONFIDENTIAL to DeltaSoft, Inc.
 * Use is subject to license terms.
 */
package coinflip;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * The Class GameModel.
 *
 * @author jonl
 * @since version 1
 */
public class GameModel
{

   /** The List flip results. Heads are true, Tails false. */
   private List<Boolean> flipResults_ = new ArrayList<Boolean>();

   /** The Random Number Generator */
   private Random rng_ = new Random(23);

   /** The Game controller. */
   private GameController controller_;

   /**
    * Instantiates a new game model.
    *
    * @param controller the controller
    */
   public GameModel(GameController controller)
   {
      controller_ = controller;
   }

   /**
    * Flip a coin.
    *
    * @return true, if successful
    */
   public boolean flip()
   {
      boolean result = rng_.nextBoolean();
      flipResults_.add(result);
      return result;
   }

   /**
    * Gets the flip results.
    *
    * @return the results, unmodifiable
    */
   public List<Boolean> getResults()
   {
      return java.util.Collections.unmodifiableList(flipResults_);
   }

   /**
    * Gets the average value.
    *
    * @return the average value
    */
   public double getAverageValue()
   {
      double total = flipResults_.size();
      if (total <= 0)
      {
         return 0;
      }
      double heads = 0;
      for (Boolean result : flipResults_)
      {
         if (result)
         {
            heads++;
         }
      }
      System.out.println("heads" + heads);
      System.out.println("total" + total);

      return heads / total;
   }

   /**
    * Gets the total.
    *
    * @return the total
    */
   public int getTotal()
   {
      return flipResults_.size();
   }
}
