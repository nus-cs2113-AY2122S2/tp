package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

import parser.Parser;

class ParserTest {

    Parser parser;

    @BeforeEach
    void setup() {
        parser = new Parser();
    }

    @Test
    void parseCommand_validCommand_splitArrayList() {
        ArrayList<String> desiredResult = new ArrayList<>(
                Arrays.asList("add", "n/ITEM_NAME sn/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE")
        );
        ArrayList<String> actualResult = parser.parseCommand("add n/ITEM_NAME sn/SERIAL_NUMBER t/TYPE c/COST pf/PURCHASED_FROM pd/PURCHASED_DATE");
        assertEquals(desiredResult, actualResult);
        assertEquals(desiredResult.get(0), actualResult.get(0));
        assertEquals(desiredResult.get(1), actualResult.get(1));
    }
}