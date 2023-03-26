import Utilities.Constants;
import Utilities.KeyReader;

public class Main {


    public static void main(String[] args) {

        ControlDrinks controlDrinks = new ControlDrinks();//Class Control
        Constants constants = new Constants();//Class with constants and static text
        controlDrinks.readJson();//load json data into Data Transfer Object (DTO) Class
        while (true) {
            System.out.println(constants.menuPrincipal);// Print the Main Menu
            int userChoice = constants.zero;// This variable is the user choice
            while (true) {
                userChoice = new KeyReader().readKey();//reading from keyboard

                //Validating userChoice Between 1 and 6; else keep trying to catch a correct value from user pressed key
                if (userChoice > 0 && userChoice < 7) {
                    // if the choice is in the range then exit loop
                    break;
                } else {
                    System.out.println(constants.msError);//Value not in range
                }

            }
            switch (userChoice) { //Solving the selected choice the program will take just one path
                case 1:
                    controlDrinks.printDrinksByType();
                    break;
                case 2:
                    controlDrinks.printDrinkMixCola();
                    break;
                case 3:
                    controlDrinks.printBiggerFounderName();
                    break;
                case 4:
                    controlDrinks.printRepeatedMix();
                    break;
                case 5:
                    controlDrinks.printMixHighestDegree();
                    break;
                case 6:
                    controlDrinks.printGoodBye();
                    System.exit(Constants.zero);
                    break;
            }
        }
    }
}
