# User Guide

## Introduction

{Give a product intro}

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}

### Creating a session: `session /create`
>Creates a new session to be managed by SplitLah. <br> 
>Sessions are a way for the user to manage their gatherings that happen across the day.

Format: `session /create /n [SESSION_NAME] /d [SESSION_DATE] /pl [NAME1 NAME2 ...]`

* `[SESSION_NAME]` refers to the name of the session.
  * The session name is **case-insensitive**.
* `[SESSION_DATE]` refers to the date of the session.
  * The format of the date can be in `DD-MM-YYYY` or `YYYY-MM-DD`.
* `[NAME1 NAME2 ...]` refers to a list of persons involved in the session.
  * The person names are **case-insensitive**.

> **Notes:**
>- The `[SESSION_NAME]` should be unique across sessions.
>- Each name in `[NAME1 NAME2 ...]` for the session should be unique.

Example of usage:
1. Adds a new session named Class Outing with Alice and Bob involved on 2022-03-15.
   1. `session /create /n Class Outing /d 2022-03-15 /pl Alice Bob`
   
<br>[INSERT SCREEN SHOT]


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
