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

####Implementation
The tag command accepts a string from the user and adds it into `ArrayList<String>` of a `Task`.

Below is the sequence diagram of how the tag feature works:
![Tag](https://user-images.githubusercontent.com/70083643/159422057-e99fed0b-dd81-407b-94a2-186f372eba04.png)
![GetModule](https://user-images.githubusercontent.com/70083643/159422213-f5b0c533-d904-4db0-9408-a1c21c03ad9b.png)
![ExecuteTagOp](https://user-images.githubusercontent.com/70083643/159422225-91f779e0-2efa-42f9-8ee3-e6c612d46753.png)

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
