# Falicia Ong 's Project Portfolio Page

## Project: Hotel Lite 

Hotel Lite - A convenient tool for hotel owners to manage their businesses.
The user interacts with it using a CLI. It is written in Java and has about 7kLoC.

Given below are my contribution to the projects:

* __New Feature__: Implement the ability to add housekeeper into list.
    * What it does: Allows user to add newly join housekeeper into the list.
    * Justification: User should be able to add housekeeper into the list to store information.
    * Highlights: 
      * Information given by the user through add housekeeper command will be used for collating
    all housekeeper's availability based on day in the week and making sure housekeeper is within the age limit.
      * The name given would be used in the assignment of housekeeper to their respective rooms needed to be cleaned. 
      * The name given would also be used to record their performance.


* __New Feature__: Implement the ability to add/update availability of housekeeper.
    * What it does: Allow user to add/update availability of housekeeper.
    * Justification: This feature will allow user to update the available days of housekeeper, which would be useful
      to keep track of how many housekeepers is available any day in the week.
    * Highlights: 
      * User is able to easily and efficiently type out the available days in the week of the housekeeper by
    indicating the days in number instead of typing out the name of day.
      * User can enter many availabilities in one command just by differentiating the different days using commas.
      * User can afford to enter duplicated days as Hotel Lite would be able to detect the duplication of days and
        regard it as one.
      


* __New Feature__: Implement the ability to view list of housekeeper.
    * What it does: Shows the full list of housekeeper currently working in the hotel. Information includes their name,
      age and availabilities.
    * Justification: This provides the user with a comprehensive list of all available housekeepers.


* __New Feature__: Implement the ability to get available housekeeper any day from Monday to Sunday.
    * What it does: Shows user the housekeeper available on a day in the week.
    * Justification: If a user needs to know if there will be enough housekeeping on a given day, he or she may simply
      utilize this function to get the names of the available housekeepers. This would assist the user in determining 
      whether extra housekeepers are required to complete the day's tasks efficiently.
    * Highlights: Able to quickly type out the command by indicate the day in the week with integers instead of typing
      out the name of the day


* __New Feature__: Implement the ability to Reset Housekeeper availability when a new week begins.
    * What it does: When a new week begins, user can reset every housekeeper availability using this feature
    * Justification: This is such that user will not have to re-update every housekeeper availability.


* __New Feature__: Implement the ability to Increment every housekeeper age when a new year begins.
    * What it does: Increment every housekeeper's age in the list by one when a new year begins.
    * Justification: When a new year begins, everyone ages would be updated by increasing by one. User
      do not have to go through each housekeeper to update their age.
    * Highlights: It will automatically remove and inform the user that the housekeeper has exceeded the age limit.


* __New Feature__: Implement the ability to Delete housekeeper from records.
    * What it does: Delete records of the housekeeper from the list.
    * Justification: This is needed when the housekeeper resigns.
    * Highlights: Gives total pax of housekeeper left after removal to alert the user such that
      the hotel will not be lack of manpower. 
    

* __Code Contribution__: Link to code [here](https://nus-cs2113-ay2122s2.github.io/tp-dashboard/?search=faliciaong&breakdown=true)


* __Contribution to UG__:
    * Did up a simple logo for the application.
    * Suggested the use of Links/Content Page.
    * Write-up for
        * add housekeeper,
        * add/update availability of housekeeper,
        * view recorded housekeeper,
        * get available housekeeper any day from Monday to Sunday,
        * Reset Housekeeper availability when a new week begins,
        * Increment every housekeeper age when a new year begins,
        * Delete housekeeper from records.
    * Command Summary Table to summary seven commands.


* __Contribution to DG__:
    * Create a Class Diagram for add housekeeper command
    * Create a Sequence Diagram for
        * Add housekeeper command
        * Delete housekeeper command


* __Contribution to team-based tasks__:
    * Created Command Parser,
    * Did up and compiled User Stories for version 1,
    * Added links into README.md,
    * Update Description of Hotel Lite Manager in README.md
    * Disable logging


* __Enhancements to existing features__:
    * Added logging into the code such that I am able to record the events that has occurred
    * Implemented assertion in code to test assumptions on program such as testing the age and day range
    * Implement Regular Expression (REGEX) to ensure naming of the housekeeper does not accept symbols and number
    * Implement commands to be case-insensitive


* __Community__:
    * Reported bugs and suggestions for other teams in the class 
    * Suggested and review other team's DG
         * [Reviewed Group T09-3](https://github.com/nus-cs2113-AY2122S2/tp/pull/3/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
         * [Reviewed Group F12-2](https://github.com/nus-cs2113-AY2122S2/tp/pull/21/files#diff-1a95edf069a4136e9cb71bee758b0dc86996f6051f0d438ec2c424557de7160b)
    
    
    