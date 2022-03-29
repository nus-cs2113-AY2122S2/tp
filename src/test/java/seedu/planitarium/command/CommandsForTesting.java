package seedu.planitarium.command;

public class CommandsForTesting {
    public static final String INVALIDCMD = "invalidcmd";

    public static final String ADDPERSON = "add /n bob /g 1";
    public static final String ADDPERSON1 = "add /n alice /g 0";
    public static final String ADDPERSON2 = "add /n carol /g 2";
    public static final String ADDPERSON3 = "add /n dave /g 3";
    public static final String ADDPERSON4 = "add /n /g 3";
    public static final String ADDPERSON5 = "add /n alice /g 4";
    public static final String ADDPERSON6 = "add /n alice /g";

    public static final String DELETEPERSON = "delete /u 1 /g 1";
    public static final String ADDINCOME = "addin /u 1 /g 1 /i 999.99 /d salary /p f ";
    public static final String DELETEINCOME = "deletein /u 1 /g 1 /r 1";
    public static final String EDITINCOME = "editin /u 1 /g 1 /r 1 /i 2000 /d income /p t ";
    public static final String ADDEXPEND = "addout /u 1 /g 1 /i 1999.99 /d spend /c 1 /p f ";
    public static final String DE
    public static final String
    public static final String


        addout /u__ /g __ /e __ /d __ /c __ /p __
        deleteout /u __ /g __ /r __
        editout /u __ /g __ /r __ /e __ /d __ /c __  /p __
        overview
    list /g __
        find /d __ /c __
        listcat
    bye
        help
}
