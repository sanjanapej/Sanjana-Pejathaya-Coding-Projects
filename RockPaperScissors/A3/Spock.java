public class Spock extends Choice {
   
    public Spock(){
        super(GameChoices.SPOCK);
    }

    @Override
    public String competeExplanation(Choice otherPlayerChoice) {
        if (otherPlayerChoice instanceof Scissors || otherPlayerChoice instanceof Rock) {
            return "Spock vaporizes Rock";
        } else if (otherPlayerChoice instanceof Paper || otherPlayerChoice instanceof Lizard) {
            return "Spock is disproven by Paper";
        } else {
            return "It's a tie!";
        }
    }

    @Override
    public int determineWin(Choice otherPlayerChoice) {
        if (otherPlayerChoice instanceof Scissors || otherPlayerChoice instanceof Rock) {
            return 1; // Spock wins
        } else if (otherPlayerChoice instanceof Paper || otherPlayerChoice instanceof Lizard) {
            return -1; // Spock loses
        } else {
            return 0; // Tie
        }
    }
}