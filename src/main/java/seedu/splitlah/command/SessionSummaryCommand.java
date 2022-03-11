package seedu.splitlah.command;

import seedu.splitlah.data.Manager;
import seedu.splitlah.data.Person;
import seedu.splitlah.data.Profile;
import seedu.splitlah.data.Session;
import seedu.splitlah.exceptions.InvalidDataException;
import seedu.splitlah.exceptions.InvalidFormatException;
import seedu.splitlah.parser.Parser;
import seedu.splitlah.ui.Message;
import seedu.splitlah.ui.TextUI;
import seedu.splitlah.util.PersonCostPair;

import java.util.ArrayList;

/**
 * Represents a command object that produces a summary of expenditure for a specified Session object.
 *
 * @author Warren
 */
public class SessionSummaryCommand extends Command {
    
    public static final String COMMAND_TEXT = "session /summary";

    public static final String COMMAND_FORMAT = "Syntax: session /summary /sid <SESSIONID>";
    public static final String PREPEND_SESSION_ID = "\nSession id:   ";
    public static final String PREPEND_SESSION_NAME = "\nSession name: ";
    public static final String PREPEND_SESSION_DATE = "\nSession date: ";
    public static final String PREPEND_PAYMENTS = "\nTransactions to be made:";
    public static final String PREPEND_TRANSACTION = "\n - ";

    private int sessionId;

    // MISC CONSTANTS
    public static final String SUMMARY_HEADER = "Summary --";
    public static final String TEMP_ERROR_INVALID_PERSONCOSTPAIR_LIST =
            "Program has faced some issue : processAllTransactions, personCostPairList is invalid";
    public static final String TEMP_ERROR_PROCESSALLTRANSACTION_METHOD_LOGIC_INVALID =
            "Program has faced some issue : processAllTransactions, payer, receiver logic is invalid";
    private static final int ZERO_INDEXING_OFFSET = 1;
    private static final double SMALL_DIFFERENCE_LIMIT = 0.0001;

    /**
     * Default constructor, sets sessionId as specified by the provided value.
     *
     * @param sessionId The session identifier number that uniquely identifies a session.
     */
    public SessionSummaryCommand(int sessionId) {
        this.sessionId = sessionId;
    }
    
    public static Command prepare(String commandArgs) {
        try {
            int sessionId = Parser.parseSessionId(commandArgs);
            return new SessionSummaryCommand(sessionId);
        } catch (InvalidFormatException exception) {
            String invalidCommandMessage = exception.getMessage() + "\n" + COMMAND_FORMAT;
            return new InvalidCommand(invalidCommandMessage);
        }
    }

    private static boolean isDifferenceSmall(double cost1, double cost2) {
        return Math.abs(cost1 - cost2) <= SMALL_DIFFERENCE_LIMIT;
    }
    
    private static boolean isValueSmall(double cost) {
        return Math.abs(cost) <= SMALL_DIFFERENCE_LIMIT;
    }

    private static ArrayList<PersonCostPair> getPersonCostPairList(ArrayList<Person> personList) {
        ArrayList<PersonCostPair> personCostPairList = new ArrayList<>();
        for (Person person : personList) {
            PersonCostPair newPair = new PersonCostPair(person);
            if (!isValueSmall(newPair.getCost())) {
                personCostPairList.add(newPair);
            }
        }
        return personCostPairList;
    }

    private static boolean isPersonCostPairListValid(ArrayList<PersonCostPair> personCostPairList) {
        double total = 0;
        for (PersonCostPair personCostPair : personCostPairList) {
            total += personCostPair.getCost();
        }
        return isValueSmall(total);
    }
    
    private String processTransaction(PersonCostPair payer, PersonCostPair receiver) {
        double payerCost = Math.abs(payer.getCost());
        double receiverAmount = Math.abs(receiver.getCost());
        
        // Both parties have near 0 cost/debt
        if (isValueSmall(payerCost) && isValueSmall(receiverAmount)) {
            return "";
        }
        
        // Equal costs
        if (isDifferenceSmall(payerCost, receiverAmount)) {
            payer.setProcessed(true);
            receiver.setProcessed(true);
            return payer.getPerson().getName() + " has to pay " + receiver.getPerson().getName()
                    + " $" + String.format("%.2f", payerCost);
        }
        
        // Payer has less debt than receiver has to collect
        if (payerCost < receiverAmount) {
            payer.setProcessed(true);
            receiver.setCost(receiverAmount - payerCost);
            return payer.getPerson().getName() + " has to pay " + receiver.getPerson().getName()
                    + " $" + String.format("%.2f", payerCost);
        }
        
        // Payer has more debt than receiver has to collect
        receiver.setProcessed(true);
        payer.setCost(receiverAmount - payerCost);
        return payer.getPerson().getName() + " has to pay " + receiver.getPerson().getName()
                + " $" + String.format("%.2f", receiverAmount);
    }

    private String processAllTransactions(ArrayList<PersonCostPair> personCostPairList, Session session) {
        StringBuilder sb = new StringBuilder(SUMMARY_HEADER);
        sb.append(PREPEND_SESSION_ID).append(sessionId);
        sb.append(PREPEND_SESSION_NAME).append(session.getSessionName());
        sb.append(PREPEND_SESSION_DATE).append(session.getDateString());
        sb.append(PREPEND_PAYMENTS);
        personCostPairList.sort(PersonCostPair::compareTo);
        int payerIndex = 0;
        int receiverIndex = personCostPairList.size() - ZERO_INDEXING_OFFSET;
        if (!isPersonCostPairListValid(personCostPairList)) {
            return TEMP_ERROR_INVALID_PERSONCOSTPAIR_LIST;
        }

        boolean hasInserted = false;
        while (payerIndex < receiverIndex) {
            PersonCostPair payer = personCostPairList.get(payerIndex);
            PersonCostPair receiver = personCostPairList.get(payerIndex);
            if (payer.getCost() > receiver.getCost()) {
                return TEMP_ERROR_PROCESSALLTRANSACTION_METHOD_LOGIC_INVALID;
            }
            String output = processTransaction(payer, receiver);
            if (!output.isEmpty()) {
                sb.append(PREPEND_TRANSACTION).append(output);
                hasInserted = true;
            }
            
            if (payer.isProcessed()) {
                payerIndex += 1;
            }
            if (receiver.isProcessed()) {
                receiverIndex -= 1;
            }
        }

        if (!hasInserted) {
            sb.append(PREPEND_TRANSACTION).append(Message.MESSAGE_SESSIONSUMMARY_NO_PAYMENTS_REQUIRED);
        }
        return sb.toString();
    }

    /**
     * Runs the command with the session identifier as provided by the user input and prints a
     * summary of expenditure for the session specified by the session identifier.
     *
     * @param manager A Manager object that manages the TextUI and Profile object.
     */
    @Override
    public void run(Manager manager) {
        Profile profile = manager.getProfile();
        TextUI ui = manager.getUi();
        Session session;
        try {
            session = profile.getSession(sessionId);
        } catch (InvalidDataException exception) {
            ui.printlnMessage(exception.getMessage());
            return;
        }

        ArrayList<Person> personList = session.getPersonList();
        ArrayList<PersonCostPair> personCostPairList = getPersonCostPairList(personList);
        // check if NET 0
        String output = processAllTransactions(personCostPairList, session);
        ui.printlnMessage(output);
    }
}
