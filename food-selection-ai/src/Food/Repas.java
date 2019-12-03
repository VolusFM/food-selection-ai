package Food;


/*
 *
 * Stores a candidate repas
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Repas {

    // Holds our repas of aliments
    private ArrayList<Aliment> repas = new ArrayList<Aliment>();
    // Cache
    private double fitness = 0;
    private int score = 0;
    private int numberOfAlimentsAsked = 5 * 2; // Calculate for two repas (a day)
    private Random rand = new Random();

    // Constructs a blank repas
    public Repas() {
        for (int i = 0; i < numberOfAlimentsAsked; i++) {
            repas.add(null);
        }
    }

    public Repas(ArrayList<Aliment> repas) {
        this.repas = repas;
    }

    // Creates a random individual
    public void generateIndividual() {

        // Randomly select aliments
        for (int i = 0; i < numberOfAlimentsAsked; i++) {
            setAliment(i, getRandomAliment(i));
        }
    }

    // Gets a aliment from the repas
    public Aliment getAliment(int repasPosition) {
        return (Aliment) repas.get(repasPosition);
    }

    public Aliment getRandomAliment(int position) {
        List<Aliment> aliments = Main.alimentlist.get(position % 5);
        int random = rand.nextInt(aliments.size());
        return aliments.get(random);
    }

    // Sets a aliment in a certain position within a repas
    public void setAliment(int repasPosition, Aliment aliment) {
        repas.set(repasPosition, aliment);
        // If the repass been altered we need to reset the fitness and score
        fitness = 0;
        score = 0;
    }

    // Gets the repass fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1 / (double) getScore();
        }
        return fitness;
    }

    // Gets the total score of the repas
    public int getScore() {
        if (score == 0) {
            double totalEnergy = 0;
            double totalProtein = 0;
            double totalCarbohydrate = 0;
            double totalSugar = 0;
            double totalFat = 0;
            double totalFatSaturated = 0;
            double totalRetinol = 0;
            double totalAVitamin = 0;
            double totalThiamin = 0;
            double totalRiboflavin = 0;
            double totalB6Vitamin = 0;
            double totalFolicAcid = 0;
            double totalB12Vitamin = 0;
            double totalCVitamin = 0;
            double totalCalcium = 0;
            double totalPhosphorus = 0;
            double totalMagnesium = 0;
            double totalIron = 0;
            double totalZinc = 0;
            double totalCopper = 0;
            double totalSelenium = 0;
            double totalPotassium = 0;
            double totalSodium = 0;
            double totalCaffeine = 0;
            double totalAlcohol = 0;

            // Loop through our repas's aliments
            for (int alimentIndex = 0; alimentIndex < repasSize(); alimentIndex++) {
                // Get aliment we're travelling from
                Aliment aliment = getAliment(alimentIndex);
                totalEnergy += aliment.getEnergy();
                totalProtein += aliment.getProtein();
                totalCarbohydrate += aliment.getCarbohydrate();
                totalFat += aliment.getFatTotal();
                totalSugar += aliment.getSugarsTotal();
                totalFatSaturated += aliment.getFattyAcidsTotalSaturated();
                totalRetinol += aliment.getRetinol();
                totalAVitamin += aliment.getAVitaminMce();
                totalThiamin += aliment.getThiamin();
                totalRiboflavin += aliment.getRiboflavin();
                totalB6Vitamin += aliment.getB6Vitamin();
                totalFolicAcid += aliment.getFolicAcid();
                totalB12Vitamin += aliment.getB12Vitamin() + aliment.getB12VitaminAdded();
                totalCVitamin += aliment.getCVitamin();
                totalCalcium += aliment.getCalcium();
                totalPhosphorus += aliment.getPhosphorus();
                totalMagnesium += aliment.getMagnesium();
                totalIron += aliment.getIron();
                totalZinc += aliment.getZinc();
                totalCopper += aliment.getCopper();
                totalSelenium += aliment.getSelenium();
                totalPotassium += aliment.getPotassium();
                totalSodium += aliment.getSodium();
                totalCaffeine += aliment.getCaffeine();
                totalAlcohol += aliment.getAlcohol();
            }
            double repasScore = Math.pow((totalEnergy - Main.goalValue.getEnergy()), 2) + Math.pow((totalProtein - Main.goalValue.getProtein()), 2)
                    + Math.pow((totalCarbohydrate - Main.goalValue.getCarbohydrate()), 2) + Math.pow((totalSugar - Main.goalValue.getSugarsTotal()), 2)
                    + Math.pow((totalFat - Main.goalValue.getFatTotal()), 2) + Math.pow((totalFatSaturated - Main.goalValue.getFattyAcidsTotalSaturated()), 2)
                    + Math.pow((totalRetinol - Main.goalValue.getRetinol()), 2) + Math.pow((totalAVitamin - Main.goalValue.getAVitaminMce()), 2)
                    + Math.pow((totalThiamin - Main.goalValue.getThiamin()), 2) + Math.pow((totalRiboflavin - Main.goalValue.getRiboflavin()), 2)
                    + Math.pow((totalB6Vitamin - Main.goalValue.getB6Vitamin()), 2) + Math.pow((totalFolicAcid - Main.goalValue.getFolicAcid()), 2)
                    + Math.pow((totalB12Vitamin - Main.goalValue.getB12Vitamin()), 2)
                    + Math.pow((totalCVitamin - Main.goalValue.getCVitamin()), 2) + Math.pow((totalCalcium - Main.goalValue.getCalcium()), 2)
                    + Math.pow((totalPhosphorus - Main.goalValue.getPhosphorus()), 2) + Math.pow((totalMagnesium - Main.goalValue.getMagnesium()), 2)
                    + Math.pow((totalIron - Main.goalValue.getIron()), 2) + Math.pow((totalZinc - Main.goalValue.getZinc()), 2)
                    + Math.pow((totalCopper - Main.goalValue.getCopper()), 2) + Math.pow((totalSelenium - Main.goalValue.getSelenium()), 2)
                    + Math.pow((totalPotassium - Main.goalValue.getPotassium()), 2) + Math.pow((totalSodium - Main.goalValue.getSodium()), 2)
                    + Math.pow((totalCaffeine - Main.goalValue.getCaffeine()), 2) + Math.pow((totalAlcohol - Main.goalValue.getAlcohol()), 2);
            score = (int) Math.sqrt(repasScore);
        }
        return score;
    }

    // Get number of aliments on our repas
    public int repasSize() {
        return repas.size();
    }

    // Check if the repas contains a aliment
    public boolean containsAliment(Aliment aliment) {
        return repas.contains(aliment);
    }

    @Override
    public String toString() {
        String geneString = "Repas conseillé-->";
        for (int i = 0; i < repasSize(); i++) {
            geneString += " --> " + getAliment(i).getFoodGroup() + " : " + getAliment(i).getDescription();
        }
        return geneString;
    }
}
	

