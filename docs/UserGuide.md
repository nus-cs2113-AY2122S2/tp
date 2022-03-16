# User Guide

## Introduction

`MindMyMoney` (M<sup>3</sup>) was proposed for users with multiple payment methods who wish to track their expenditures. 
Conducting anecdotal interviews, we realized users face difficulty tracking their expenditures across different payment 
platforms on vastly different items. `MindMyMoney` was then conceptualized on the basis of tracking user’s expenditures.

## Quick Start

1. Ensure that you have Java 11 or above installed. 
2. Down the latest version of `MindMyMoney` from [here](https://github.com/AY2122S2-CS2113T-T10-4/tp).


## Commands Summary <br>

| Command | Format, examples                                                            |
|---------|-----------------------------------------------------------------------------|
| Help    | `help` <br/> Prints a list of available commands                            |
| Add     | `add [DESCRIPTION] [AMOUNT]`<br/>e.g `add biscuits 3`                       |
| List    | `list`<br/> Displays the current list of expenditures stored by the user    |
| Delete  | `delete [INDEX]`<br/>e.g `delete 1`                                         | 
| Update  | `update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]`<br/>e.g `update 2 snacks 5` |
| Exit    | `bye`<br/> Shuts down `MindMyMoney` application                             |
|

## Features <br>
The following are features of the `MindMyMoney` application. Please ensure that the format of commands given is accurate<br>

### Display list of currently available commands:`help` <br>
Prints a list of currently available commands for users to track their expenditures<br> 
Format:`help` <br>
![help screenshot](./images/help.PNG)<br>

### Add an expenditure to your program:`add`<br>
Adds an expenditure to your program. Only **one** expenditure can only be added per command.<br>
Format:`add [DESCRIPTION] [AMOUNT]` <br>
Example:`add biscuits 3`<br>
![add screenshot](./images/add.PNG)

### Display current list of expenditures:`list` <br>
Prints in the terminal the current list of expenditures that you have logged so far <br>
Format:`list`<br>
![list screenshot](./images/list.PNG)<br>

### Modify an expenditure on your list:`update` <br>
Modify an expenditure on your list, by specifying the expenditure's index. <br>
Use the `list` command to view the current indices of your expenditures. <br>
Format:`update [INDEX] [NEW_DESCRIPTION] [NEW_AMOUNT]`<br>
For example: `update 1 drinks 2`<br>
![update screenshot](./images/update.PNG)<br>

### Removing an expenditure on your list:`list`<br>
Delete an expenditure from your list, by specifying the expenditure's index.<br>
Use the `list` command to view the current indices of your expenditures. <br>
Format:`delete`<br>
For example: `delete 1`
![delete screenshot](./images/delete.PNG)<br>

### Exiting MindMyMoney application:`bye`<br>
Shuts down the MindMyMoney application.<br>
Format:`bye`<br>
![bye screenshot](./images/bye.PNG)<br>

## FAQ

**Q**: How do I save my data for my next usage? <br>

**A**: We are currently working on implementing this feature and 
will update the User Guide once available! :) <br>

