public class Paper extends Choice {
     public Paper(){
        super(GameChoices.PAPER);
    }

    @Override
    public String competeExplanation(Choice otherPlayerChoice) {
        if (otherPlayerChoice instanceof Rock) {
            return "Paper covers Rock";
        } else if (otherPlayerChoice instanceof Spock) {
            return "Paper disproves Spock";
        } else {
            return "It's a tie!";
        }
    }

   @Override
public int determineWin(Choice otherPlayerChoice) {
    // Paper wins
    if (otherPlayerChoice instanceof Rock || otherPlayerChoice instanceof Spock) {
        return 1;
    // Paper loses
    } else if (otherPlayerChoice instanceof Scissors || otherPlayerChoice instanceof Lizard) {
        return -1;
    // Tie
    } else {
        return 0;
    }
    }
}
