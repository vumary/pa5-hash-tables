
/**
 * Name: Mary Vu
 * Email: m2vu@ucsd.edu
 * Sources used: None
 * 
 * This file contains one class, Sanctuary, and is made for the wildlife 
 * sanctuary that needs my help. Also, this file imports and uses HashMap 
 * from Java's built in library.
 */
import java.util.HashMap;
import java.util.Set;

/**
 * This is my implementation of methods and instance variables that help
 * efficiently track the animals that are in the wildlife sanctuary's care.
 * This class has instance variables (sanctuary, maxAnimal, and maxSpecies)
 * that keep track of how many of each species are currently located on its
 * grounds.
 */
public class Sanctuary {

    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Constructor that initializes the HashMap with no element and initializes
     * the other instance variables accordingly
     * 
     * @param maxAnimals maximum num of animals that the sanctuary can support
     * @param maxSpecies maximum num of species that the sanctuary can support
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
        sanctuary = new HashMap<String, Integer>();
    }

    /**
     * Return the number of animals at the sanctuary that are of the given
     * species
     * 
     * @param species the species in question
     * @return the number of animals of the species in the sanctuary
     */
    public int getNum(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        if (!sanctuary.containsKey(species)) {
            return 0;
        }
        return sanctuary.get(species);
    }

    /**
     * Get the total number of animals at the sanctuary
     * 
     * @return number of animals in sanctuary
     */
    public int getTotalAnimals() {
        Integer totalAnimals = 0;
        for (Integer animals : sanctuary.values()) {
            totalAnimals += animals;
        }
        return (int) totalAnimals;
    }

    /**
     * Get the total number of species at the sanctuary
     * 
     * @return number of species in sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * If it does not exceed the maxAnimals nor maxSpecies of the sanctuary,
     * add num animals of species to the sanctuary. If adding num animals
     * exceeds a maximum limit, add as many animals as permitted. Return the
     * number of animals that could not be rescued.
     * 
     * @param species the species in question
     * @param num the number of animals in question to be added to sanctuary
     * @return the number of animals not rescued
     */
    public int rescue(String species, int num) {
        if (num <= 0 || species == null) {
            throw new IllegalArgumentException();
        }
        // calculate the number of spots left for animals
        int spaceLeft = maxAnimals - this.getTotalAnimals();

        // calculate current number of that species in the sanctuary
        int currNum = 0;
        if (sanctuary.containsKey(species)) {
            currNum = sanctuary.get(species);
        }

        // if it exceeds the max species -OR- if there is already max animals
        // in the sanctuary, return num since none can be rescued
        if ((!sanctuary.containsKey(species)
                && sanctuary.size() == maxSpecies)
                || spaceLeft == 0) {
            return num;
        }

        if (num > spaceLeft) {
            sanctuary.put(species, currNum + spaceLeft);
            return num - spaceLeft;
        }

        sanctuary.put(species, num);
        return 0;
    }

    /**
     * Remove num animals of species from the sanctuary. Remove the species
     * from the sanctuary if its remaining count reaches 0.
     * 
     * @param species the species in question
     * @param num the number of animals to be removed
     */
    public void release(String species, int num) {
        if (num <= 0 || num > sanctuary.get(species) || species == null ||
                !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        sanctuary.put(species, sanctuary.get(species) - num);

        // remove species from sanctuary if there are zero animals left of
        // that species
        if (sanctuary.get(species) == 0) {
            sanctuary.remove(species);
        }
    }
}
