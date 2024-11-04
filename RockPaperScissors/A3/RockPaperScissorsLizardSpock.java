/*
 * Created on 2024-03-24
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsLizardSpock {

    private Player player;
    private Player computer;

    private final int minWins = 2;

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    protected RockPaperScissorsLizardSpock() {
        System.out.println("Player 1, please enter your name:");
        player = new Player(scanner.nextLine(), "");
        System.out.println();
        computer = new Player("Computer", "");

    //I am passing an empty string ("") in the constructor of Player to create a player without specifying a choice.
    }

    // TODO: implement playRound and round win check here
    public void playRound() {
        System.out.printf("<<Player name>>, please enter %s, %s, %s, %s, or %s: %n", GameChoices.ROCK, GameChoices.PAPER,
                GameChoices.SCISSORS, GameChoices.LIZARD, GameChoices.SPOCK);
        // All choices have been provided for each rounds

        String choice = scanner.nextLine();
        player.setChoice(choice);
        Choice playerChoice = getChoice(choice);
        Choice computerChoice = getRandomChoice();

        System.out.printf("%s chose %s%n", player.getName(), playerChoice.getName());
        System.out.printf("%s chose %s%n", computer.getName(), computerChoice.getName());

        int result = playerChoice.compete(computerChoice);

        if (result == 1){
            System.out.println(player.getName() + " wins this round");
            player.incrementScore();
        }else if(result == -1){
            System.out.println(computer.getName() + " wins this round");
        }else{
            System.out.println("It's a tie");
        }
        System.out.println();
    }

    // TODO: implement a method to convert the player's input into a choice
    private Choice getChoice(String choice) {
        choice = choice.toLowerCase();
        // Check the passed string and return a Choice object (i.e., Rock, Paper, Scissors, Lizard, or Spock)
        // Pick a default case

        switch (choice){
        case "rock":
            return new Rock();
        case "paper":
            return new Paper();
        case "scissors":
            return new Scissors();
        case "lizard":
            return new Lizard();
        case "spock":
            return new Spock();
        
        // Input validation and error handling 
        default:
            System.out.println("Invalid Choice. Please try again.");
            return getChoice(scanner.nextLine());
        }
        
    }

    // TODO: implement a method to get a random choice for the computer
    private Choice getRandomChoice() {
        // Based on a random number, return a Choice object (i.e., Rock, Paper, Scissors, Lizard, or Spock)
        int randomNumber = random.nextInt(5);
        // Pick a default case
        //Switch case statememnt for the multiple choices procided
        switch (randomNumber){
        case 0:
            return new Rock();
        case 1: 
            return new Paper();
        case 2:
            return new Scissors();
        case 3: 
            return new Lizard();
        case 4: 
            return new Spock();

        // Input validation and error handling 
        default:
            return null;
        }
    }

    // TODO: implement the main game loop and overall game win check here
    public void startGame() {
        while (player.getScore() < minWins && computer.getScore() < minWins){
            playRound();
        }
        if (player.getScore() >= minWins){
            System.out.println(player.getName() + " wins the game!");

        }else {
            System.out.println(computer.getName() + " wins the game!");
        }

        System.out.println("Final Scores: ");
        System.out.println(player.getName() + ": " + player.getScore());
        System.out.println(computer.getName() + ": " + computer.getScore());

    
    }

    // NO NEED TO TOUCH main()
    public static void main(String[] args) {
        RockPaperScissorsLizardSpock game = new RockPaperScissorsLizardSpock();
        game.startGame();
        game.scanner.close();
    }
}
