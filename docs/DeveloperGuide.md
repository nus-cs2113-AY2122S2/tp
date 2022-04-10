# Developer Guide

## Acknowledgements

## Design
![Architecture Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/ArchitectureDiagram.puml)

The **Architecture Diagram** above shows the high level design of Simplst. A user who enters their input into Simplst will be handled by User Interface Class.
1. The [User Interface Class](#user-interface-class) will then decide which [Parser](#command-parser) to send the user input to
2. The specific Parser will then call the [Match Keyword Class](#match-keyword-class) to find the corresponding keywords in the user input
3. The parser will then check the flags indicated in the user input to check which function from the [Warehouse Class](#warehouse-class) to call
4. The [Warehouse Class](#warehouse-class) will contain a collection of [Unit Good](#unit-good-class), [Good](#good-class) and [Order](#order-class)

The sequence diagram using the [View Parser](#view-parser) below shows an example of how the user will send Simplst `view o/ oid/1` with the aim of viewing details of order id 1

![View Order Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/ViewOrder.puml)

The specific details of exactly how view order command will run will be described later in [View Parser](#view-parser)

## Implementation
### Unit Good Class
The Unit good class keep track of the unit goods in the warehouse. A unit good is the template, holding the specific details such as SKU, name, description and [Capacity](#capacity-enumeration)
#### Description
![Unit Good Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/UnitGood.puml)

Unit goods will be stored as a HashMap<String, UnitGood> in the warehouse with the key being a unique SKU as a string and the value is the actual Unit Good.
This means that to update a Unit Good details, there is a need to first remove the Unit Good from the HashMap, then add a new instance of the Unit Good. This is to ensure that existing Unit Goods 
will not be incorrectly overwritten

The class diagram will be shown below.


### Good Class
A Goods class will extend the unit good class. It will contain the extra variable of quantity which is meant to show the current quantity the warehouse contains.
#### Description
Similar to Unit Goods, the Goods will be stored as a HashMap<String, Good> in the warehouse with the key being the SKU as a string and the value is the actual Good.

![Good Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Good.puml)

The Goods class allows for the creation of orderline objects which has the following attributes: id, name, quantity and
description. Each attribute can be obtained using public get methods, and the attribute quantity can be set using the public set method.

### Capacity Enumeration
The Capacity enum is meant as a heuristic to determine the size of a unit good and good.
#### Description
This enum will have 3 values:
- SMALL
- MEDIUM
- LARGE

The capacity SMALL will be 1 unit of size, MEDIUM will be 2 units of size and finally LARGE will be 3 units in size.
A diagram can be seen in the [Architecture Class Diagram](#design)

### Order Class
#### Description
### Orderline Class
A Orderline class will extend the Goods class. It will contain the extra variable of quantityFulfilled and isCheckedOff.
#### Description
![Orderline Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Orderline.puml)

The quantity variable here will be to indicate the quantity required to fulfill the current order.
The quantity fulfilled variable will be to indicate the quantity currently fulfilled for that order. When the quantity fulfilled equals to the quantity required, the variable isCheckedOff will be true.
This is to indicate that this orderline is fulfilled in that order, and it does not require any more of the good.

The diagram below shows the model component of the orderline class.
### Warehouse Class
The warehouse class is created to simulate an entire warehouse
#### Description
![Warehouse Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/Warehouse.puml)

Most of the methods in the Warehouse Class will require users to know the SKU of the Good that they want to interact with.

The warehouse class will contain:
- List of orders
  - An arraylist is chosen to store the orders.
  - This is so as to store the ordering of orders being added.
- HashMap of unitgoods
  - A hashmap is chosen for UnitGoods with the <key, value> pair as <SKU, UnitGood>.
  - This is to allow for quick access to the details of the unit good through knowing the SKU
- LinkedHashMap of goods
  - A linked hashmap is chosen for goods to ensure that we are able to maintain the ordering of goods added while having the simplicity of accessing the necessary goods through their SKU


### Command Parser
#### Description
This is the description for the Command Parser. This is an abstract class which all other parser classes will implement
![Command Parser Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/CommandParser.puml)

#### Add Parser
##### Description
#### Remove Parser
##### Description
#### View Parser
##### Description
#### List Parser
##### Description
#### Find Parser
##### Description
####  Help Parser
##### Description
#### Fulfill Parser
##### Description

### User Interface Class
#### Description

### Display Class
#### Description

### Match Keyword Class
#### Description
The Match Keyword Class is created to simplify the use of regular expressions in Java.

![Keyword Matching Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/MatchKeywords.puml)

The Class Diagram above shows the different variables and methods associated with the Match Keyword class.
This class takes in the regular expression as a string and the string to be matched in the class contructor, and stores the results
in the private variable `groupValues` which is a Hash Map where the key would be the capture group name and the value as the matched results.

#### Operation

![Keyword Matching Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/KeywordMatching.puml)

The above diagram shows the inner workings of how the Match Keyword Class will work. Here are the steps taken by the Regex Class:
1. findMatch() called when instantiating groupValues variable
2. findGroups() called within findMatch() to find the capture group names within the regex string
3. regexMatching() will then be called within findMatch() which will use Java's base Pattern and Matcher Class, and returns a Matcher Object
4. findMatch() will then use the Matcher object to fill in the HashMap of <key, value> pair of <capture group name, regex matching>
5. findMatch() will then return the HashMap object to groupValues variable

#### Example of Usage
```
String regex = "id/(?<id>\\d*) n/(?<name>.*) q/(?<qty>\\d*) d/(?<desc>\\.*)";
Regex matchKeywordsMatch = new Regex(userInput, regex);
HashMap<String, String> matches = matchKeywordsMatch.getGroupValues();
String id = matches.get("id");
String name = matches.get("name");
String quantity = matches.get("qty");
String description = matches.get("desc");
```

### View Good Method
#### Description
View Good belongs as part of the Commands Class. It is used when a user would like to know more information about an inventory item has an item id linked to it in the warehouse.

#### Operation

![View Good Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/viewGood.puml)

The above sequence diagram shows the operation of how the view orderline method will be called.
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `view`
3. The Regex Class will then be call to match the rest of the user's input to find the id required for retrieving information of the inverntory.
4. The User Interface Class will call the viewGood() method from the Commands Class
5. This method will retrieve information of an inventory item by searching through the userOrderlines array list for the corresponding id.
6. The information retrieved will then be formatted and returned to screen for the user the see the information.

### Add Goods Method
#### Description
Add Goods belongs as part of the Commands Class. It is used to add a Good Object into the Collection of Goods Objects currently in the Warehouse.

#### Operation

![Regex Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/addGood.puml)

The above sequence diagram shows the operation of how the add goods method will be called. 
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `add`
3. The Regex Class will then be call to match the rest of the user's input to find the necessary values required for the Good Class
4. Afterwards, the User Interface class will call addGoods() method from the Commands Class
5. This method will then call the constructor of the Good class, creating a new instance of the Good Object
6. The addGood() method will then add the Good to the Collections storing the Good Objects currently in the WareHouse

For more examples of how a user can use a command, refer to the [UserGuide](/UserGuide.md)

### Remove Goods Method
#### Description
Remove Goods belongs as part of the Commands Class. It is used to remove a certain amount of goods from the inventory.
#### Operation

![removeGood diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/removeGood.puml)

The above sequence diagram shows the operation of how the add goods method will be called.
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `remove`
3. The Regex Class will then be called to match the rest of the user's input to find the values required to remove goods from the inventory.
4. Afterwards, the User Interface class will call removeGood() method from the Commands Class
5. This method will then reduce the quantity of a type of goods if the quantity input is not larger than the existing quantity. If the quantity input is the same as the existing quantity, the goods object will be removed from the inventory.
6. The UI will show a message of format
    > `quantity` `name` have been removed
to show that the operation is successful.

For more examples of how a user can use a command, refer to the [UserGuide](/UserGuide.md)

### List Goods or List Orders Method
#### Description
The list goods or list orders method belongs to the Warehouse Class. It is used to display the list of existing goods 
or existing orders in the warehouse to the User.

![List sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/List.puml)

The above sequence diagram shows the operation of how the list goods method will be called.

1. The User input will be read by the User Interface Class
2. The User Interface Class will the match the command keyword `list`
3. Together with the previous step, the User Interface Class will check the flag followed by `list`
   3.1 If the flag is `o/`, the User Interface Class will call the listOrders() method in the Warehouse class
      3.1.1 Lastly, the list of orders is printed to the command line.
   3.2 Else, if the flag is `g/`, the User Interface Class will call the listGoods() method in the Warehouse class
      3.2.1 Following that, the Warehouse class will call the getGoods() method on the Good class
      3.2.2 Lastly, the list of goods is printed to the command line.
### Total Goods Method
#### Description
Total Goods belongs as part of the Commands Class. It is used to show the total quantity of Goods Objects currently in the Warehouse.

#### Operation

![Regex Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/totalGoods.puml)

The above sequence diagram shows the operation of how the add goods method will be called.
1. The User input will be read by the User Interface Class
2. The User Interface Class will then match the command keyword `total`
3. Afterwards, the User Interface class will call totalGoods() method from the Commands Class
4. This method will then iterate through orderLists and sum up each quantity of each Good object in each order.
5. The totalGoods() method will return an integer of the number of goods in the warehouse. 

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
|v1.0|Warehouse Worker|Remove inventory items from the items list|so that I can track whenever a orderline is not in the warehouse|
|v1.0|Warehouse Worker|Get a list of all the inventory currently in the warehouse|so that I can see all the items we have in the warehouse|
<!-- |v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list| -->

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
