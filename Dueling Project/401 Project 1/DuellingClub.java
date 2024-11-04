/*
 * Created on Wed Jan 31 2024
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

public class DuellingClub {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Dueling Club!");

        Dueler harry = new Dueler("Harry Potter", 100);
        Dueler hermione = new Dueler("Hermione Granger", 100);

       Duel duel = new Duel(harry, hermione);


        Spell protego = new Spell("Protego");
        Spell expelliarmus = new Spell("Expelliarmus");
        Spell stupefy = new Spell("Stupefy");

       Spell.castSpellProtego(protego);
       Spell.castSpellExpelliarmus(expelliarmus);
       Spell.castSpellStupefy(stupefy);


        // TODO: implement
        duel.startDuel();
    }
}
