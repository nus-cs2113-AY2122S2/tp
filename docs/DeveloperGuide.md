# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}


## Product scope
### Target user profile

- Studies in a non-IT field
- Usually works alone
- Working in a small travel agency that offers affordable budget travel packages to only some countries
- Handles many customers a day
- Is unfamiliar with using CLI apps

### Value proposition

- Travel agencies often have to manage multiple customers and their respective bookings or plans.
- For employees of the travel agencies to track and record customers’ booking of travel packages. 


## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|Add reservation for a customer with basic information such as name, country etc. |Make a reservation|
|v1.0|new user|Print a list of all current and available travel packages |View all current travel packages and tell customers about our travel packages with one look|
|v1.0|new user|Remove an existing reservation|Remove information that we do not need anymore|
|v1.0|User ready to start using the app| Find out which travel packages are available for the specific location and duration|Make a recommendation of travel package to customers based on their desired location|
|v1.0|new user|Search for a specific travel package|Make recommendations to a customer based on their desired travel package requirements|
|v2.0|User ready to start using the app|Upload existing reservation data|Get started quickly|
|v2.0|User ready to start using the app|Make reservations based on custom input and edit them where necessary|Get familiar with inputting|
|v2.0|User ready to start using the app|See the changes I made from the previous day|Continue working|
|v2.1|Expert user|Create and use shortcuts|Quickly enter the details needed for a reservation and query any details I might need for an existing reservation|
|v2.1|Expert user|Update ratings of travel packages based on customer feedback|Provide a better recommendations for future customers|
|v2.1|Expert user|Ascertain the error in my input based on the error messages from the application|Quickly troubleshoot any mistakes that would slow down my work|
|v2.1|Expert user|View all reservations currently under a specific travel package|Optimize that travel package according to user’s feedback|
|v2.1|Expert user|I can build a customized travel package based on what the customer wants|It appeals to customers who do not want our existing tour packages|
|v2.1|Expert user|Add in hotels to the list of available hotel options|Update the itinerary |
|v2.1|Expert user|Add in countries to the list of available countries|Update the itinerary|
|v2.1|Expert user|Sort all reservations according to month|Analyze trends throughout the year|
|v2.1|Expert user|Mark a reservation as ‘completed’|Access both completed and ongoing reservations|
|v2.1|Expert user|Compute the price of custom travel packages|Get a rough estimate for what to charge the customer|

## Non-Functional Requirements

### Performance Requirements
- Should work on any mainstream OS as long as it has Java 11 or above installed.

### Usability
- A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
- Prompts and instructions must be in proper and concise sentences for ease of understanding.

## Glossary

* *glossary item* - Definition
* Packages - Travel Package within the agency's database
* Reservations - Reservation of travel package made by one customer through the app 

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Summary of User Commands

| Command | Format Examples |
| ------- | --------------- |
|packages|packages|
|info|info {num} (num < number of available packages) <br/> e.g. info 2 |
|add|add {package_name} {country} {duration} {price} {vacancies} <br/>  e.g. add Skiing_Trip Sweden 15/2/2022-19/2/2022 800 100|
|delete|delete {num} (num < number of available packages) <br/> e.g. delete 2|
|reserve|reserve {package_number} {contact_name} {contact_number} {number_pax} <br/> e..g reserve 3 John 91234567 3 |
|remove|remove {reservation_id} <br/> e..g remove R001|
|reservations|reservations {package_number} <br/> eg. reservations 2|

## Feature - Help Command 
Aim: Displays a list of all available commands that the user can refer to as a guide

## Feature - Saved files
Aim: Allows users to save and load up existing reservations and travel packages from a text file
![](storage sequence diagram.png)

