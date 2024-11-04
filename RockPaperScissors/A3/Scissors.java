public class Scissors extends Choice {
    public Scissors(){
        super(GameChoices.SCISSORS);
    }

    @Override
    public String competeExplanation(Choice otherPlayerChoice) {
        if (otherPlayerChoice instanceof Paper) {
            return "Scissors cuts Paper";
        } else if (otherPlayerChoice instanceof Lizard) {
            return "Scissors decapitates Lizard";
        } else {
            return "It's a tie!";
        }
    }

    @Override
    public int determineWin(Choice otherPlayerChoice) {
        // Scissors wins
        if (otherPlayerChoice instanceof Paper || otherPlayerChoice instanceof Lizard) {
            return 1; 
        // Scissors loses
        } else if (otherPlayerChoice instanceof Rock || otherPlayerChoice instanceof Spock) {
            return -1; 
        // Tie
        } else {
            return 0; 
        }
    }
}