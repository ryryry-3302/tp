package seedu.healthmate;

public class UI {
    
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SEPARATOR = "_________________________________________________________________________";
    private static final String INTENDATION = "      ";
    private static final String LINE = INTENDATION + SEPARATOR;
    private static final String FRAME_LINE = LINE + LINE_SEPARATOR;
    private static final String LOGO =
              INTENDATION + " |\n"
            + INTENDATION + "     \\\\|//\n"
            + INTENDATION + "     \\\\|//\n"
            + INTENDATION + "    \\\\\\|///\n"
            + INTENDATION + "    \\\\\\|///\n"
            + INTENDATION + "     \\\\|//\n"
            + INTENDATION + "      \\|/\n"
            + INTENDATION + "       |\n";


    public static void printReply(String input, String actionPerformed) {
        System.out.println(LINE);
        System.out.println(INTENDATION + actionPerformed + input);
        System.out.println(LINE);
    }

    public static void printGreeting() {
        System.out.println(INTENDATION + LOGO);
        System.out.println(LINE);
        System.out.println(INTENDATION + "Welcome to HealthMate");
        System.out.println(INTENDATION + "Let's get healthy!");
        System.out.println(LINE);
    }

    public static void printFarewell() {
        System.out.println(INTENDATION + "Stay healthy!");
        System.out.println(LINE);
    }

    public static void printString(String input) {
        System.out.println(INTENDATION + input);
    }

    public static void printSeparator() {
        System.out.println(LINE);
    }

    public static void printMealOptions(MealList mealOptions) {
        printSeparator();
        if (mealOptions.size() > 0) {
            for (int i = 0; i < mealOptions.size(); i++) {
                System.out.println(INTENDATION + (i + 1)
                        + ": " + mealOptions.toMealString(i));
            }
        } else {
            printReply("No meal options added yet", "");
        }
        printSeparator();
    }

    public static void printMealEntries(MealEntriesList mealEntries) {
        printSeparator();
        if (mealEntries.size() > 0) {
            for (int i = 0; i < mealEntries.size(); i++) {
                System.out.println(INTENDATION + (i + 1)
                        + ": " + mealEntries.toMealString(i));
            }
        } else {
            printReply("No meal entries added yet", "");
        }
        printSeparator();
    }

    public static void printCommands() {
        System.out.println(LINE);
        for (String command: Commands.getAllCommands()){
            System.out.println(INTENDATION + "-" + command);
        }
        System.out.println(LINE);

    }

    public static String simulateReply(String input, String actionPerformed) {
        String line1 = LINE_SEPARATOR;
        String line2 = INTENDATION + actionPerformed + input + LINE_SEPARATOR;
        return  line1 + line2 + FRAME_LINE;
    }

    public static String simulateFareWell() {
        String line1 = INTENDATION + "Stay healthy!" + LINE_SEPARATOR;;
        return line1 + FRAME_LINE;
    }

    public static String simulateInitOutput() {
        String line2 = INTENDATION + "Meal Entries Loaded Successfully!" + LINE_SEPARATOR;
        String line3 = INTENDATION + "Meal Options Loaded Successfully!" + LINE_SEPARATOR;
        return FRAME_LINE + line2 + line3 + FRAME_LINE + LINE;

    }

    /**
     * Ouputs the result of list meals as a String if a newMealString would be added at the end
     * @param mealOptions
     * @param newMealString
     * @return
     */
    public static String toMealOptionsString(MealList mealOptions, String newMealString) {
        String mealOptionsString = "";
        for (int i = 0; i < mealOptions.size(); i++) {
            mealOptionsString += INTENDATION + (i + 1) + ": " + mealOptions.toMealString(i) + LINE_SEPARATOR;
        }
        mealOptionsString += INTENDATION + (mealOptions.size() + 1) + ": " + newMealString + LINE_SEPARATOR;
        return LINE + LINE_SEPARATOR + mealOptionsString + LINE + LINE_SEPARATOR;
    }

}
