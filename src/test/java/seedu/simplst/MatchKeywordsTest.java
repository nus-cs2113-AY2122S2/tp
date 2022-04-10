package seedu.simplst;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchKeywordsTest {

    @Test
    void getGroupValues() {
        String regex = "(?<flag>[og])/ oid/(?<oid>\\d*) gid/(?<gid>\\d*) r/(?<r>.*) a/(?<address>.*)"
                + " n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>.*)";
        String test1 = "add o/ oid/12 gid/156 r/receiver a/address n/name q/100 d/description";
        MatchKeywords matchKeywords1 = new MatchKeywords(test1, regex);
        HashMap<String, String> matches = matchKeywords1.getGroupValues();

        //test 1 successful matching of values
        assertEquals(8, matches.size());
        assertEquals("o", matches.get("flag"));
        assertEquals("12", matches.get("oid"));
        assertEquals("156", matches.get("gid"));
        assertEquals("receiver", matches.get("r"));
        assertEquals("address", matches.get("address"));
        assertEquals("name", matches.get("name"));
        assertEquals("100", matches.get("qty"));
        assertEquals("description", matches.get("desc"));

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
}
