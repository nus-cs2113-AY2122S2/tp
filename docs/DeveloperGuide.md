# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation
### Regex Class
#### Description
The Regex Class is created to simplify the use of regular expressions in Java.
![Regex Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Regex.puml)
The Class Diagram above shows the different variables and methods associated with the regex class.
This class takes in the regular expression as a string and the string to be matched in the class contructor, and stores the results
in the private variable `groupValues` which is a Hash Map where the key would be the capture group name and the value as the matched results.

#### Operation
![Regex Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/RegexMatching.puml)
The above diagram shows the inner workings of how the Regex Class will work. Here are the steps taken by the Regex Class:
1. findMatch() called when instantiating groupValues variable
2. findGroups() called within findMatch() to find the capture group names within the regex string
3. regexMatching() will then be called within findMatch() which will use Java's base Pattern and Matcher Class, and returns a Matcher Object
4. findMatch() will then use the Matcher object to fill in the HashMap of <key, value> pair of <capture group name, regex matching>
5. findMatch() will then return the HashMap object to groupValues variable

#### Example of Usage
```
String regex = "id/(?<id>\\d*) n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>\\.*)";
Regex regexMatch = new Regex(userInput, regex);
HashMap<String, String> matches = regexMatch.getGroupValues();
String id = matches.get("id");
String name = matches.get("name");
String quantity = matches.get("qty");
String description = matches.get("desc");
```
### Commands Class
#### Description
The Commands Class is the class which contains all the available commands that can be used in the Simplst CLI application.
![Commands Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/CommandsClass.puml)

More about how each feature is run can be seen in each of the methods that can be found below.

### View Good Method
#### Description
View Good belongs as part of the Commands Class. It is used when a user would like to know more information about an inventory item has an item id linked to it in the warehouse.

#### Operation
![View Good Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/viewGood.puml)

The above sequence diagram shows the operation of how the view good method will be called.
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `view`
3. The Regex Class will then be call to match the rest of the user's input to find the id required for retrieving information of the inverntory.
4. The User Interface Class will call the viewGood() method from the Commands Class
5. This method will retrieve information of an inventory item by searching through the userGoods array list for the corresponding id.
6. The information retrieved will then be formatted and returned to screen for the user the see the information.

### Add Goods Method
#### Description
Add Goods belongs as part of the Commands Class. It is used to add a Good Object into the Collection of Goods Objects currently in the Warehouse.

#### Operation
![Regex Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Regex.puml)
The above sequence diagram shows the operation of how the add goods method will be called. 
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `add`
3. The Regex Class will then be call to match the rest of the user's input to find the necessary values required for the Good Class
4. Afterwards, the User Interface class will call addGoods() method from the Commands Class
5. This method will then call the constructor of the Good class, creating a new instance of the Good Object
6. The addGood() method will then add the Good to the Collections storing the Good Objects currently in the WareHouse

For more examples of how a user can use a command, refer to the [UserGuide](/UserGuide.md)


## Product scope
### Target user profile

Target user profile: Warehouse Workers
* has a need to update the warehouse on current stock of goods
* has a need to view the details of specific goods
* can type fast
* is reasonably comfortable using CLI apps

Target user profile: Warehouse Managers



### Value proposition

A cheap, user-friendly Warehouse Management System with intuitive commands to improve efficiency.

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|Warehouse Worker|Get the total number of goods in the warehouse|its less tiring to have to count the total number of goods in the warehouse|
|v1.0|Warehouse Worker|View the description of goods easily|it would be easier for stocktaking|
|v1.0|Warehouse Worker|Add inventory items to the items list|so that I can keep track of goods entering the warehouse|
|v1.0|Warehouse Worker|Remove inventory items from the items list|so that I can track whenever a good is not in the warehouse|
|v1.0|Warehouse Worker|Get a list of all the inventory currently in the warehouse|so that I can see all the items we have in the warehouse|
<!-- |v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list| -->

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
