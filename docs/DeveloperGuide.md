# Developer Guide

## Table of contents
* [Design](#design)
  * [Unit Good Class](#unit-good-class)
  * [Good Class](#good-class)
  * [Order Class](#order-class)
  * [Orderline Class](#orderline-class)
  * [Warehouse Class](#warehouse-class)
  * [Command Parser](#command-parser)
  * [User Interface Class](#user-interface-class)
  * [Match Keyword Class](#match-keyword-class)
* [Implementation](#implementation)
  * [View Order Feature](#view-order)
  * [Fulfill Order Feature](#fulfill-order)
* [Product Scope](#product-scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
  * [User Stories](#user-stories)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [Instructions for Manual Testing](#instructions-for-manual-testing)

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

### Unit Good Class
The Unit good class keep track of the unit goods in the warehouse.
#### Description
Unit goods will be stored as a HashMap<String, UnitGood> in the warehouse with the key being a unique SKU as a string and the value is the actual Unit Good.

A unit good is a template good, holding the specific details such as SKU, name, description and [Capacity](#capacity-enumeration).

![Unit Good Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/UnitGood.puml)

When a Unit Good is added, Simplst will also add a Good of the same SKU with 0 quantity. This is to indicate that Simplst knows that this Unit Good is tied to the corresponding Good of the same SKU.

To update a Unit Good details, there is a need to first remove the Unit Good from the HashMap, then add a new instance of the Unit Good.
This design is intentional to ensure that existing Unit Goods will not be incorrectly overwritten.

### Good Class
A Goods class will extend the unit good class.
#### Description
Similar to [Unit Goods](#unit-good-class), the Goods will be stored as a LinkedHashMap<String, Good> in the warehouse with the key being the SKU as a string and the value is the actual Good.

The Good Class contains the extra variable of quantity which is meant to show the current quantity the warehouse contains in its inventory now.

![Good Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Good.puml)

When a good is being added into Simplst, it will first check for its corresponding UnitGood by checking if a UnitGood with the same SKU is known by Simplst.
Simplst will then add the quantity specified to the LinkedHashMap of Goods stored in the warehouse.

If the SKU is not known, this means that a UnitGood of the same SKU has to be added first.

#### Capacity Enumeration
The Capacity enum is meant as a heuristic to determine the size of a unit good and good.
##### Description
This enum will have 3 values:
- SMALL
- MEDIUM
- LARGE

The capacity SMALL will be 1 unit of size, MEDIUM will be 2 units of size and finally LARGE will be 3 units in size.
A diagram can be seen in the [Architecture Class Diagram](#design)

### Order Class
The Order Class is used to imitate how orders will be stored in Simplst.
#### Description
![Order Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Order.puml)

Orders will contain the Order ID which will be used to identity the order. The Order ID has to be a unique positive number.
As seen in the class diagram above, Orders will hold a collection of [Orderlines](#orderline-class). This will be used to indicate what goods are required to fulfill the Order.

To [fulfill](#fulfill-parser) an order, this would go through all the orderlines in that order and attempt to fulfill them.
When all orderlines in the order are fulfilled, this will indicate that the order is fulfilled.

### Orderline Class
A Orderline class will extend the Goods class.
#### Description
![Orderline Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Orderline.puml)

Orderline will contain the extra variable of quantityFulfilled and isCheckedOff as compared to the [Goods Class](#good-class).
The quantity variable will be to indicate the quantity required to fulfill the current order. This is different as compared to in the Goods Class which indicates the current quantity in the warehouse.
The quantity fulfilled variable will be to indicate the quantity currently fulfilled for that order. When the quantity fulfilled equals to the quantity required, the variable isCheckedOff will be true.
This is to indicate that this orderline is fulfilled in that order, and it does not require any more of the good.

### Warehouse Class
The warehouse class is created to simulate an entire warehouse.
#### Description
![Warehouse Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Warehouse.puml)

Most of the methods in the Warehouse Class will require users to know the SKU of the Good that they want to interact with.

The find method within the Find Parser is aimed at helping users find the SKU of Good that they need.

The warehouse class will contain:
- List of orders
  - An arraylist is chosen to store the orders.
  - This is so as to store the ordering of orders being added.
- HashMap of unitgoods
  - A hashmap is chosen for UnitGoods with the <key, value> pair as <SKU, UnitGood>.
  - This is to allow for quick access to the details of the unit good through knowing the SKU
- LinkedHashMap of goods
  - A linked hashmap is chosen for goods to ensure that we are able to maintain the ordering of goods added while having the simplicity of accessing the necessary goods through their SKU

For more information of the methods regarding the Warehouse Class, check the [UserGuide](/UserGuide.md) to see what features are available to each of the above Classes.
### Display Class
Display class is used to store and display different messages to user.
#### Description
![Display Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/Display.puml)

By calling different methods of Display class, Simplst can print out different messages for users.

### UserInterface Class
UserInterface class is used to take user input and pass it to the corresponding command parser.

#### Description
![UserInterface Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/UserInterface.puml)

The main method in UserInterface is the run() method. This would choose what which Parser to send the user's input to based on the 1st word of the user's input.
Below are the steps taken:

1. After receiving user input, the first part of the command will be used to determine what is the command type of the input. 
2. It will pass the command to the corresponding command parser. 
3. After executing the commannd, user will be asked to input another command until the program is terminated.
4. This will continue until the user inputs `bye`, no other command will be taken and the program will be terminated.

### Command Parser
#### Description
This is the description for the Command Parser. This is an abstract class which all other parser classes will implement

![Command Parser Class Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/CommandParser.puml)

Each parser class will use regular expressions done in the [Match Keyword Class](#match-keyword-class) to obtain a flag
such that the program knows which operation to perform, whether it is a command related to Unit Good, Order, etc. The 
parsers will then perform another series of us of regular expressions to obtain more specific details of the command
such that it can properly execute the command.


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

## Implementation
Some features will be explained here with more explanation. View Order will be a simple feature, similar to many other features such as adding, removing or both order and goods. It will be used as an overarching example of how these similar features will run in Simplst. Fulfill feature is much more complex and hence it will be explained here too.
### View Order
This feature can be used to view a current order in the warehouse
#### Description
View Order will require the [Warehouse Class](#warehouse-class) to find the corresponding [Order](#order-class) with the user input order id.
The order will then print out its order id, receiver, shipping address, and details of orderlines.

#### Operation

![View Order Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/ViewOrder.puml)

The above sequence diagram shows the operation of how the view order feature will be called.
1. The User input will be read by the User Interface Class
2. The [User Interface Class](#userinterface-class) will then match the command keyword `view`
3. The User Interface Class will hand over to the [View Parser](#view-parser)
4. The [Match Keyword Class](#match-keyword-class) will then be call to match the Order id number to be viewed.
5. The View Parser will now call viewOrderById(String oid) method within the warehouse class with the order id number received from the Match Keyword Class
6. This will then run a loop of the orders in the orderLists of the warehouse to find the matching Order using the order id. Since order id is unique, only 1 order can be matched here
7. If an order is found, viewOrderById() will call multiple getter classes from order to get the orderId, receiver name, shipping address and orderlines associated
  - these are done through getId(), getReceiver(), getShippingAddress() and getOrderlines() respectively
8. These details will then be printed out to the user and marks the end of view order feature.

### Fulfill Order
This feature will help to fulfill an order that is currently in the warehouse. More description can be found [here](/UserGuide.md)
#### Description
Fulfill Order will require the [Warehouse Class](#warehouse-class) to first find the corresponding [Order](#order-class) to fulfill. 
Then it will check all the [Orderlines](#orderline-class) in that Order to check if they are already fulfilled. 
If they are not, an attempt will be made to fulfill them by checking against the current quantity of the corresponding [Good](#good-class) in the warehouse. 
If there is enough quantity of the good in the warehouse to fulfill the orderline, then the orderline will be checked off and fulfilled, reducing the quantity of the Good in the warehouse.
If there not enough quantity of the good in the Warehouse, then nothing will happen and the orderline will be considered as not fulfill.
After going through all the orderlines, the order will only be fulfilled if all the orderlines are fulfilled.

Fulfill can be called on any order which is not fulfilled, or partially fulfilled.
A step by step description of how the fulfill feature can be seen below

#### Operation

![Fulfill Order Sequence Diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/AY2122S2-CS2113T-T09-4/tp/master/docs/diagrams/FulfillSequence.puml)

The above sequence diagram shows the operation of how the fulfill feature will be called. 
1. The User input will be read by the User Interface Class
2. The [User Interface Class](#userinterface-class) will then match the command keyword `fulfill`
3. The User Interface Class will hand over to the [Fulfill Parser](#fulfill-parser)
4. The [Match Keyword Class](#match-keyword-class) will then be call to match the Order id number to be fulfilled.
5. The Fulfill Parser will now call fulfillOrder(String oid) method within the warehouse class with the order id number received from the Match Keyword Class
6. This method will first find the corresponding order with the given order id in the warehouse by calling findOrder()
7. If the order exists, then it will return the corresponding order
8. Next thing is to check if the current order in question is already fulfilled, if it is, then fulfillOrder() will return immediately as there is nothing more to do
9. We will then get the orderlines associated with this order through order.getOrderlines()
10. fulfillOrder() will then loop for each orderline in orderlines. It will call the helper function fulfillOrderlines() for each orderline
11. fulfillOrderlines() will aim to individually fulfill each orderline. This is done by checking if there is enough quantity of Goods with the same SKU as the orderline. In this instance, enough is defined as having more or equal to the quantity required by that orderline.
12. If the above condition is met, setQuantityFulfilled() will be called for that orderline to set the quantityFulfilled variable in the orderline
13. setQuantityFulfilled() will call checkOff() to set the isFulfilled variable in the orderline if quantity is equal to quantityFulfilled
14. fulfillOrder() will then call removeQuantity() of the corresponding Good from the inventory in the warehouse
15. fulfillOrder() will now call checkOrderComplete() to check if all the orderlines in the order are fulfilled
16. If they are, checkOrderComplete() will set the order to fulfilled as well
17. The result of whether the order is fulfilled or not will then be printed out to the user and marks the end of fulfill order feature.

For more examples of how a user can use a command, refer to the [UserGuide](/UserGuide.md)

<!-- ### Remove Goods Method
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

For more examples of how a user can use a command, refer to the [UserGuide](/UserGuide.md) -->

<!-- ### List Goods or List Orders Method
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
      3.2.2 Lastly, the list of goods is printed to the command line. -->

## Product scope
### Target user profile

Target user profile: Warehouse Workers
* has a need to update the warehouse on current stock of goods
* has a need to view the details of specific goods
* can type fast
* is reasonably comfortable using CLI apps

Target user profile: Warehouse Managers
* has a need to update warehouse and workers on new orders
* can type fast
* is reasonably comfortable using CLI apps

### Value proposition

A cheap, user-friendly Warehouse Management System with intuitive commands to improve efficiency.

## User Stories

| Version | As a ...          | I want to ...                                           | So that I can ...                                                                         |
|---------|-------------------|---------------------------------------------------------|-------------------------------------------------------------------------------------------|
| v1.0    | Warehouse Worker  | Get the total number of goods in the warehouse          | its less tiring to have to count the total number of goods in the warehouse               |
| v1.0    | Warehouse Worker  | View the description of goods easily                    | it would be easier for stocktaking                                                        |
| v1.0    | Warehouse Worker  | Add Goods to the items list                             | so that I can keep track of goods entering the warehouse                                  |
| v1.0    | Warehouse Worker  | Remove Goods from the items list                        | so that I can track whenever a Good is not in the warehouse                               |
| v1.0    | Warehouse Worker  | Get a list of all the Goods currently in the warehouse  | so that I can see all the items we have in the warehouse                                  |
| v2.0    | Warehouse Worker  | Find a certain Good currently in the warehouse by name  | so that I can easily find a certain Good we have in the warehouse                         |
| v2.0    | Warehouse Worker  | Get a list of all the orders currently in the warehouse | so that I can see all the orders we have in the warehouse                                 |
| v2.0    | Warehouse Worker  | Get a list of all the orderlines in an order            | so that I can see goods required to fulfill the order                                     |
| v2.0    | Warehouse Manager | Add an order to the warehouse                           | so that I can inform warehouse workers on what orders the warehouse requires to fulfill   |
| v2.0    | Warehouse Worker  | Fulfill a completed Order                               | so that I know which order does not require more goods and is completed to be shipped out |
| v2.0    | Warehouse Worker  | View a specific order                                   | so that I can see all the details regarding a certain order in mind                       |

## Non-Functional Requirements

Device Requirement:
* Must have Java 11 or higher installed (Recommended to use Java 11)
* Supports 32-bit and 64-bit systems
* Supports use of the Command Line Interface

Application Performance:
* Does not require internet connection; Functions offline
* Quick and easy to launch and use
* Responds to a command within 3 seconds
* Provides function to save and load JSON files

Application Reliability:
* Data should be saved and stored accurately
* Same input of a command should result in the same outcomes
* Data storage will function normally as long as the application is not forced to close
* Simplst should be able to hold data of up to hundreds of Unit Goods, Goods and Orders simultaneously

## Glossary

* *SKU* - Stock-Keeping Unit. It is the unique unit number for a specific warehouse item. It can contain characters and numbers (e.g WC01).
* *[Unit Good](#unit-good-class)* - Template for creating a Good. Holds details of a certain Good.
* *[Good](#good-class)* - Good to be stored in the warehouse
* *[Order](#order-class)* - Information about what goods are delivered to who and where
* *[Orderline](#orderline-class)* - The goods that needs to be delivered in that order

## Instructions for manual testing

Following commands from the [User Guide](/UserGuide.md) can give a rough overview of how Simplst will handle the commands.
Here is a quick guide on example commands to try.

| Action                               | Command to be keyed into the terminal                        |
|--------------------------------------|--------------------------------------------------------------|
| Add Unit Good                        | `add ug/ sku/SKU01 n/Metal Chair d/Made of steel size/small` |
| Remove Unit Good                     | `remove ug/ sku/SKU01`                                       |
| List Unit Good                       | `list ug/`                                                   |
| Add Good quantity                    | `add g/ sku/SKU01 qty/30`                                    |
| Remove Good quantity                 | `remove g/ sku/SKU01 qty/10`                                 |
| List Goods (with quantity displayed) | `list g/`                                                    |
| Viewing a Good                       | `view g/ sku/SKU01`                                          |
| Find Good                            | `find n/chair`                                               |
| Add Order                            | `add o/ oid/1 r/Mirana addr/Radiant Fountain`                |
| Remove Order                         | `remove o/ oid/1`                                            |
| List Orders                          | `list o/`                                                    |
| View Order                           | `view o/ oid/1`                                              |
| Fulfill Order                        | `fulfill oid/1`                                              |
| Add Orderline                        | `add og/ oid/1 sku/SKU01 q/10`                               | 
| Remove Orderline Quantity            | `remove og/ oid/1 sku/SKU01 q/5`                             |
| List Orderlines                      | `list og/ oid/1`                                             |
