# Wan Si Zheng - Project Portfolio Page

## Project: PlanITarium

<!-- this is placeholder description -->
PlanITarium is a CLI application that helps you and your family to track your finances. It is written in Java, and has
about 9k LoC.
<!-- this is placeholder description -->

Given below are my contributions to the project.

* **Code
  Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=T10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2022-02-18&tabOpen=true&tabType=authorship&tabAuthor=1szheng&tabRepo=AY2122S2-CS2113T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

<p></p>

* **New Feature**: Added user input parsing.
    * Functionality: The parser methods that retrieves the parameters from the user's input and assists in correcting
      incorrect input format by providing feedback messages and warnings.
    * Justification: This feature improves the product significantly as a user can make mistakes when entering commands.
      The application should provide understandable and descriptive messages to assist in the corrections.
    * Highlights: This feature required an in-depth analysis on the user's possible undesirable inputs and deciding if
      such input should be blocked or given a warning. Its implementation then required much research and testing (e.g.
      learning regex) as there exists methods provided by Java that are misleading in its description and usage.

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
    * Researched and implemented method for testing prints via IO redirection:
      [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files#diff-caeba67935d0d3100de8785480552427170cb99b6af6c254616486b0bb870335)
    * Updated error messages to be more descriptive:
      [#231](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/231/files#diff-f409e5bd3cb2421cd456383eaacbf733bb6e8663452dacc719d717a7b809f240)
    * Suggested tests for features implemented by team members to increase test coverage.

<p></p>

* **Documentation**:
    * User Guide (UG):
        * Added documentation for `addin` and `deletein` beyond the base skeleton:
          [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Added administrative
          descriptions: [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Added aesthetic changes for format and styling:
          [#136](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/136/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
          , [#161](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/161)
          , [#218](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/218/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)
        * Added parameters glossary under Features section:
          [#161](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/161/files#diff-b50feaf9240709b6b02fb9584696b012c2a69feeba89e409952cc2f401f373fb)

    * Developer Guide (DG):
        * Added base format for `Design` and `Implementation` sections:
          [#109](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files),
          [#111](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/111/files)
        * Added administrative descriptions:
          [#218](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/218/files),
          [#222](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/222/files)
        * Added documentation for
          the [Architecture Design](https://ay2122s2-cs2113t-t10-2.github.io/tp/DeveloperGuide.html#architecture)
          section:
          [#109](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/109/files),
          [#131](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/131/files)
        * Added documentation for
          the [Parser Component Design](https://ay2122s2-cs2113t-t10-2.github.io/tp/DeveloperGuide.html#parser-component)
          section:
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
    * PRs reviewed (with non-trivial comments):
      [#52](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/51),
      [#79](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/79),
      [#112](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/112),
      [#124](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/124),
      [#237](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/237)

<p></p>

* **Community-based Contribution**:
    * Above average number of review comments for iP PR peer review:
      [PR19](https://github.com/nus-cs2113-AY2122S2/ip/pull/19),
      [PR60](https://github.com/nus-cs2113-AY2122S2/ip/pull/60),
      [PR74](https://github.com/nus-cs2113-AY2122S2/ip/pull/74)
    * Above average number of bugs found for [PE-D](https://github.com/1szheng/ped/issues).
