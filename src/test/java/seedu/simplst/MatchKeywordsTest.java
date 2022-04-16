package seedu.simplst;

import org.junit.jupiter.api.Test;
import util.exceptions.EmptyFieldException;
import util.exceptions.MissingFlagException;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchKeywordsTest {
    /*
    @Test
    void getGroupValues() throws MissingFlagException, EmptyFieldException {
        String regex = "oid/(?<oid>\\d*) r/(?<recv>.*) addr/(?<addr>.*)";
        String test1 = "o/ oid/12 r/receiver a/address";
        MatchKeywords matchKeywords1 = new MatchKeywords(test1, regex);
        HashMap<String, String> matches = matchKeywords1.getGroupValues();


        //test 1 successful matching of values
        assertEquals(4, matches.size());
        assertEquals("o", matches.get("flag"));
        assertEquals("12", matches.get("oid"));
        assertEquals("receiver", matches.get("recv"));
        assertEquals("address", matches.get("addr"));


        String regex2 = "(?<flag>[og])/ oid/(?<oid>\\d*)";
        String test2 = "add g/ oid/-12 gid/-156 r/receiver a/address n/name q/-100 d/description";
        MatchKeywords matchKeywords2 = new MatchKeywords(test2, regex2);
        HashMap<String, String> matches2 = matchKeywords2.getGroupValues();

        //test 2 successful detection and avoiding of negative numbers
        //does not match illegal userInput
        assertEquals(2, matches2.size());
        assertEquals("g", matches2.get("flag"));
        assertEquals("", matches2.get("oid"));
    }
    */
}
