/*
 * Created on Wed Jan 31 2024
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

public class Duel {
    //Duelers
    private Dueler dueler1;
    private Dueler dueler2;
    private Spell protegoSpell;
    private Spell expelliarmusSpell;
    private Spell stupefySpell;
   

    // A Duel should involve two duelers
    Duel(Dueler dueler1, Dueler dueler2) {
        this.dueler1 = dueler1;
        this.dueler2 = dueler2;
        this.protegoSpell = new Spell("Protego");
        this.expelliarmusSpell = new Spell("Expelliarmus");
        this.stupefySpell = new Spell("Stupefy");
    }

    public void startDuel() {
         Dueler harry = new Dueler("Harry Potter", 100);
        Dueler hermione = new Dueler("Hermione Granger",  90);

        System.out.println("Duel starts between " + dueler1 + "and" + dueler2);

         while (dueler1.getHealth() > 0 && dueler2.getHealth() > 0) {
       
       
        // Simulate spell impact on health
            int impact1 = expelliarmusSpell.generateImpact();// Calculate impact for dueler1
            int impact2 = stupefySpell.generateImpact(); // Calculate impact for dueler2
            dueler1.receiveImpact(impact1);
            dueler2.receiveImpact(impact2);

        // Optional: Print the current health of each dueler
        System.out.println("Health of Harry Potter: " + dueler1.getHealth());
        System.out.println("Health of Hermione Granger: " + dueler2.getHealth());

         if (dueler1.getHealth() <= 0 || dueler2.getHealth() <= 0) {
    // The duel is over, determine the winner and declare it
            Dueler winner = (dueler1.getHealth() > 0) ? dueler1 : dueler2;
            declareWinner(winner);

        }
    
        }
       
    }



   


    
   

     
    


    


    

    private void declareWinner(Dueler winner) {
        

       // System.out.println("\nImpact on " + ": " /* + dueler1 received impact */);
       // System.out.println("Impact on " /* + dueler2 name */ + ": " /* + dueler2 received impact */ + "\n");

        //System.out.println(/* winner name + */ " has won the duel!");
    }

    public static void main(String[] args) throws Exception {
        Dueler harry = new Dueler("Harry Potter", 100);
        Dueler hermione = new Dueler("Hermione Granger",  90);
        Duel duel = new Duel(harry, hermione);
        duel.startDuel();

       // Dueler winner = determineWinner(dueler1, dueler2);
       // duel.declareWinner(winner);
    

    }
}
