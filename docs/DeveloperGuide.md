# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Controllers
A `Controller` is defined as the user-facing part of the programme. It is in charge of getting
input from the user, as well as managing the user experience and interface, such as printing to the console.

All controllers are derived from the base abstract class `Controller`. This base class acts as a mini-framework, and
includes the base implementation for the input-loop. In this way, there is no need to rewrite input handling for all 
extended controllers. 

To create a new `Controller`, the new class simply extends from the `Controller` class. The `Controller` class is then provided
a list of strings indicating the choices available to the user. **Note that the first choice is always to exit the current controller.** 
The user will then select these choices using integers (the implementation is handled by the base class, including
input loops). The new class then implements this abstract method at the least:
- `protected abstract boolean optionSwitcher(int choice) throws OperationTerminationException`
  - This method is responsible for selecting the corresponding action to the user's selected choice.
  For more information and examples, refer to the `Controller` class' documentation, and `MainController` class for an
  example implementation.

By itself, `Controllers` are not useful. They should usually have an underlying `Manager` class for data storage and
manipulation.

### Managers
A `Manager` is defined as the data management part of the programme. It is in charge of manipulating underlying data and
takes care of data integrity.



### Menu (dishes) Management

The management of menu is functioned by `DishManager`, which implements following methods:

- `void printDishes()` -- print all dishes and their menu index in current menu.
- `void addDish(Dish dish)` -- add a new dish.
- `void deleteDish(int menuIndex)` -- delete a dish using its menu index.
- `void setPrice(int menuIndex, double newPrice)` -- change the price of a dish using its menu index.
- `void setName(int menuIndex, String newName)` -- change the name of a dish using its menu index.

These functions are "delegated" to `ArrayLise<Dish>`, which is associated with `DishManager`.

For example, `printDishes()`, iterates through `dishes` and calls `get(int)` method (`setPrice(int, double)`, `setName(int, String)` also call `get(int)`):

![](Sequence/DishManager_printDishes.png)

`deleteDish(int)` calls `remove(int)`:

![](Sequence/DishManager_deleteDish.png)

`addDish(Dish)` calls `add(Dish)`:

![](Sequence/DishManager_addDish.png)



## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|staff|modify the menu (add, delete, or modify dishes)|keep the menu up to date|
|v2.0|staff|store the menu|refer to the menu anytime|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
