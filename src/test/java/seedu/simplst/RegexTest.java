package seedu.simplst;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexTest {

    @Test
    void getGroupValues() {
        String regex = "(?<flag>[og])/ oid/(?<oid>\\d*) gid/(?<gid>\\d*) r/(?<r>.*) a/(?<address>.*)"
                + " n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>.*)";
        String test1 = "add o/ oid/12 gid/156 r/receiver a/address n/name q/100 d/description";
        Regex regex1 = new Regex(test1, regex);
        HashMap<String, String> matches = regex1.getGroupValues();

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

        String test2 = "add g/ oid/-12 gid/-156 r/receiver a/address n/name q/-100 d/description";
        Regex regex2 = new Regex(test2, regex);
        HashMap<String, String> matches2 = regex2.getGroupValues();

        //test 2 successful detection and avoiding of negative numbers
        //does not match illegal userInput
        assertEquals(8, matches2.size());
        assertEquals("", matches2.get("flag"));
        assertEquals("", matches2.get("oid"));
        assertEquals("", matches2.get("gid"));
        assertEquals("", matches2.get("r"));
        assertEquals("", matches2.get("address"));
        assertEquals("", matches2.get("name"));
        assertEquals("", matches2.get("qty"));
        assertEquals("", matches2.get("desc"));
    }
}
