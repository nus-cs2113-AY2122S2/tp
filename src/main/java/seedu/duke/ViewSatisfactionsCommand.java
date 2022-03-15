package seedu.duke;

import java.util.ArrayList;
public class ViewSatisfactionsCommand extends Command {
    @Override
    public void execute() {

    }

    public void execute(SatisfactionList satisfactionList) {
        int number = 1;
        ArrayList<Satisfaction> satisfactionArrayList = satisfactionList.getSatisfactionList();
        for (Satisfaction satisfaction : satisfactionArrayList) {
            System.out.println(Integer.toString(number) + ". " + satisfaction.getCustomerName() + " - " + satisfaction.getSatisfactionValue());
            number += 1;
        }

    }
}

