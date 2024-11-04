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
     public void castSpell() {
        System.out.println("Casting " + name + " spell!");
        // Add logic specific to each spell here
        int impact = generateImpact();
        System.out.println("Impact of " + name + " spell " + impact);
    }
    private int generateImpact() {
    
        return random.nextInt(11);
    }
    public static void main(String[] args) {
        Spell protego = new Spell("Protego");
        Spell expelliarmus = new Spell("Expelliarmus");
        Spell stupefy = new Spell("Stupefy");

        protego.castSpell();
        expelliarmus.castSpell();
        stupefy.castSpell();
    }

    // TODO: implement.

    
}

