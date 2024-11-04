/*
 * Created on Wed Jan 31 2024
 *
 * Copyright (c) 2024 Nadine von Frankenberg
 */
import java.util.Random;

public class Dueler {
    private int totalImpact; // Total impact on the dueler
    private Random random = new Random(); // Initialize Random object


    private int health;

    public Dueler() {
        this.totalImpact = 0;
       
        this.health = 0;
    }

   

    public Dueler(String name, int health) {
        switch (name) {
            case "Harry Potter":
                this.health = random.nextInt(11) + 10;
                break;
            case "Hermione Granger":
                this.health = random.nextInt(11) + 10;
                break;
            default:
                System.out.println("Unrecognized player type");
            
                this.health = random.nextInt(11) + 10;
                break;
        }
    }

    



    public int getHealth() {
        return health;
    }

   


    

    public void receiveImpact(int impact) {
        totalImpact += impact;
    }

    public int getTotalImpact() {
        return totalImpact;
    }

    }
