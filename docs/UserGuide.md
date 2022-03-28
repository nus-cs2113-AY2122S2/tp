# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Adding a customer satisfaction: `add satifaction`
Adds a new customer satisfaction (name of customer and their satisfaction rating from 1-5) to the list of 
customer satisfactions.

Format: `add satisfaction CUSTOMER_NAME / SATISFACTION_RATING`

* The `CUSTOMER_NAME` can be in a natural language format. It must not be 
the name of a customer whose satisfaction rating was already recorded. 
* The `SATISFACTION_RATING` must be an integer from 1 to 5, inclusive. 

Example of usage:

`add satisfaction Will Smith / 3`

Expected output: 

```
The Satisfaction instance will smith: 3 has been added to the list of Satisfactions.
There are currently 1 recorded customer satisfactions.
```




### View all customer satisfactions: `view satisfactions`
Lists out all customer satisfactions recorded so far in a user-readable format. 

Format: `view satisfactions`

Example of usage:

`add satisfaction Will Smith / 3`  <br />
`add satisfaction Bob / 5`  <br />
`add satisfaction Chris Rock / 4`  <br />
`view satisfactions`

Expected output:
```
The Satisfaction instance will smith: 3 has been added to the list of Satisfactions.
There are currently 1 recorded customer satisfactions.

The Satisfaction instance bob: 5 has been added to the list of Satisfactions.
There are currently 2 recorded customer satisfactions.

The Satisfaction instance chris rock: 4 has been added to the list of Satisfactions.
There are currently 3 recorded customer satisfactions.

======== Customer Satisfaction List ========
1. [ will smith ]: 3
2. [ bob ]: 5
3. [ chris rock ]: 4
============= End of the list =============
```

### Adding a housekeeper's performance: `add housekeeper performance`
Adds a new housekeeper performance (name of housekeeper and their performance rating from 1-5) to the list of
housekeeper performances. 


Format: `add housekeeper performance HOUSEKEEPER_NAME / PERFORMANCE_RATING`

* The `HOUSEKEEPER_NAME` can be in a natural language format. It must not be the name of a 
housekeeper who already has a recorded performance rating, and it must be the name of a 
housekeeper who has a corresponding record in the list of housekeepers. 
* The `PERFORMANCE_RATING` must be an integer from 1-5, inclusive. 

Example of usage:

`add housekeeper performance Fred Jones / 4`

Expected output:

```
The HousekeeperPerformance instance Fred Jones: 4 has been added to the list of housekeeper performances.
There are currently 1 recorded housekeeper performances.
```

### View all housekeeper performances: `view housekeeper performances`
Lists out all housekeeper performances recorded so far in a user-readable format. The performance
ratings are sorted in descending order (from highest to lowest). 

Format: `view housekeeper performances`

Example of usage:

`add housekeeper performance Fred Jones / 4`  <br />
`add housekeeper performance Joe Johnson / 2`  <br />
`add housekeeper performance John Smith / 3`  <br />
`view housekeeper performances` 

Expected output:

```
The HousekeeperPerformance instance Fred Jones: 4 has been added to the list of housekeeper performances.
There are currently 1 recorded housekeeper performances.

The HousekeeperPerformance instance Joe Johnson: 2 has been added to the list of housekeeper performances.
There are currently 2 recorded housekeeper performances.

The HousekeeperPerformance instance John Smith: 3 has been added to the list of housekeeper performances.
There are currently 3 recorded housekeeper performances.

======== Housekeeper Performance List ========
1. [ Fred Jones ]: 4
2. [ John Smith ]: 3
3. [ Joe Johnson ]: 2
============= End of the list =============
```



### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`



## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
