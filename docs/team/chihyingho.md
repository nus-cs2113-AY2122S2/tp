# Ho Chih Ying's Project Portfolio Page

## Project: Inventory Manager
Inventory Manager is a command line application that aims to help student organisations manage their inventory. <br>
The target users of this application are executive committee members of student organisations.

## My Contributions
<ul>
    <li><a href="#code">1. Code Contributed </a></li>
    <li><a href="#features">2. Features Implemented </a></li>
    <li><a href="#enhancements">3. Enhancements Implemented </a></li>
    <li><a href="#ug">4. Contributions to the User Guide </a></li>
    <li><a href="#dg">5. Contributions to the Developer's Guide </a></li>
    <li><a href="#team">6. Contributions to Team Based Tasks </a></li>
    <li><a href="#review">7. Review/Mentoring Contributions </a></li>
</ul>

<h3 id="code">1. Code Contributed </h3>
Click <a href="https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=chihyingho&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other">here</a> to view the code that I contributed.

<h3 id="features">2. Features Implemented </h3>
1. Help Command
- What it does:
  Displays a Help Menu that tells users what each command does and the correct syntax for calling them.
- Justification:
  Inventory Manager has many commands, each with its own command syntax.<br>
  The Help Command will help both new and old users to recall what commands are available and how to call them, <br>
  hence allowing them to make full use of the variety of features that Inventory Manager has.
2. Return Command
- What it does:
  Returns an item that is either overdue or currently on loan. It also modifies the end date of the borrow record of the
  <br> item to the date on which the item was returned and the isReturned attribute of the borrow record.
- Justification:
  Modifying the return date of a borrow record to the date of return will help the club committee keep track of which
  <br> items and what quantity of it should be in the inventory at any one point of time.
  Additionally, it will help the committee be aware of whether an item is available for loan ahead of time, or
  <br> unavailable for loan even after the item's due date.
3. Lost Command
- What it does:
  Marks an item as lost and deletes it from the inventory.
- Justification:
  By allowing an item to be reported as lost and deleting it from the inventory, Inventory Manager ensures that students
  <br> will not mistakenly loan an item that is lost and no longer available. It also helps student committees be aware of
  <br> what items are no longer in the inventory.

<h3 id = "enhancements">3. Enhancements Implemented </h3>
1. Implemented `UI` component which displays messages to the user.
2. Implemented `InvMgr` main class which governs the flow of the entire programme.
3. Modified `LostCommand` to delete an item from the inventory.
   Initially, `LostCommand` was implemented by adding `isLost` as attribute to `Item` so that users could view which items were lost.
   <br> However, this was later modified as it was deemed that viewing lost items would be unnecessary information and would result in too many items being displayed in the CLI. Hence, this attribute was removed.
   <br> Later,`LostCommand` was implemented to delete an item from the inventory when the item's entire quantity was lost. The item's quantity would be reduced if a partial quantity of the item was lost.
   <br> However, this was later realised to create bugs as reducing the quantity of an item would affect all current and future borrowings.
   <br> Hence, LostCommand was finally implemented to delete an item entirely when it was lost, along with its borrow records.
4. Modified `BorrowRecord` class to include `isReturned` attribute.
   The justification for doing this was so that other commands could utilise the `isReturned` attribute in conjunction with the `BorrowStatus` class and attribute to assess if an item was currently available, currently on loan, or overdue.
5. Implemented `ListCurrentBorrowingsCommand` to list all items that are currently being borrowed.
6. Implemented `DescCommand` class to retrieve the description from a chosen item by the user.
7. Wrote JUnit tests for `ReturnCommand` and `LostCommand`

<h3 id="ug">4. Contributions to the User Guide </h3>
1. Wrote the user guide for `help`, `lost`, `return`.

<h3 id="dg">5. Contributions to the Developer's Guide </h3>
1. Created `Lost Command`, `Return Command` and `HelpCommand` sequence diagrams and wrote implementation sections for all 3 commands.
2. Create `UI` class diagram and wrote UI Component section.

<h3 id="team">6. Contributions to Team Based Tasks </h3>
1. General code enhancements and bug fixing
2. Updated the issue tracker

<h3 id="review">7. Review/Mentoring Contributions </h3>
Team CS2113-T11-2 Repo (DG Review):
1. [Link](https://github.com/nus-cs2113-AY2122S2/tp/pull/17/files/fe0608ed4b787b900a45390816f1c55f088a9559)

Our Team's Repo 
- General reviewing and approval of PRs
- [#PR 199] https://github.com/AY2122S2-CS2113-F10-2/tp/pull/199
- [#PR 185] https://github.com/AY2122S2-CS2113-F10-2/tp/pull/185
- [#PR 179] https://github.com/AY2122S2-CS2113-F10-2/tp/pull/179
- [#PR 92] https://github.com/AY2122S2-CS2113-F10-2/tp/pull/92
- [#PR 91] https://github.com/AY2122S2-CS2113-F10-2/tp/pull/91



