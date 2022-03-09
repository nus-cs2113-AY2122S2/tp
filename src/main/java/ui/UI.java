package ui;

/**
 * print messages with horizontal line
 */
public class UI {
    protected static final String HORI_LINE = "---------------------------";
    protected String content;

    public UI() {
    }

    public void printMsg(String message) {
        System.out.println(HORI_LINE);
        System.out.println(message);
        System.out.println(HORI_LINE);
    }
}
