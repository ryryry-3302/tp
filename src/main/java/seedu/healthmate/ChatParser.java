package seedu.healthmate;

import seedu.healthmate.command.commands.LogMealsCommand;
import seedu.healthmate.command.commands.SaveMealCommand;
import seedu.healthmate.command.commands.ListCommandsCommand;
import seedu.healthmate.command.commands.AddMealEntryCommand;
import seedu.healthmate.command.commands.DeleteMealCommand;
import seedu.healthmate.command.commands.DeleteMealEntryCommand;
import seedu.healthmate.command.commands.MealMenuCommand;
import seedu.healthmate.command.commands.SetHealthGoalCommand;

import java.util.Scanner;



/**
 * Encapsulates the main logic of the application by parsing user input into objects
 * and storing them respectively.
 */
public class ChatParser {

    public static final String CALORIE_SIGNALLER = "/c";

    private MealEntriesList mealEntries;
    private MealList mealOptions;
    private final HistoryTracker historyTracker;

    public ChatParser(){
        this.historyTracker = new HistoryTracker();
        UI.printSeparator();
        this.mealEntries = historyTracker.loadMealEntries();
        this.mealOptions = historyTracker.loadMealOptions();
        UI.printSeparator();
    }

    /**
     * Reads in user input from the command line
     * and initiates the parsing process steered by one-token and two-token-based user prompts.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine().strip();
            switch (userInput) {
            case "bye":
                UI.printFarewell();
                break;
            default:
                try {
                    this.multiCommandParsing(userInput);
                } catch (ArrayIndexOutOfBoundsException a) {
                    UI.printReply("Invalid command", "Retry: ");
                }
            }
        }
    }

    /**
     * Steers the activation of features offered to the user via two-token commands
     * @param userInput String user input from the command line
     */
    public void multiCommandParsing(String userInput) {
        String[] inputTokens = userInput.split(" ");
        String commandToken1 = inputTokens[0].strip();
        String commandToken2 = inputTokens[1].strip();
        String command = commandToken1 + " " + commandToken2;
        switch (command) {
        case MealMenuCommand.COMMAND:
            UI.printMealOptions(this.mealOptions);
            break;
        case SaveMealCommand.COMMAND:
            mealOptions.appendMealFromString(userInput, command, mealOptions);
            historyTracker.saveMealOptions(mealOptions);
            break;
        case DeleteMealCommand.COMMAND:
            mealOptions.removeMealFromString(userInput, command);
            historyTracker.saveMealOptions(mealOptions);
            break;
        case DeleteMealEntryCommand.COMMAND:
            mealEntries.removeMealFromString(userInput, command);
            historyTracker.saveMealEntries(mealEntries);
            break;
        case AddMealEntryCommand.COMMAND:
            mealEntries.appendMealFromString(userInput, command, mealOptions);
            historyTracker.saveMealEntries(mealEntries);
            break;
        case LogMealsCommand.COMMAND:
            UI.printMealEntries(this.mealEntries);
            break;
        case ListCommandsCommand.COMMAND:
            UI.printCommands();
            break;
        case SetHealthGoalCommand.COMMAND:
            UI.printReply("Work in Progress", "Setting HealthGoal");
            break;
        default:
            UI.printReply("Use a valid command", "Retry: ");
            break;
        }
    }

    public String toMealOptionsStringWithNew(String newMealString) {
        return UI.toMealOptionsString(this.mealOptions, newMealString);
    }

    public void cleanListsAfterTesting() {
        this.mealEntries = this.historyTracker.loadEmptyMealEntries();
        this.mealOptions = this.historyTracker.loadEmptyMealOptions();
        historyTracker.saveMealOptions(mealOptions);
        historyTracker.saveMealEntries(mealEntries);
    }

}
