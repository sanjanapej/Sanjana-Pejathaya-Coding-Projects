/*
 * Created on 2024-03-24
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */
import java.util.Scanner;
import java.util.Random;

// NO NEED TO TOUCH THIS CLASS
public final class GameChoices {
    // String representations of the game choices, including emojis
    public static final String ROCK = "Rock \uD83E\uDEA8";
    public static final String PAPER = "Paper \uD83D\uDCC4";
    public static final String SCISSORS = "Scissors \u2702\uFE0F";
    public static final String LIZARD = "Lizard \uD83E\uDD8E";
    public static final String SPOCK = "Spock \uD83D\uDD96️";
    
    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    
    // Prevent instantiation (use a private constructor)
    private Choice getChoice(String choice) {
    choice = choice.toLowerCase();
    switch (choice) {
        case "rock":
            return new Choice(GameChoices.ROCK);
        case "paper":
            return new Choice(GameChoices.PAPER);
        case "scissors":
            return new Choice(GameChoices.SCISSORS);
        case "lizard":
            return new Choice(GameChoices.LIZARD);
        case "spock":
            return new Choice(GameChoices.SPOCK);
        default:
            System.out.println("Invalid choice. Please try again.");
            return getChoice(scanner.nextLine());
    }
}

private Choice getRandomChoice() {
    int randomNumber = random.nextInt(5); // Generate random number between 0 and 4
    switch (randomNumber) {
        case 0:
            return new Choice(GameChoices.ROCK);
        case 1:
            return new Choice(GameChoices.PAPER);
        case 2:
            return new Choice(GameChoices.SCISSORS);
        case 3:
            return new Choice(GameChoices.LIZARD);
        case 4:
            return new Choice(GameChoices.SPOCK);
        default:
            return null; // Should never happen
    }
}

}
