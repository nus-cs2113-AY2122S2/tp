package UI;

/**
 * print messages with horizontal line
 */
public class UI {
    protected static final String HORI_LINE = "---------------------------";
    protected String content;

    public UI() {
    }

    public void printMsg(String message) {
        this.content = message;
        System.out.println(content);
        System.out.println(HORI_LINE);
    }
}
