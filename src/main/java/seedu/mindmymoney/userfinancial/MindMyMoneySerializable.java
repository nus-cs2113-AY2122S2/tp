package seedu.mindmymoney.userfinancial;

/**
 * Interface for serializable objects.
 * Note that this is different from java.io.Serializable,
 * as classes implementing this interface should ensure that their
 * serializations are human-readable.
 */
public interface MindMyMoneySerializable {
    String serialize();
}
