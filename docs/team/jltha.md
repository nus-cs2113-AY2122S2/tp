# Tha Jun Lim - Project Portfolio Page

## Overview

Sherpass is a command line interface desktop application to help students manage their academic workloads
by preparing them a timetable to plan their activities, as well as encouraging them to 
complete the activities with the use of the study session.

- [Sherpass Github](https://github.com/AY2122S2-CS2113T-T09-1/tp)
- [Sherpass User Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/UserGuide.html)
- [Sherpass Developer Guide](https://ay2122s2-cs2113t-t09-1.github.io/tp/DeveloperGuide.html)
### Summary of Contributions

- Code contributed: [RepoSense link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=jltha&tabRepo=AY2122S2-CS2113T-T09-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


- New Features:

  - Timetable and task listing generation
    - What it does: 
      1. Allows the user to view a timetable for the current day/week.
      User is also able to view the timetable for a specific date of his/her choosing.
      Timetable for the day is always shown when the user starts the application.
      2. Allows the user to view all the pending tasks/tasks that have been added
      in a condensed version (without the timetable formatting).
    - Justification: This feature allows the user to view their schedule and any related tasks
    which requires their attention, allowing them to take action promptly.
    - Highlights: This feature affects existing commands (edit/delete) which requires a 
    task number in order to manipulate the task content, or remove that task.
    
  - Study session (Parsing user inputs and UI)
    - What it does:
      1. Allows the user to enter a study session and complete any tasks that are due.
      2. Allows the user to also select different extra features within the study session, e.g.
      choosing different timer modes, as well as marking and listing tasks.
      3. Shows a pop-up window of the timer once the user has started a timer of his/her choosing.
    - Justification:
      1. The different modes which are parsed from the user input allows the variety of choices, since the 
      study session aims to provide users the tools to maximise his/her productivity in completing any tasks at hand.
      2. The extra pop-up window provides easily accessible timer functions for when the user has started the timer. 
      This removes the need for typing out command words as inputs, which is convenient for when the user is doing 
      time-sensitive tasks. Such a feature resolves the problem of the command not being registered properly as the 
      elapsed time and time left are being printed in the terminal.
    - Credits: The following imports were used to create the pop-up window (GUI) for the timer:
      - javax.swing.JFrame;
      - javax.swing.JLabel;
      - javax.swing.JToggleButton;
      - javax.swing.SwingConstants;
      - java.awt.BorderLayout;
      - java.awt.event.ActionListener;
      - java.awt.event.WindowEvent;
      - java.awt.event.WindowListener;
    

- Enhancements to existing features:

  - Merged the code for add command, edit command, and delete command to incorporate
  both adding/editing/deleting of recurring and non-recurring tasks in the codebase
  (pull request [#83](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/83)).
  - Fixed the parsing of user inputs to be more accurate and flexible 
  (pull request [#83](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/83)).
  - Enabled the feature of checking of date and time clashes 
  (pull requests [#83](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/83) and
  - [#137](https://github.com/AY2122S2-CS2113T-T09-1/tp/pull/137)).
  - Clean up the code in various occasions.
  

- Project management:
    - Managed releases `v1.0` - `v2.0` (2 releases) on GitHub.
    - Maintained issue tracker for team repo.
    

- Documentation
    - User Guide:
      - Added documentation for features `study session`, `timetable` and `mark/unmark`.
      - Did some tweaks to existing features in the document `add`, `delete`
    - Developer Guide:
      - Added implementation details of the `timetable` feature


- Community:
    - Reviewed PRs (with non-trivial review comments) for almost all the team's PR.
    - Contributed to forum discussion (forum issue [#57](https://github.com/nus-cs2113-AY2122S2/forum/issues/57))
    - Some areas of the comments were adopted by teammates



