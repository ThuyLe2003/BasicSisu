# SISU

Welcome to SISU, a program for handling degree data to get an easy way to keep track of where students in Tampere University are at regarding completing a degree.

## Group information: Member and workshare

**Member 1:** Thuy Le, thuy.t.le@tuni.fi, 150533634, responsible for:
    1. Creating classes `DegreeProgramme`, `GroupingModule`, `StudyModule`, and `Course` (using the abstract class `DegreeModule`) to handle degree structures.
    2. Creating class `Student` to handle information of users in role of a student.
    3. Fetching data from Kori API and create degree modules or courses using fetched data by building class `Data` and interface `Builder`.
    4. Updating `README.md`and writing the documentation for the project.

**Member 2:** Elsa Seppänen, , , responsible for:
    1. Setting up the first dialogue interface of the application.
    2. Setting up the interface for the application after logging in.
    3. Handling the flow of the application from the interface.

**Member 3:** Eero Jormalainen, , , responsible for:
    1. Reading and writing to a file with class `JsonReaderWriter` to save or read the progress of each student.
    2. Implementing unit tests for all the classes.
    3. Implementing unit tests for the graphical user interface.


## Description

Sisu is a program for planning and keeping track of university studies. The program able users to investigate the degree structures of study programmes at Tampere University.

-	Only English interface is implemented in this version.
-	When the user opens Sisu application, a first dialogue view will appear for logging in or signing up.
-	A student setting tab view is implemented: Users can view their own information and change their expected graduation year (if needed). In this view, they can also choose or change their degree programme.
-	Only courses that are still available for students (whose minimum credits are not 0) are fetched for users to choose.
-	The users’ progress over their studies can be viewed. 
-	The users can mark courses completed as studies go forward.

## Project structure

The **UML class diagram** of our application: `./class-diagram.png`. Classes are also documented using javadocs. The documentation files are available in `./target/site/apidocs`.

## Tests

