# Teo Jia Rong - Project Portfolio Page

## Project: PlanITarium

PlanITarium is an application that assists you and your family in managing your finances, optimized for use on the
Command Line Interface (CLI). You can use it to view your monthly financial status, logically group family members for
better management, and categorise your expenditures. PlanITarium is written in, and meant to be run on `Java 11`.

Given below are my contributions to the project.

* **Code
  Contributed**: [RepoSense Link](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=tjiarong&breakdown=true)

<p></p>

* **New Feature**: Added Money class
  * Functionality: Allows tracking of items related to money.

<p></p>

* **New Feature**: Added MoneyList class to track a list of spending.
  * Functionality: Stores money related items in a list to allow for aggregated tracking. 

<p></p>

* **New Feature**: Added Income class
  * Functionality: As an extension of Money class, Income class allows tracking of income item with greater details. 

<p></p>

* **New Feature**: Added IncomeList 
  * Functionality: As an extension of MoneyList class, IncomeList class allows for aggregated tracking 
  of list of incomes with greater details. 
  * Notable Additional Implementations:
    * Edit:
      Allows for changes to tracked Income items as desired by user, such as its amount or descriptions.
    * Find:
      Allows for user to search through tracked Income items for a specific descriptions or amount.

<p></p>

<div style="page-break-after: always;"></div>

* **Enhancements to Existing Features**:
    * Implemented class abstraction for Expenditure and ExpenditureList as well as additional attributes and functions:
      * Notable Additional Implementations:
          * Edit:
            Allows for changes to tracked Expenditure items as desired by user, such as its amount or descriptions.
          * Find:
            Allows for user to search through tracked Expenditure items for a specific descriptions or amount.
    * Implemented JUnit testing for Income and IncomeList class and make it more defensive:
      [#61](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/61/files),
      [#133](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/133/files)
    * Implemented additional JUnit testing for Expenditure and ExpenditureList class to cover additional implementations.
      [#133](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/133/files)

<p></p>

* **Documentation**:
    * User Guide (UG):
        * Added documentation for the features `editin` and `find`: 
          [#169](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/169/files)
    * Developer Guide (DG):
        * Added sections on `Money` design.
        * Added UML diagrams for `Money` component and `Find` implementation.

<p></p>

* **Team-based Contribution**:
    * PRs reviewed (with non-trivial comments):
      [#78](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/78),
      [#163](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/163),
      [#242](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/242),
      [#244](https://github.com/AY2122S2-CS2113T-T10-2/tp/pull/244),
    * Managed Issues
      * Created and assigned issues related to user stories for `v1.0`
      * Created and assigned issues related to user stories for `v2.0`

<p></p>

* **Community-based Contribution**:
    * Above average number of bugs found for [PE-D](https://github.com/tjiarong/ped/issues).
