/*
 * Created on 2024-03-24
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

// TODO: implement Choice
// Choice should function as the Base class for all the choices
// There is no default Choice!

// Rock was already partially implemented as an example
// You may make changes to the structure and type of both classes!

public class Choice {
    private String name;

    public Choice(String name){
        this.name = name;
    }

    // NO NEED TO TOUCH compete()
    public int compete(Choice otherPlayerChoice) {
        System.out.println("Outcome: " + competeExplanation(otherPlayerChoice));
        return determineWin(otherPlayerChoice);
    }

    public String getName(){
        return name;
    }
    

    public String competeExplanation(Choice otherPlayerChoice){
        if (this == otherPlayerChoice){
            return "It's a tie!";
        }

    String explanation = "Outcome = ";
    switch(this.getName()){
        case GameChoices.ROCK:
            explanation += this.determineRockExplanation(otherPlayerChoice);
            break;
        case GameChoices.PAPER:
            explanation += this.determinePaperExplanation(otherPlayerChoice);
            break;
        case GameChoices.SCISSORS:
            explanation += this.determineScissorsExplanation(otherPlayerChoice);
            break;
        case GameChoices.LIZARD:
            explanation += this.determineLizardExplanation(otherPlayerChoice);
            break;
        case GameChoices.SPOCK:
            explanation += this.determineSpockExplanation(otherPlayerChoice);
            break;
        }

        return explanation;
    
    }

   public int determineWin(Choice otherPlayerChoice) {
        if (this == otherPlayerChoice) {
            return 0; // Tie
        }

        switch (this.getName()) {
            case GameChoices.ROCK:
                return this.determineRockWin(otherPlayerChoice);
            case GameChoices.PAPER:
                return this.determinePaperWin(otherPlayerChoice);
            case GameChoices.SCISSORS:
                return this.determineScissorsWin(otherPlayerChoice);
            case GameChoices.LIZARD:
                return this.determineLizardWin(otherPlayerChoice);
            case GameChoices.SPOCK:
                return this.determineSpockWin(otherPlayerChoice);
            default:
                return 0; // Default to tie
        }
    }

     private int determineRockWin(Choice otherPlayerChoice) {
        return (otherPlayerChoice.getName().equals(GameChoices.SCISSORS) || otherPlayerChoice.getName().equals(GameChoices.LIZARD)) ? 1 : -1;
    }

    private int determinePaperWin(Choice otherPlayerChoice) {
        return (otherPlayerChoice.getName().equals(GameChoices.ROCK) || otherPlayerChoice.getName().equals(GameChoices.SPOCK)) ? 1 : -1;
    }

    private int determineScissorsWin(Choice otherPlayerChoice) {
        return (otherPlayerChoice.getName().equals(GameChoices.PAPER) || otherPlayerChoice.getName().equals(GameChoices.LIZARD)) ? 1 : -1;
    }

    private int determineLizardWin(Choice otherPlayerChoice) {
        return (otherPlayerChoice.getName().equals(GameChoices.PAPER) || otherPlayerChoice.getName().equals(GameChoices.SPOCK)) ? 1 : -1;
    }

    private int determineSpockWin(Choice otherPlayerChoice) {
        return (otherPlayerChoice.getName().equals(GameChoices.ROCK) || otherPlayerChoice.getName().equals(GameChoices.SCISSORS)) ? 1 : -1;
    }

    private String determineRockExplanation(Choice otherPlayerChoice) {
        return otherPlayerChoice.getName().equals(GameChoices.SCISSORS) ? "Rock crushes Scissors" : "Rock crushes Lizard";
    }

    private String determinePaperExplanation(Choice otherPlayerChoice) {
        return otherPlayerChoice.getName().equals(GameChoices.ROCK) ? "Paper covers Rock" : "Paper disproves Spock";
    }

    private String determineScissorsExplanation(Choice otherPlayerChoice) {
        return otherPlayerChoice.getName().equals(GameChoices.PAPER) ? "Scissors cuts Paper" : "Scissors decapitates Lizard";
    }

    private String determineLizardExplanation(Choice otherPlayerChoice) {
        return otherPlayerChoice.getName().equals(GameChoices.PAPER) ? "Lizard eats Paper" : "Lizard poisons Spock";
    }

    private String determineSpockExplanation(Choice otherPlayerChoice) {
        return otherPlayerChoice.getName().equals(GameChoices.ROCK) ? "Spock vaporizes Rock" : "Spock smashes Scissors";
    }


}
