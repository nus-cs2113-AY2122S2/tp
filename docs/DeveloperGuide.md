# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Command
`Command` is an abstract class that sets certain commonalities that is implemented across all types of commands - `AddCommand`, `DescCommand`, `ListCommand`, `DeleteCommand`, `HelpCommand`, `ExitCommand`. Each of these classes have to override the `Command`'s `execute()` method as each command has a different execution. For example, `AddCommand` will be focused on adding an item to an inventory list whereas `DescCommand` will be about retrieving information from the inventory list.

This is illustrated with the class diagram for `Command` below.

![CommandClassDiagram](img/CommandClassDiagram.png)

### DescCommand
For a user who is unaware of what an item is about, he/she can enter the command eg. `desc 2` command to extract the description for the second item in the inventory list. This command is interpreted by the `Parser` and a `DescCommand` is returned to `InvMgr`. `InvMgr` calls the execute command of `DescCommand` which retrieves the item's information from the `ItemList` and then outputs them into the `Ui` for the user to see.

This is illustrated with the sequence diagram for `DescCommand` below.

![DescCommandSequenceDiagram](img/DescCommandSequenceDiagram.png)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ...                               | I want to ...                                   | So that I can ...                                            |
|---------|----------------------------------------|-------------------------------------------------|--------------------------------------------------------------|
| v1.0    | Manager                                | Add a new item to the inventory                 | Update my inventory                                          |
| v1.0    | Manager                                | Remove an item from the inventory               | Update my inventory                                          |
| v1.0    | Stocktaker                             | list out all my items                           | View all my items at a glance                                |
| v1.0    | New user                               | List out all possible commands                  | I can familiarise myself with using the system               |
| v1.0    | User who has not seen items physically | Get the description of a particular item        | I can visualise the item better to know if it is what i need |
| v1.0    | As a frequent/first time user          | Write to a file containing the entire inventory | Save my inventory data to a file                             |
| v1.0    | Stocktaker                             | Read from and load an inventory file data       | To work on and view the data                                 |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
