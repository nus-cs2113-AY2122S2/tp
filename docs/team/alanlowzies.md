# Project Portfolio Page of Alan Low (alanlowzies)

## Overview
This page showcases my contributions to the development of WerkIt!, written in Java that allows users to create a weekly 
workout schedule for them to refer to and follow. I co-developed this application with my peers as part of a team 
project (tP) in the CS2113T Software Engineering & Object-Oriented Programming module offered by the School of 
Computing, National University of Singapore. More details about the project can be found in the 
[WerkIt! User Guide](../UserGuide.md), [Developer Guide](../DeveloperGuide.md), and 
[GitHub repository](ttps://github.com/AY2122S2-CS2113T-T09-2/tp).

## Summary of Contributions
### Code Contributed
A detailed report of my code contributions to the tP can be found in the [tP Code Dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=alanlowzies&breakdown=true) 
hosted by the module coordinators of CS2113T.

A summary of my code contributions are as follows:
1. Wrote most of the `FileManager` API, which allows WerkIt! to read and write application data
   into text files stored on the user's local filesystem. 
2. Implemented the functionality for users to create a new workout.
3. Wrote most of the `UI` API, which contains UI-related elements, including WerkIt!'s banner logo and response messages.
4. Wrote most of the `WerkIt` API, which contains the code to start up WerkIt!, load saved file data
   (if any), and receive and process user inputs.
5. Wrote parts the `Parser` API to parse user inputs into the appropriate `Command` class types and check for invalid 
   characters in the user inputs.
6. Implemented the HashMap data structure in the `WorkoutList` API to allow `PlanList` to reference
   workouts created by the user.
7. Wrote the `LogHandler` API, which allows the various components in WerkIt! to log into a file
  stored in the user's local filesystem.
8. Wrote the `Command` abstract class, which is a template class that sets out the main structure of all types
  of commands in WerkIt! (e.g. `WorkoutCommand`, `ExerciseCommand`, etc.).
9. Wrote most of the `WorkoutCommand` API, which contains a workout-related command that the user has requested
  WerkIt! to carry out.
10. Wrote some exception APIs for WerkIt!, including `InvalidWorkoutException`, `InvalidExerciseException`, and
  `UnknownFileException`.
11. Contributed to the `ExerciseList` API, which is mainly done by my team member Haofeng, by adding
  additional functionality to the API such as populating the `exercise.txt` file with default exercises. The exercises
  were thought of by Haofeng :)
12. Wrote JUnit test cases for WerkIt! APIs including (but not limited to) `WorkoutCommand`, `FileManager`, `Parser`, etc.
13. Wrote JavaDocs for the classes and methods that I have created.


