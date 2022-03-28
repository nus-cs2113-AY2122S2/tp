package commands;

import records.*;
import java.util.*;
import manager.ExpenseManager;


public class SummaryCommand extends Command {
    /** Keyword to trigger summary command. */
    public static final String COMMAND_WORD = "summary";

    /** Help message for summary command. */
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the summary table based on their type."
            + "\nExample: " + COMMAND_WORD;

    /**
     * Executes the command and returns the result.
     *
     * @return Result of the command
     */
    @Override
    public CommandResult execute() {
        List<Record> allRecords = recordMgr.getAllRecords();
        List<String> types=Arrays.asList("fashion", "food", "accessory","others");
        for (String type:types) {
            int flag=0;
            for (Record r:allRecords){
                if(flag==0) {
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.printf("%20s %20s %13s %13s", "Type: " + type, "Name", "Price", "Date");
                    System.out.println();
                    System.out.println("-------------------------------------------------------------------------------------");
                }
                flag=1;
                if (r instanceof Product) {
                    Product p = (Product) r;
                    if (p.getProductType().equals(type)) {
                        switch(type){
                            case "fashion":
                                ExpenseManager.addToFashionExpense(p.getPrice());
                                break;
                            case "food":
                                ExpenseManager.addToFoodExpense(p.getPrice());
                                break;
                            case "accessory":
                                ExpenseManager.addToAccessoryExpense((p.getPrice()));
                                break;
                            case "others":
                                ExpenseManager.addToOtherExpense(p.getPrice());
                                break;
                        }
                        System.out.printf("%20s %20s %13s %13s", " ", p.getName(), p.getPrice(), p.getDate());
                        System.out.println();
                    }
                }
            }
            System.out.println("-------------------------------------------------------------------------------------");
            switch(type){
                case "fashion":
                    System.out.printf("%66s %13s", "Total: ",ExpenseManager.getFashionExpense());
                    break;
                case "food":
                    System.out.printf("%66s %13s", "Total: ",ExpenseManager.getFoodExpense());
                    break;
                case "accessory":
                    System.out.printf("%66s %13s", "Total: ",ExpenseManager.getAccessoryExpense());
                    break;
                case "others":
                    System.out.printf("%66s %13s", "Total: ",ExpenseManager.getOtherExpense());
                    break;
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%20s %20s %13s %13s %13s", "Type: subscription", "Name", "Price", "Date", "Renewal");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        for(Record record:allRecords){
            if (record instanceof Subscription) {
                Subscription s = (Subscription) record;
                System.out.printf("%20s %20s %13s %13s %13s", " ", s.getName(),
                        s.getPrice(), s.getDate(), s.getRenewal());
                System.out.println();
                ExpenseManager.addToSubscriptionExpense(s.getPrice());
            }
        }
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%66s %13s", "Total: ",ExpenseManager.getSubscriptionExpense());
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("%66s %13s", "Overall Total: ",ExpenseManager.calculateTotalExpense());
        System.out.println();
        String newTotalExpense = "The summary table is shown above.";
        ExpenseManager.resetExpenses();
        return new CommandResult(newTotalExpense);
    }
}
