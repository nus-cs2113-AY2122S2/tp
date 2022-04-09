package seedu.duke.stubs;

import seedu.duke.data.BorrowRecord;
import seedu.duke.data.BorrowStatus;

import java.time.LocalDate;

public class BorrowRecordStubs {

    public static final LocalDate TODAY = LocalDate.now();
    public static final int BORROW_QUANTITY = 1;

    public static final LocalDate FUTURE_ONE_DAY = TODAY.plusDays(1);
    public static final LocalDate FUTURE_THREE_DAYS = TODAY.plusDays(3);
    public static final LocalDate FUTURE_FIVE_DAYS = TODAY.plusDays(5);
    public static final LocalDate FUTURE_SEVEN_DAYS = TODAY.plusDays(7);

    public static final LocalDate PAST_ONE_DAY = TODAY.minusDays(1);
    public static final LocalDate PAST_THREE_DAYS = TODAY.minusDays(3);
    public static final LocalDate PAST_FIVE_DAYS = TODAY.minusDays(5);
    public static final LocalDate PAST_SEVEN_DAYS = TODAY.minusDays(7);

    public static final LocalDate PRESENTRECORD_A_STARTDATE = TODAY;
    public static final LocalDate PRESENTRECORD_A_ENDDATE = FUTURE_ONE_DAY;
    public static final String PRESENTRECORD_A_NAME = "Jasper";
    public static final BorrowRecord PRESENTRECORD_A = new BorrowRecord(BORROW_QUANTITY,
            PRESENTRECORD_A_STARTDATE,
            PRESENTRECORD_A_ENDDATE,
            PRESENTRECORD_A_NAME);

    public static final LocalDate PRESENTRECORD_B_STARTDATE = TODAY;
    public static final LocalDate PRESENTRECORD_B_ENDDATE = FUTURE_THREE_DAYS;
    public static final String PRESENTRECORD_B_NAME = "Ethan";
    public static final BorrowRecord PRESENTRECORD_B = new BorrowRecord(BORROW_QUANTITY,
            PRESENTRECORD_B_STARTDATE,
            PRESENTRECORD_B_ENDDATE,
            PRESENTRECORD_B_NAME);

    public static final LocalDate FUTURERECORD_A_STARTDATE = FUTURE_ONE_DAY;
    public static final LocalDate FUTURERECORD_A_ENDDATE = FUTURE_FIVE_DAYS;
    public static final String FUTURERECORD_A_NAME = "Lester";
    public static final BorrowRecord FUTURERECORD_A = new BorrowRecord(BORROW_QUANTITY,
            FUTURERECORD_A_STARTDATE,
            FUTURERECORD_A_ENDDATE,
            FUTURERECORD_A_NAME);

    public static final LocalDate FUTURERECORD_B_STARTDATE = FUTURE_THREE_DAYS;
    public static final LocalDate FUTURERECORD_B_ENDDATE = FUTURE_SEVEN_DAYS;
    public static final String FUTURERECORD_B_NAME = "Chih";
    public static final BorrowRecord FUTURERECORD_B = new BorrowRecord(BORROW_QUANTITY,
            FUTURERECORD_B_STARTDATE,
            FUTURERECORD_B_ENDDATE,
            FUTURERECORD_B_NAME);

    public static final LocalDate PASTRECORD_A_STARTDATE = PAST_THREE_DAYS;
    public static final LocalDate PASTRECORD_A_ENDDATE = PAST_ONE_DAY;
    public static final String PASTRECORD_A_NAME = "Jasper";
    public static final BorrowRecord PASTRECORD_A = new BorrowRecord(BORROW_QUANTITY,
            PASTRECORD_A_STARTDATE,
            PASTRECORD_A_ENDDATE,
            PASTRECORD_A_NAME);

    public static final LocalDate PASTRECORD_B_STARTDATE = PAST_SEVEN_DAYS;
    public static final LocalDate PASTRECORD_B_ENDDATE = PAST_FIVE_DAYS;
    public static final String PASTRECORD_B_NAME = "Chih";
    public static final BorrowRecord PASTRECORD_B = new BorrowRecord(BORROW_QUANTITY,
            PASTRECORD_B_STARTDATE,
            PASTRECORD_B_ENDDATE,
            PASTRECORD_B_NAME);

}
