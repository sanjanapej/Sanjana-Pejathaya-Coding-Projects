/*
 * Created on Wed Jan 31 2024
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */

import java.util.Random;

public class Spell {
    private String name;
    private Random random;

    public Spell(String name){
        this.name = name;
        this.random = new Random();
    }

     public static void castSpellProtego(Spell spell) {
        System.out.println("Casting " + spell.name + " spell!");
        // Add logic specific to each spell here
        int impact = spell.generateImpact();
        System.out.println("Impact of " + spell.name + " spell " + impact);
       
    }

    public static void castSpellExpelliarmus(Spell spell) {
        System.out.println("Casting " + spell.name + " spell!");
        // Add logic specific to each spell here
        int impact = spell.generateImpact();
        System.out.println("Impact of " + spell.name + " spell " + impact);
    }

    public static void castSpellStupefy(Spell spell) {
        System.out.println("Casting " + spell.name + " spell!");
        // Add logic specific to each spell here
        int impact = spell.generateImpact();
        System.out.println("Impact of " + spell.name + " spell " + impact);
    }
    public int generateImpact() {
    
        return random.nextInt(11);
    }
    public static void main(String[] args) {
        Spell protego = new Spell("Protego");
        Spell expelliarmus = new Spell("Expelliarmus");
        Spell stupefy = new Spell("Stupefy");

       castSpellProtego(protego);
       castSpellExpelliarmus(expelliarmus);
       castSpellStupefy(stupefy);


        
    }

    // TODO: implement.

    
}

