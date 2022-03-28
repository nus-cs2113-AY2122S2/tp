# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

## Customer Satisfaction Related Commands

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


## Housekeeper Related Commands

### Adding Housekeeper Profile : `add housekeeper`

Adds a new housekeeper profile into the list of housekeepers. The add profile command includes the name and age of the
new housekeeper to be added.

Format: `add housekeeper NAME / AGE`

* The `NAME` can be in any format. For example, Susan1@.
* The `AGE` must be between 21 and 60.

Example of usage:

`add housekeeper susan / 46`

`add housekeeper jane / 33`

Expected output:
```
========== Noted ! ==========
[SUSAN]: Age: 46, Availability: <Enter Availability>
=============================
```

### View housekeeper list : `view recorded housekeeper`

View all housekeeper in the list with their name, age and availability. Availability will not be shown if user
have not entered it yet.

Format: `view recorded housekeeper`

Expected output:
```
======== Housekeeper List ========
1. [SUSAN]: Age: 46, Availability: <Enter Availability>
2. [JANE]: Age: 33, Availability: <Enter Availability>
3. [SALLY]: Age: 60, Availability: ( Monday Tuesday )
======== End of the list ========
```

### Add Availability of Housekeeper : `availability`
Declare housekeeper's availability in the week using numbers from 1 to 7 where each number represents a day in a week.

Numbers from 1 to 7 are map to a day in a week by: <br/>
`1`: Monday <br/>
`2`: Tuesday <br/>
`3`: Wednesday<br/>
`4`: Thursday <br/>
`5`: Friday <br/>
`6`: Saturday <br/>
`7`: Sunday <br/>

Format: `availability NAME / DAYS`
* `NAME` given must be in the records of housekeeper list to be eligible to add availability.
* `DAYS` given must be between 1 and 7. Multiple days can be represented using commas.

Example of usage:

`availability sally / 1`

`availability jane / 1,3,5,7`

Expected output:

`Added sally availability into records`

### Delete Housekeeper: `delete housekeeper `
When a housekeeper resigns, calling delete housekeeper command will remove housekeeper that resign from 
the list of housekeepers. This command will also show number of housekeeper left in the list of housekeeper.


Format: `delete housekeeper NAME`

* `NAME` given must be in the records of housekeeper list to be eligible for deletion.

Example of usage:
```
delete housekeeper sally
```

Expected output:
```
Deleted sally from the list of profile
Take note! Total pax of housekeeper:  2
```

### Get Housekeepers Available on Interested Days : `get available on `
Derive a list of housekeeper available from any day in a week. If command is used on days with no housekeeper 
available, list printed will indicate that no housekeeper is available on that day.

Format:
`get available on DAY`

* `DAY` have to be between 1 and 7.
* Numbers from 1 to 7 are map to a `DAY` in a week by: <br/>
  `1`: Monday <br/>
  `2`: Tuesday <br/>
  `3`: Wednesday<br/>
  `4`: Thursday <br/>
  `5`: Friday <br/>
  `6`: Saturday <br/>
  `7`: Sunday <br/>
  
* Only one day can be enquired at a time.

Example of usage:

`get available on 1`

`get available on 7`

Expected output:

If there exist any housekeeper on Monday:
```
======== Monday List ========
1. sally
======== End of the list ========
```

If there is no housekeeper available on Monday:
```
======== Monday List ========
TAKE NOTE! NO ONE IS AVAILABLE!!
======== End of the list ========
```

### Reset Housekeeper Availability: `is a new week`
When a new week begins, all housekeeper availabilities can be reset by calling this command. For verification,
command will also print out the list with all availabilities being reset.

Format: `is a new week`

Expected output:
```
Housekeeper's availability has been reset!
======== Housekeeper List ========
1. [SUSAN]: Age: 46, Availability: <Enter Availability>
2. [JANE]: Age: 33, Availability: <Enter Availability>
======== End of the list ========mand Sum
```

### Increase Age of All Housekeepers: `is a new year`
When a new year begins, all housekeeper age can be increase by one through this command.
As the age limit of working as a housekeeper in the hotel is 21 to 60 years old, housekeeper
that exceed age limit of 60 after increment will be removed from the list of housekeeper. A list of
housekeeper exceeding age limit will be shown when removing.

Format: `is a new year`

Expected output:

If list of housekeeper has housekeeper which exceed age limit:
```
Everyone age has increased by one
**Over age limit housekeeper will be removed from list**
======== Age Limit Exceed List ========
1. [LUCY]: Age: 60, Availability: <Enter Availability>
2. [JON]: Age: 60, Availability: <Enter Availability>
======== End of the list ========
```
If none of the housekeeper exceed age limit:
```
Everyone age has increased by one
**Over age limit housekeeper will be removed from list**
======== Age Limit Exceed List ========
Everyone is within age limit
======== End of the list ========
```


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Commary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
