# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Order Management

The management of menu is functioned by `OrderManager`, which implements following methods:

- `void addOrder(Order order)` -- add a new order.
- `int addDishToOrder(Object inputObj)` -- add dishes in to an order.
- `void deleteOrder(int orderIndex)` -- delete an order using its order index.
- `Order getOrder(int orderIndex)` -- get an order using its order index.
- `double getOrderPrice(int orderIndex)` -- get the total price of an order using its order index.
- `double getAllOrderValue()` -- get the total price of all orders.

These functions are "delegated" to `ArrayLise<Order>`, which is associated with `OrderManager`.

For example, `getOrder(int)`, iterates through `orders` and calls `get(int)` method (`getOrderPrice(int)`, `getAllOrderValue()` also call `get(int)`):


`deleteOrder(int)` calls `remove(int)`:


`getOrderPrice(int)` calls `getTotalPrice()` and method `getAllOrderValue()` also calls `getTotalPrice()`:


## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ...                                  | So that I can ...          |
|--------|----------|------------------------------------------------|----------------------------|
|v1.0|staff| modify the order (add, delete, or check price) | keep the order up to date  |
|v2.0|staff| print out the order receipt                    | refer to the order anytime |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
