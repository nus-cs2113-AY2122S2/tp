# Wan Si Zheng - Project Portfolio Page

## Project: PlanITarium

PlanITarium is an application that assists you and your family in managing your finances, optimized for use on the
Command Line Interface (CLI). You can use it to view your monthly financial status, logical group family members for
better management and categorise your expenditure. PlanITarium is written in and meant to be run on `Java 11`.

### Summary of Contributions

* **Code
  Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=T10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=1szheng&tabRepo=AY2122S2-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

<p></p>

* **New Feature**: Added user input parsing.
    * Functionality: The parser methods that retrieves the parameters from the user's input and assists in correcting
      incorrect input format by providing feedback messages and warnings.
    * Justification: This feature improves the product significantly as a user can make mistakes when entering commands.
      The application should provide understandable and descriptive messages to assist in the corrections.
    * Highlights: This feature required an in-depth analysis on the user's possible undesirable inputs and deciding if
      such input should be blocked or given a warning. Its implementation then required much research and testing as
      there exists methods provided by Java that are misleading in its description or usage.

<p></p>

* **New Feature**: Added user input validation.
    * Functionality: The parser methods that helps to ensure that the user's inputs are valid.
    * Justification: This feature improves the product as a user can make mistakes when entering commands. Such methods
      are consolidated in the Parser class for ease of access, modification and addition by developers.
    * Highlights: This feature requires information and cooperation from the various command implementations, and
      affects future implementations in the same manner.

<p></p>

* **New Feature**: Added category enumeration.
    * Functionality: The enumeration of expenditure categories that allow users to tag their expenditures by using the
      number labels assigned to each category.
    * Justification: This feature improves the product as it allows advanced users to better manage their expenditure by
      categorizing them. Enumeration is used over the alternatives as the application currently does not allow for
      customized categories.
    * Highlights: This enhancement affects future commands or features that wish to extend the usage for the
      categorizing of expenditures.

<p></p>

* **Enhancements to Existing Features**:
    * Added JUnit tests for `Parser`, `ParserUtility` and `Category`:
      [#94](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/96/files),
      [#116](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/116/files),
      [131](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/131/files#diff-bbaf65121d975e9f0cc1284d06e75e12c83ff0f7b8291fd70888a08804575e88)
    * Researched and implemented method for testing prints via IO redirection:
      [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files#diff-caeba67935d0d3100de8785480552427170cb99b6af6c254616486b0bb870335)
    * Updated error messages to be more descriptive:
      [#231](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/231/files#diff-f409e5bd3cb2421cd456383eaacbf733bb6e8663452dacc719d717a7b809f240)
    * Suggested tests for features implemented by team members to increase test coverage.

<p></p>

* **Documentation**:
    * User Guide (UG):
        * Added documentation for `addin` and `deletein` beyond the base skeleton:
          [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files),
          [#161](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/161/files)
        * Added administrative descriptions: [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files)
        * Added aesthetic changes for format and styling:
          [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files),
          [#161](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/161/files),
          [#218](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/218/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Added parameters glossary under the Features section:
          [#161](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/161/files)

    <p></p>

    * Developer Guide (DG):
        * Added base format for `Design` and `Implementation` sections:
          [#109](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files),
          [#111](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/111/files)
        * Added administrative descriptions:
          [#218](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/218/files),
          [#222](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/222/files)
        * Added documentation and diagrams for the
          [Architecture Design](https://ay2122s2-cs2113t-t10-2.github.io/tp/DeveloperGuide.html#architecture) section:
          [#109](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files),
          [#131](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/131/files),
          [#231](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/231/files)
        * Added documentation and diagrams for the
          [Parser Design](https://ay2122s2-cs2113t-t10-2.github.io/tp/DeveloperGuide.html#parser-component) section:
          [#131](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/131/files),
          [#145](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/145/files),
          [#231](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/231/files)
        * Helped to standardize diagram themes:
          [#109](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files),
          [#222](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files)

<p></p>

* **Team-based Contribution**:
    * Finalized and released `v2.0` on GitHub during a Team Meeting.
    * Helped to tag milestones and labels for team on occasions.
    * Provided offline assistance to teammates.
    * Collated, summarised and categorised issues raised in PE-D for ease of fixing.
    * PRs reviewed (with non-trivial comments):
      [#52](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/51),
      [#79](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/79),
      [#112](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/112),
      [#124](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/124),
      [#237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237)
