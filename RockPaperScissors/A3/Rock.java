/*
 * Created on 2024-03-24
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

public class Rock extends Choice{
    

    public Rock(){
        super(GameChoices.ROCK);
    }
    // TODO Implement a specific version of getName() for each choice
    // Hint: Make use of GameChoices to get a nice representation of the name!
    /*public String getName(){
        return GameChoices.ROCK;
    }*/
    

    // TODO Implement a specific version of competeExplanation() for each choice
    // Should return a String of the game round's explanation (e.g., "Rock crushes
    // Lizard")
    @Override
     public String competeExplanation(Choice otherPlayerChoice) {
        if (otherPlayerChoice.getName().equals(GameChoices.SCISSORS)) {
            return "Rock crushes Scissors";
        } else if (otherPlayerChoice.getName().equals(GameChoices.LIZARD)) {
            return "Rock crushes Lizard";
        } else {
            return "It's a tie!";
        }


    }

   

    // TODO Implement a specific version of determineWin() for each choice
    /*
     * Compares the choice with the other player's choice
     * - Should return an `int`:
     * - `0`: choice is equal to otherPlayerChoice
     * - `1`: choice wins agains otherPlayerChoice
     * - `-1`: choice looses to otherPlayerChoice
     */

    @Override
    public int determineWin(Choice otherPlayerChoice){
        // Rock wins
        if (otherPlayerChoice.getName().equals(GameChoices.SCISSORS) || otherPlayerChoice.getName().equals(GameChoices.LIZARD)) {
            return 1; 
        // Rock loses
        } else if (otherPlayerChoice.getName().equals(GameChoices.PAPER) || otherPlayerChoice.getName().equals(GameChoices.SPOCK)) {
            return -1; 
        // Tie
        } else {
            return 0; 
        }
    }
}
