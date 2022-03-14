package seedu.duke.ui;

import static seedu.duke.common.Messages.MESSAGE_DIVIDER;
import static seedu.duke.common.Messages.LOGO;
import static seedu.duke.common.Messages.MESSAGE_WELCOME;
import static seedu.duke.common.Messages.MESSAGE_QUESTION_NAME;
import static seedu.duke.common.Messages.MESSAGE_HINT;
import static seedu.duke.common.Messages.MESSAGE_GOODBYE;

/**
 * Text UI of the application.
 */
public class TextUi {
    public static void showWelcomeMessage() {
        System.out.println(LOGO);
        System.out.println(MESSAGE_WELCOME);
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(MESSAGE_QUESTION_NAME);
        System.out.println(MESSAGE_DIVIDER);
    }

    public static void showHelpHint() {
        System.out.println(MESSAGE_HINT);
    }

    public static void showGoodByeMessage() {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println(MESSAGE_GOODBYE);
    }
}
