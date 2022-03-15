package seedu.duke;

import java.util.ArrayList;
public class ViewSatisfactionsCommand extends Command {
    public void execute(SatisfactionList satisfactionList, Ui ui) {
        int number = 1;
        ArrayList<Satisfaction> satisfactionArrayList = satisfactionList.getSatisfactionList();
        for (Satisfaction satisfaction : satisfactionArrayList) {
            System.out.println(Integer.toString(number) + ". " + satisfaction.getCustomerName() + " - " + satisfaction.getSatisfactionValue());
            number += 1;
        }

    }
}

