import Model.DTO_Alcohol;
import Model.DTO_Drinks;
import Model.DTO_Mixer;
import Model.DTO_Type;
import Utilities.Constants;
import Utilities.*;

import java.util.ArrayList;

public class ControlDrinks  {
    private static DTO_Drinks dto_drinks;

    public static void readJson() {
        dto_drinks = new JsonHandler().loadJsonFile();//loading the json file on the constructor
    }

    public static void printDrinksByType() {
        System.out.println(Constants.title1);//Print: DTO_Alcoholic Drinks of each type:
        int item = Constants.one;//This is used to print a itemCount
        for (DTO_Type types : dto_drinks.getTypes()) {
            int i = 0;//We'll save the count here
            //Doing a 'for' viewing every record of the json of DTO_Alcohols Array
            for (DTO_Alcohol alcohol : dto_drinks.getAlcohols()) {
                if (types.getId() == alcohol.getType()) i++;//every time we find a match adding a unit to "i"
            }
            System.out.println(item++ + "." + types.getName() + ": " + i + " Drinks");
        }
        System.out.println();
    }

    public static void printDrinkMixCola() {
        System.out.println(Constants.title2);// Print: Distilled drinks that can be mixed with cola
        DTO_Alcohol[] dto_alcohols = dto_drinks.getAlcohols();
        int item = Constants.one;//This is used to print a itemCount
        for (DTO_Alcohol alcohol : dto_alcohols) {
            float graduation = alcohol.getGraduation();//Getting graduation of each drink
            //To find distilled drinks it's necessary filter each drink by graduation value between 25 and 60
            if (graduation >= Constants.lowGraduation) {//if graduation is more or equals 25
                if (graduation <= Constants.highGraduation) { //And here less or equals than 60
                    for (int combinations : alcohol.getCombinations()) {
                        //if match with cola then print that drink
                        if (combinations == Constants.colaId) System.out.println(item++ + ". " + alcohol.getNom());
                    }
                }
            }
        }
        System.out.println();//print empty line
    }

    public static void printBiggerFounderName() {
        System.out.println(Constants.title3);//Print: Show all the information of the gin drink which has the bigger sum of the size of it's foundersname
        // Setting initial values into variables
        int currentName = Constants.zero;
        String name = "";
        double graduation = Constants.zero;
        String origin = null;
        int year = Constants.zero;
        String type = null;
        String founders = null;
        String mixes = null;

        //For each drink
        for (DTO_Alcohol alcohol : dto_drinks.getAlcohols()) {
            int currentLongName = Constants.zero;
            String founderName = "";
            //if drink is GIN then continue
            if (alcohol.getType() == Constants.GinId) {
                for (DTO_Alcohol.Founder founder : alcohol.getFounders()) {
                    currentLongName += founder.getName().length();//adding length sizes of every founder's names
                    founderName += founder.getName();//Concatenate names of every founders
                }
            }
            if (currentLongName > currentName) { //if name is more large than previous then save it extract fields
                currentName = currentLongName;
                name = alcohol.getNom();
                graduation = alcohol.getGraduation();
                origin = alcohol.getProcedence();
                year = alcohol.getYear();
                type = dto_drinks.getTypeNameById(alcohol.getType());
                founders = founderName;
                mixes = "";
                //Getting mixes of the larger founder's name Gin
                boolean firstDTO_Mixer = true;
                for (int comb : alcohol.getCombinations()) {
                    for (DTO_Mixer mixer : dto_drinks.getMixers()) {

                        //Searching names of mixes
                        if (mixer.getId() == comb) {//if match get value and print
                            if (!firstDTO_Mixer) {
                                mixes = mixes + " , " + mixer.getName();//the second and following mix(es) separated by ','
                            } else {
                                mixes = mixer.getName();//the first mix do not print ','
                                firstDTO_Mixer = false;//this flag indicate that first element was already saved
                            }
                        }
                    }
                }
            }
        }
        //Setting string on output format
        String founderLargestName =
                        "Name: <" + name + ">\n"
                        + "Graduation : <" + graduation + ">\n"
                        + "Origin: <" + origin + ">\n"
                        + "Year: <" + year + ">\n"
                        + "Type: <" + type + ">\n"
                        + "Founders: <" + founders + ">\n"
                        + "Mixes:<" + mixes + ">\n";
        System.out.println(founderLargestName); //Print result
    }

