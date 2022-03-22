# Developer Guide

## Design
### Architecture
Given below is a quick overview of the main components of Mod Happy and how they interact with one another.
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Ch40gRv1-Mu/tp/branch-A-UML/docs/Components.puml)

### Parser Component
![Class Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/Ch40gRv1-Mu/tp/branch-A-UML/docs/Parser.puml)
## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Tag Feature

The tag command accepts a string from the user and adds it into `ArrayList<String> tags` of a `Task`.  

Here is an example on adding a tag to a general task:  
1) User inputs `tag add 2 "testTag"`. 
2) `TagParser` will initialise `TagCommand` with add as `tagOperation` 2 as `taskIndex` and testTag as `tagDescription`, while `taskModule` is null.
3) `TagCommand` then gets the relevant `Module`. If `taskModule` is null, `getGeneralTasks()` is called. Else, `getModule(taskModule)` is called instead.
4) Next, `TagCommand` checks the `tagOperation`. If add, `addTag(targetModule)` is called. Else if del, `removeTag(targetModule)` is called. Else, it throws `ParseException`.

Below is the sequence diagram of how the tag feature works:

![Tag](https://user-images.githubusercontent.com/70083643/159491805-2cffefd1-73d6-4d3c-8098-ef7ff46acfd6.png)
![GetModule](https://user-images.githubusercontent.com/70083643/159489557-d82be092-7570-459c-b933-5d1497490b98.png)
![ExecuteTagOp](https://user-images.githubusercontent.com/70083643/159489602-2a88ed8f-3227-4f06-bf4a-d1f743d60856.png)

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
