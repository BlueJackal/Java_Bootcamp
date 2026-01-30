import java.util.Scanner;

public class CritCalculator {
    
    // Class Variables
    int[] Raw;
    static double CritMultiplyer = 1.5;
    static double MasteryValue = 1.332;
    static double CritChance = 21;

    static int Attack_MortalStrike = 15_795;
    static int Attack_Overpower = 11_594;
    static int Attack_Execute = 24_365;

    //Main Function
    public static void main(String[] args){
        

        // Creating a scanner object connecting to System.in (our keyboard)
        Scanner scanner = new Scanner(System.in);

        // Get how many unique damage events we want to calculate
        System.out.println("Insert how many damage events you'd like to calculate:");
        int numEvents = scanner.nextInt();

        System.out.println("How many iterations do you want to run?");
        int numIterations = scanner.nextInt();

        System.out.println("What is your crit chance?");
        CritChance = scanner.nextDouble();

        System.out.println("What is your mastery value?");
        MasteryValue = scanner.nextDouble();

        // The final result
        double thousandIterationDamage = 0;

        for (int i = 0; i < numIterations; i++){

            int[] attackEvents = new int[numEvents];
            attackEvents = GenerateEvents(numEvents);

            double[] attackOutput = new double[numEvents];

            for (int k = 0; k < numEvents; k++){
            int critRoll = (int)(Math.random() * 101);

            if (critRoll <= CritChance){
                    attackOutput[k] = (attackEvents[k] * MasteryValue) * CritMultiplyer;
                } else {
                    attackOutput[k] = attackEvents[k] * MasteryValue;
                }
            }

            double totalDamage = 0;

            for (int j = 0; j < numEvents; j++){
                totalDamage += attackOutput[j];
            }

            thousandIterationDamage += totalDamage;
        }

        

        System.out.println("Final dps average after " + numIterations + " iterations is " + String.format("%.2f", thousandIterationDamage / numIterations, 2));
    }

    // Neighbor Function for Generating Events
    public static int[] GenerateEvents(int number_of_events){
        
        // Create an array of specific size, or Java will shit
        int[] events = new int[number_of_events];


        for (int i = 0; i < number_of_events; i++){
            int randomNum = (int)(Math.random() * 3);

            if (randomNum == 0) {
                events[i] = Attack_MortalStrike; 
            } else if (randomNum == 1) {
                events[i] = Attack_Overpower;
            } else if (randomNum == 2) {
                events[i] = Attack_Execute;
            }
        }
        
        return events;
    }

}


