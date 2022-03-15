package seedu.planitarium.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parseDelimitedTerm_getDelimitedToken_success() {
        String input1 = "";
        String input2 = "add /e bills";
        String input3 = "add /e bills /n 100";
        String input4 = "";
        String delimiter = "/e";
        String delimiterBack = "/n";

        String output1 = Parser.parseDelimitedTerm(input1, delimiter, delimiterBack);
        assertEquals(input1, output1);

        String output2 = Parser.parseDelimitedTerm(input2, delimiter, delimiterBack);
        assertEquals("bills", output2);

    }

    @Test
    void parseKeyword() {
    }

    @Test
    void parseName() {
    }

    @Test
    void parseUserIndex() {
    }

    @Test
    void parseDescription() {
    }

    @Test
    void parseIncome() {
    }

    @Test
    void parseExpenditure() {
    }

    @Test
    void parseRecordIndex() {
    }

    @Test
    void getValidMoney() {
    }

    @Test
    void getValidUserIndex() {
    }

    @Test
    void getValidExpenditureIndex() {
    }

    @Test
    void getValidIncomeIndex() {
    }
}