/*
 * Created on 2024-03-24
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

 public final class Player {
    // TODO: implement.
   private String name;
   private String choice;
   private int score;

   public Player (String name, String choice){
      this.name = name;
      this.choice = choice;
      this.score = 0;
   }

   public String getName(){
      return name;
   }

   public String getChoice(){
      return choice;
   }

   public void setChoice(String choice){
      this.choice = choice;
   }

   public int getScore(){
      return score;
   }

   public void incrementScore(){
      score++;
   }
}