    public static void printRepeatedMix() {
        System.out.println(Constants.title4);//Print: Top 3 of the most repeated combinations (alcohol + mixer)
        ArrayList<Integer> xCombinations = new ArrayList<>();//This array will contain a combination list of id's
        ArrayList<Integer> mixerId = new ArrayList<>();//This array will contain a inventary of combinations from alcohols
        //Setting initial values
        int maxValue = 0;
        int indexOfCombination = 0;
        int indexOfDTO_MixerId = 0;
        for (DTO_Mixer mix : dto_drinks.getMixers()) {
            xCombinations.add(Constants.zero);//All counts are set to zero value
            int idMix = mix.getId();
            mixerId.add(idMix);
        }
        //At each drink we find it combinations and add every one of those
        DTO_Alcohol[] dto_alcohols = dto_drinks.getAlcohols();
        for (DTO_Alcohol alcohol : dto_alcohols) {
            for (int combinations : alcohol.getCombinations()) {
                indexOfCombination = mixerId.indexOf(combinations);
                int acumCombinations = xCombinations.get(indexOfCombination) + 1;
                xCombinations.set(indexOfCombination, acumCombinations);
            }
        }
        int item = Constants.one;
        //At this for we looking for 3 highest values
        for (int j = Constants.zero; j < Constants.maxIteration; j++) {
            int combinationsLength = xCombinations.size();//Getting length of combinations
            //We have to inspect every combination
            for (int i = 0; i < combinationsLength; i++) {
                // On each iteration we compare every combination to maxValue,
                if (xCombinations.get(i) > maxValue) {
                    maxValue = xCombinations.get(i);//The first 3 values saved will be the most repeated
                }
            }
            indexOfDTO_MixerId = xCombinations.indexOf(maxValue);
            int index = mixerId.get(indexOfDTO_MixerId);//index of drink most repeated
            String msj = item+++". "+dto_drinks.getMixerNameById(index);//Name of drink most repeated
            System.out.println(msj);//print drink most repeated
            xCombinations.set(indexOfDTO_MixerId, Constants.zero);//
            maxValue = Constants.zero;
        }
        System.out.println("");//print empty line
    }

    public static void printMixHighestDegree() {
        ArrayList<Double> avgByMixer = new ArrayList<>();//This list is used to save average between alcohol and mix element
        ArrayList<Integer> mixerId = new ArrayList<>(); //This list is used to save the id's
        double max = Constants.zero;
        int indexOfCombination = Constants.zero;
        int indexOfMixerId = Constants.zero;
        //we need to get id's of every mix and
        for (DTO_Mixer mixer : dto_drinks.getMixers()) {
            mixerId.add(mixer.getId()); //set id of every mix at array list
            avgByMixer.add(Constants.zeroD); //set 0 avg list on each record
        }
        System.out.println(Constants.title5);//Print: Show a top 3 of the mixers that get mixed with the drinks that altoghether have the highest degree average.
        //
        for (DTO_Mixer mixer : dto_drinks.getMixers()) {
            int countCombination = Constants.zero;//this store how many combinatios have the mix
            double average = Constants.zero;//Contaings average of graduation level of a combination
            double grades = Constants.zero;//Containing a sum o graduation of a combination
            for (DTO_Alcohol alcohol : dto_drinks.getAlcohols()) {
                for (int combinations : alcohol.getCombinations()) {
                    if (combinations == mixer.getId()) {
                        countCombination++; //if we find a combination matching then add one more to the counting
                        grades = grades + alcohol.getGraduation();
                    }
                }
            }
            average = grades / countCombination;//Calculating average
            //Searching index and set average on list
            indexOfCombination = mixerId.indexOf(mixer.getId());
            avgByMixer.set(indexOfCombination, average);//Saving average into the list
        }

        //At the end we need to check what is the three highest combination we do a final Nested Loop
        int item = Constants.one;
        for (int j = Constants.zero; j < Constants.maxIteration; j++) {
            int avgByMixerLength = avgByMixer.size();

            for (int i = Constants.zero; i < avgByMixerLength; i++) {
                //if the average is more than max then set max with this value of average mix
                if (avgByMixer.get(i) > max) {
                    max = avgByMixer.get(i);
                }
            }
            indexOfMixerId = avgByMixer.indexOf(max);//getting index of 'max' save in 'indexOfMixerId'
            String MixHighestDegree =item+++". "+dto_drinks.getMixerNameById(mixerId.get(indexOfMixerId));//Getting name of mix by index
            System.out.println(MixHighestDegree);//Print result
            avgByMixer.set(indexOfMixerId, Constants.zeroD);
            max =Constants.zero;//reset max to zero
        }
        System.out.println();//print empty line
    }

    public static void printGoodBye() {
        System.out.println(Constants.title6);//Print bye
    }


}
