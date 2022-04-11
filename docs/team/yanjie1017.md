# Toh Yan Jie - Project Portfolio Page

## Overview
**MeetingJio**

MeetingJio is a program written in Java for finding potential time slots for team meetings
based on everyone’s availability via a Command Line Interface (CLI). If you can type fast,
MeetingJio can get your meeting management tasks done faster than manually eyeballing your timetable.

## Summary of Contributions
- Functional code
- Test code
- Documentation
- Other roles

### Contribution to Functional Code

**Add User Feature**

- `AddUserCommand`
  - Wrote the code to create a user and add a new timetable to the master timetable.
  - Added exception handling, error messages, JavaDoc and JUnit testing for `AddUserCommand`.
- `prepareAddUser`
  - Wrote the parser for `AddUserCommand` to parse user input that starts with 'add_user',
  check the validity of the input and convert it into arguments that can be read by the `AddUserCommand`.
  - Added exception handling, error messages and JUnit testing for `prepareAddUser`.

**Add Lesson Feature**
- `AddLessonCommand`
  - Wrote the code to create a new lesson and add the new lesson to the specified timetable.
  - Added exception handling, error messages, JavaDoc and JUnit testing for `AddLessonCommand`.

**Edit Lesson Feature**
- `EditCommand`
  - Wrote the code to edit the specified lesson with the user input and check if the new parameters' values 
  are valid.
  - Wrote the code to revert the edit if the edited lesson is invalid in the specified timetable.
  - Added exception handling, error messages, JavaDoc and JUnit testing for `EditCommand`.
- `prepareEdit`
  - Wrote the parser for `EditCommand` to parse user input that starts with 'edit',
    check the validity of the input and convert it into arguments that can be read by the `EditCommand`.
  - Added exception handling, error messages and JUnit testing for `prepareEdit`.

**Parser to Split Arguments**
- `ParserArguments`
  - Wrote multiple argument parsers which includes parameter checks for different types of commands.
  - Added exception handling, error messages and JavaDoc for `ParserArguments`.

### Contribution to Test Code
**Commands**
- AddUserCommandTest
- AddLessonCommandTest
- EditCommandTest

**Parsers**
- ParserTest
  - prepareEdit

**Timetables**
- MasterTimetableTest
- TimetableTest

### Contribution to Documentation
**User Guide**
- Add User Feature
  - Wrote the add user section in the User Guide.
- Add Lesson Feature
  - Wrote the add lesson section in the User Guide.
- Edit Lesson Feature
  - Wrote the edit lesson section in the User Guide.
- Others
  - Added the Introduction and Quick Start sections in the User Guide.
  - Added the parameter tables for all the commands.
  - Updated the command summary section in the User Guide.
  - Formatted the User Guide.

**Developer Guide**
- Add User Feature
  - Wrote the add user section in the Developer Guide.
  - Drew the sequence diagram for add user operations.
- Add Lesson Feature
  - Wrote the add lesson section in the Developer Guide.
  - Drew the sequence diagram for add lesson operations.
- Edit Lesson Feature
  - Wrote the edit lesson section in the Developer Guide.
  - Drew the sequence diagram for edit lesson operations.
- Parser Component
  - Wrote the parser component section in the Developer Guide.
  - Drew the class diagram of parser component.
- Others
  - Added the user stories.


## Other roles
**Architecture:**

Came up with a rough software architecture design that includes parsers, events, timetables and commands.

**Integration:**

In charge of versioning of the code, maintaining the code repository, integrating various parts of the software to create a whole.

**Code quality:**

Looked after code quality, ensured adherence to coding standards, etc.
  