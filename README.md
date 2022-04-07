# SplitLah

This java project was made as a requirement for CS2113T. Our application, _SplitLah_, is a bill splitting application. 
Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Ensure Intellij JDK 11 is defined as an SDK**, as described [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk) -- this step is not needed if you have used JDK 11 in a previous Intellij project.
   * In the same dialog, you _may have to set the Project language level_ field to the SDK default option.
2. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
3. **Verify the set up**: After the importing is complete, locate the `src/main/java/seedu/splitlah/SplitLah.java` file, right-click it, and choose `Run SplitLah.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes
   
   > Task :SplitLah.main()
   The data file was not found or has been corrupted. No saves were loaded into SplitLah
   ============================================================
   $$$$$$\            $$\ $$\   $$\     $$\                $$\       
   $$  __$$\           $$ |\__|  $$ |    $$ |               $$ |      
   $$ /  \__| $$$$$$\  $$ |$$\ $$$$$$\   $$ |      $$$$$$\  $$$$$$$\  
   \$$$$$$\  $$  __$$\ $$ |$$ |\_$$  _|  $$ |      \____$$\ $$  __$$\
   \____$$\ $$ /  $$ |$$ |$$ |  $$ |    $$ |      $$$$$$$ |$$ |  $$ |
   $$\   $$ |$$ |  $$ |$$ |$$ |  $$ |$$\ $$ |     $$  __$$ |$$ |  $$ |
   \$$$$$$  |$$$$$$$  |$$ |$$ |  \$$$$  |$$$$$$$$\\$$$$$$$ |$$ |  $$ |
   \______/ $$  ____/ \__|\__|   \____/ \________|\_______|\__|  \__|
   $$ |                                                     
   $$ |                                                     
   \__|
   Welcome to SplitLah!
   ============================================================
   >
   ```
   Please refer to our [user guide](#https://ay2122s2-cs2113t-t10-1.github.io/tp/UserGuide.html) for the full list of commands to interact with SplitLah.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.
