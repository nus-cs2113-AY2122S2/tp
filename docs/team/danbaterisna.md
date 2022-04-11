# Dan Baterisna - Project Portfolio Page

##Overview
`MindMyMoney` (M<sup>3</sup>) is a desktop app for managing your personal finances, optimized for use via a Command Line
Interface (CLI). You can use it to track your expenditures across multiple payment methods, calculate monthly
expenditure, and set financial goals.

<br/>

## Contribution Summary

### Code Contribution
Code Contribution can be viewed [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=danbaterisna&breakdown=true)

Enhancements implemented:

1. Implemented initial `UpdateCommand`.
    - Accepted an index, new description and new amount for an expenditure, and updated
2. Added `PropertyList`
    - List of key-value pairs that can be saved to and loaded from a string
    - Forms underlying basis for de/serialization.
3. Implemented `Storage` component. 
    - Has `save` and `load` methods, which save a `User` to and load a `User` from a file, respectively.
4. Implemented `serialize`, `deserialize` and `deserializeFrom` methods for `UserFinancial` classes.
    - `User`, `ExpenditureList`, `CreditCardList`, `IncomeList`, `Expenditure`, `CreditCard`, and `Income` 
      can now save themselves to and load themselves from either strings or `Scanner`s.
5. Implemented `SerializerFunctions`.
    - Abstracts out common functionality needed by de/serializers, improving DRY and SLAP.
    - Implements `addListToStringBuilder` and `convertInputToList` methods.
6. Implemented `ValidatorFunctions`.
    - Abstracts out common functionality used to check that the content of the save file is valid.
7. Added JUnit tests.
    - Added JUnit tests for `UpdateCommand`, `Storage`, `Expenditure`, and `PropertyList`.
    - Updated `UpdateCommand` and `Expenditure` tests during transition to v2.0.
    - Ensures that the code is bug free, and helps in regression testing future iterations.
8. Miscellaneous
    - Wrote JavaDoc comments for classes and methods created.
    - Fixed bugs raised during PE-D.
    - Updated command formats shown by `help` command during transition to v2.1.


### UG Contribution
1. Added short explanation of save file format.

### DG Contribution

1. Added sequence diagrams for loading.
   - Explains interaction between classes for loading, as well as hierarchical nature
     of the loading design.
2. Added sequence diagrams for saving.
   - Explains interaction between classes for saving, as well as hierarchical nature
     of the saving design.

### Team-Based Tasks Contribution

1. Wrote manual testing instructions.


### Miscellaneous Contribution
PR reviews (with non-trivial comments):
[#41](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/41), [#69](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/69),
[#206](https://github.com/AY2122S2-CS2113T-T10-4/tp/pull/206)

High number of bugs found (11) during PE-Dry testing for `AY2122S2-CS2113-T11-2` can be viewed [here](https://github.com/danbaterisna/ped/issues).