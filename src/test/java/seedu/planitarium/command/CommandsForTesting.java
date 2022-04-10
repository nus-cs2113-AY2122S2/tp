package seedu.planitarium.command;

public class CommandsForTesting {
    public static final String INVALIDCMD = "invalidcmd";

    public static final String ADDPERSON = "add /n bob /g 1";
    public static final String ADDPERSON4 = "add /n /g 3";
    public static final String ADDPERSON5 = "add /n alice /g 4";
    public static final String ADDPERSON6 = "add /n alice /g";

    public static final String DELETEPERSON = "delete /u 1 /g 1";

    public static final String ADDINCOME = "addin /u 1 /g 1 /i 999.99 /d salary /p f ";
    public static final String ADDINCOME2 = "addin /u 2 /g 1 /i 999.99 /d salary /p f ";
    public static final String ADDINCOME3 = "addin /u 1 /g 1 /i  /d salary /p f ";
    public static final String ADDINCOME4 = "addin /u 1 /g 1 /i -999.99 /d salary /p f ";

    public static final String ADDEXPEND = "addout /u 1 /g 1 /e 1999.99 /d spend /c 1 /p f ";
    public static final String ADDEXPEND2 = "addout /u 1 /g 1 /e -1999.99 /d spend /c 1 /p f ";
    public static final String ADDEXPEND3 = "addout /u 1 /g 1 /e 1999.99 /d spend /c -1 /p f ";

    public static final String DELETEINCOME = "deletein /u 1 /g 1 /r 1";
    public static final String DELETEEXPEND = "deleteout /u 1 /g 1 /r 1";

    public static final String EDITINCOME = "editin /u 1 /g 1 /r 1 /i 2000 /d income /p t ";
    public static final String EDITEXPEND = "editout /u 1 /g 1 /r 1 /e 999.99 /d spent /c 3 /p f ";

    public static final String LISTBYGROUP = "list /g 1";

    public static final String LISTCAT = "listcat";

    public static final String FIND = "find /d s /c 1";
    public static final String FIND1 = "find";

    public static final String EXIT = "bye";

    public static final String OVERVIEW = "overview";

    public static final String HELP = "help";

}
