public class Lizard extends Choice {
 public Lizard(){
        super(GameChoices.LIZARD);
    }

    @Override
    public String competeExplanation(Choice otherPlayerChoice) {
        if (otherPlayerChoice instanceof Spock || otherPlayerChoice instanceof Paper) {
            return "Lizard eats Paper";
        } else if (otherPlayerChoice instanceof Rock || otherPlayerChoice instanceof Scissors) {
            return "Lizard is crushed by Rock";
        } else {
            return "It's a tie!";
        }
    }

    @Override
    public int determineWin(Choice otherPlayerChoice) {
        //Lizard wins
        if (otherPlayerChoice instanceof Spock || otherPlayerChoice instanceof Paper) {
            return 1; 
        // Lizard loses
        } else if (otherPlayerChoice instanceof Rock || otherPlayerChoice instanceof Scissors) {
            return -1; 
        //Tie
        } else {
            return 0; 
        }
    }
}