### Enhancements Implemented
1. (Developer and User Guides) Replaced Markdown quote blocks with custom CSS boxes for informational and warning boxes
    as the original MD quote blocks were hard to spot and thus readers may miss such messages.
    - An example of the custom CSS boxes can be found in the ['Notations Used In This Guide'](../UserGuide.md#notations-used-in-this-guide)
  section of the user guide.
2. (Developer and User Guides) Inserted 'buttons' throughout the documentations to allow users to easily click and navigate back 
   to the guides' tables of contents. 
    - I tried to utilise JavaScript for a floating back-to-top button, but due to the time constraints and the need to
    fully understand the structure of the webpage theme that we're using on GitHub Pages, I decided to
    use simple HTML hyperlink buttons with some CSS styling instead.
    - Click [here](../images/ppp/alanlowzies/BackToToCDemo.gif) for a GIF demonstration of the enhancement.

### Contributions to the User Guide (UG)
1. Wrote the guide on [creating a new workout](../UserGuide.md#create-a-workout-workout-new).
2. Wrote the informational section on [WerkIt!'s local storage structure](../UserGuide.md#werkits-local-storage-information).
3. Wrote the [Quick Start Guide](../UserGuide.md#quick-start-guide) section, which guides the user on the necessary
prerequisites to download and the steps to get it running on the user's computer.
   - Includes a list of recommended OSes, their versions, and the terminals to use for each OS.
4. Wrote the [frequently asked questions](../UserGuide.md#frequently-asked-questions-faq) section.

### Contributions to the Developer Guide (DG)
1. Wrote and designed diagrams for the following sections of the ['Implementation'](../DeveloperGuide.md#implementation) 
   section:
    - How WerkIt! repeatedly waits and receives user inputs ([link to part](../DeveloperGuide.md#getting-user-input-continuously)).
    - How WerkIt! parses user inputs and determines the appropriate procedures to take 
      ([link to part](../DeveloperGuide.md#parsing-user-input-and-getting-the-right-command)).
    - How WerkIt! processes and creates a new workout when requested by the user
      ([link to part](../DeveloperGuide.md#create-new-workout)).
    - Most of the section on [File Management](../DeveloperGuide.md#file-management).
3. Wrote the 'Design - Storage Component' section ([link to part](../DeveloperGuide.md#storage-component)).
4. Wrote the instructions on 
[how to set up the development environment](../DeveloperGuide.md#setting-up-your-development-environment).
5. Wrote the instructions for manual testing of [creating workouts](../DeveloperGuide.md#creating-a-new-workout) and 
   [data saving](../DeveloperGuide.md#test-on-data-saving).
6. Wrote the [target user profile](../DeveloperGuide.md#target-user-profile), 
   [value proposition](../DeveloperGuide.md#value-proposition), and parts of the 
   [glossary](../DeveloperGuide.md#glossary) of WerkIt!.
7. Wrote the [table of contents](../DeveloperGuide.md#table-of-contents).

### Contributions to Team-Based Tasks
- Set up the team's organisation and repository on GitHub
- Created skeleton codes and packages for the project.
- Set up and maintained the team's Google Drive folder, which includes the team's project notebook
and other documents for the co-requsuite module CS2101 Effective Communication for Computing Professionals.
- Helped to maintain the team's GitHub repository issue tracker.

### Review/Mentoring Contributions
- Reviewed fellow team members' pull requests (PRs), including (but not limited to):
  - [PR #71 - Add logging and assertion for update workout](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/71#pullrequestreview-910244404)
  - [PR #97 - Add Create Plan Feature](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/97#pullrequestreview-914977219)
  - [PR #123 - Branch search workout](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/123#pullrequestreview-919593971)
  - [PR #134 - Improve delete workout](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/134#pullrequestreview-920276634)
  - [PR #218 - Branch file bug](https://github.com/AY2122S2-CS2113T-T09-2/tp/pull/218#pullrequestreview-929510891)

### Contributions Beyond the Project Team
- Provided feedback and potential bug reports to another team's project (CS2113T-T10-3's Mod Happy) as part
  of the Practical Exam Dry-Run (PED). Some of the bugs reported include (but are not limited to):
  - [(#3) Editing a task to add a duration does not show the new duration](https://github.com/alanlowzies/ped/issues/3)
  - [(#5) Discrepancy in maximum allowable time for time values mentioned in UG](https://github.com/alanlowzies/ped/issues/4)
  - [(#5) Editing a module to an empty description is not accepted](https://github.com/alanlowzies/ped/issues/5)

---

## Reflections on the Team Project (tP)
Despite having done a couple of software engineering projects back in my polytechnic days, the tP has taught me many
new things about the intricacies of a software engineering project. From learning to use Git to learning how to write
proper documentations for the intended audience, the practical tP has given me the opportunity to learn these things 
which may not have been possible or as effective if I just sat through lectures to learn about these concepts. In
addition, the tP has reminded me the importance of communicating effectively with my team members on project matters,
and to communicate professionally to get the team to achieve our goals in the project. Thus, I am grateful that the 
co-module CS2101 Effective Communication for Computing Professionals was conducted alongside the CS2113T module as it
gave us the additional experience of having to present our work to non-technical professionals, which I presume is the
norm when working in the industry. Thus, learning how to convert technical topics into something that can be easily
understood by non-technical professionals is also an important skill to have.

Overall, this module has given me a greater appreciation for the 'art' of software engineering, especially when thinking
about the large tech companies out there having to manage and maintain much more complex software and systems among 
large groups of people who are from technical (e.g. software developers) and non-technical (e.g. marketing managers) 
backgrounds.