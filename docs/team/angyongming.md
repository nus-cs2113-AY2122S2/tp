# Ang Yong Ming - Project Portfolio Page

## Overview
**Project: MeetingJio**

MeetingJio is a program written in Java for finding potential time slots for team meetings
based on everyone’s availability via a Command Line Interface (CLI). If you can type fast,
MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Summary of Contributions
### Code Contributed

**List Command**
- Wrote the code to list out all events in the timetable
- Added exception handling, error messages, JavaDoc and JUnit testing for List Command

**Free Command**
- Wrote a searching algorithm to find common free timeslots for everyone to meet
- Added exception handling, error messages, JavaDoc and JUnit testing for Free Command

**Parser for Add Lesson**
- Wrote the functions in ParserHelperMethods to execute input validation for Parser's prepareAddLesson
- Added exception handling, error messages, JavaDoc and JUnit testing for Parser's prepareAddLesson and 
ParserHelperMethods
- Added JUnit testing for Add Lesson Command

### Enhancements Implemented

**List Command**
- Extended `list` to `list_lesson` and `list_meeting` to allow users to specify what type of event to list out
- Extended `list` to `list all` and `list NAME` to allow users to specify whose timetable to list out
- Wrote a sorting algorithm to ensure the events listed out are sorted from earliest to latest

**Free Command**
- Extended `free` to `free DURATION` to allow users to specify the minimum duration required for everyone to be free 

### Contributions to UG

**List Command**
- Wrote the User Guide's sections on List Command and its extensions

**Free Command**
- Wrote the User Guide's section on Free Command

### Contributions to DG

**List Command**
- Wrote the Developer Guide's sections on List Command and its extensions
- Designed the UML diagram for the `list all` command

**Free Command**
- Wrote the Developer Guide's section on Free Command
- Designed the UML diagram for the `free` command

**Others**
- Wrote Appendix C 
- Wrote Appendix D
- Designed Class diagram for Ui Component
- Designed Class diagram for Events Component

### Contributions to Team-Based Tasks

- In charge of documentation and enforcing code quality.
