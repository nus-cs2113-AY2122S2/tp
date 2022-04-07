# Xunyi Zeng - Project Portfolio Page

## Overview
I was one of the developers of Hotel Lite, an application to help hotel managers easily 
manage their businesses. This application offers functionalities for keeping track of hotel inventory,
managing housekeeper records, recording customer satisfaction ratings,
assigning housekeepers to rooms, adding, deleting, viewing events happening in the hotel,
and maintaining the taste of check-in/ check-out rooms.


### Summary of Contributions
#### Code contributed
[Xunyi_Zeng: tp code dashboard](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=xunyiiz&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
<br>
I was responsible for implementing the functionality to update and view room status
and implement file storage.
<br>
<br>
The updating and viewing of room required the implementation of the following classes: a `Room` class
representing a single room, and a `RoomList` which stored the list of Rooms. 
<br>
<br>
Updating the room status requires following `CheckInCommand` and `CheckOutCommand`.
The `CheckInCommand` class parses the user's input when they invoke "check in" command, changes a room status from vacant to occupied. 
The `CheckOutCommand` class parses the user's output when they invoke "check out" command, changes a room status from occupied to vacant.
When parsing user input, I consider the following edge cases/possible user errors.
* User inputs empty room id.
* User inputs invalid room id (i.e. room id is not inside the room list).
* User inputs room id which the room is already in occupied/vacant status.
<br>
<br>
Viewing room information can be implemented by `CheckRoomCommand` to check one room according to the room id, `CheckAllRoomCommand`
to list the information of all room, `CheckRoomByLevelCommand` to list the information of room according to the level and `CheckRoomByCategoryCommand`
to list the room information according to the category.
When parsing user input, I consider the following edge cases/possible user errors.
* User inputs empty room id.
* User inputs invalid room id (i.e. room id is not inside the room list).
* User inputs invalid category (valid category: single, double, triple, queen, king, twin)
* User inputs invalid level (valid level is from 1 to 4)
<br>
<br>
I also implemented `AssignmentMap` to record the room and assigned housekeeper name. This is implemented using HashMap
data structure. The key of the map is room id and the corresponding value is assigned housekeeper name.
<br>
<br>
Specific exception classes (that inherited from the parent exception class, `HotelLiteManagerException`) were created
for most of the edge cases above (i.e. `InvalidRoomRoomNumberException`, `InvalidLevelException`, `InvalidLevelException`)
<br>
<br>
I implemented the abstract class `FileManager` which other file manager classes can inherit from.
<br>
<br>
I implemented the `ListContainer` so that all the list classes (i.e. `RoomList`, `SatisfactionList`) can be retrieved from it.
When passing lists to execute function of Command class, we only need to pass one `ListContainer` object, rather than all the 
list objects.
<br>
<br>
Finally, saving the room status and housekeeper assignment map required the implementation of the `RoomFileManager` 
and `AssignmentListFileManager` classes.
#### Contributions to the UG
I wrote the instructions for the "Check in a room: `check in`",
Check out a room: `check out`, "Check room information: `check room`",
"Check room information by level: `check level`", "Check room information by category: `check category`",
and "Check all room information: `check all room`".
#### Contributions to the DG
I wrote the documentation for "check room information by level" section.
#### Contributions to team-based tasks
I helped maintain the issue tracker.
#### Reviewing/mentoring contributions
I often reviewed and approved the pull requests of other members of my team.

During team meetings, I participated in the tasks at hand, whether it was brainstorming, bug-fixing, or resolving
conflicting files. When not in meetings,
