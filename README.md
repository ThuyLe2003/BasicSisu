# SISU

Welcome to SISU, a program for handling degree data to get an easy way to keep track of where students in Tampere University are at regarding completing a degree.

## Group information: Member and workshare

**Member 1:** Thuy Le, thuy.t.le@tuni.fi, 150533634, responsible for:
    1. Implementing classes `DegreeProgramme`, `GroupingModule`, `StudyModule`, and `Course` (using the abstract class `DegreeModule`) to handle degree structures. Implementing class `Student` to handle information of users in role of a student.
    2. Implementing unit tests for `fi.tuni.prog3.sisu.modules` package.
    3. Fetching data from Kori API and getting degree modules or courses using fetched data by building class `Data` and interface `Builder`.
    4. Updating `README.md` and writing the documentation for the project.

**Member 2:** Elsa Seppänen, elsa.seppanen@tuni.fi, H299913, responsible for:
    1. Setting up the first dialogue interface of the application.
    2. Setting up the student settings view for the application after logging in.
    3. Working with Eero Jormalainen in implementing unit tests for the interface.

**Member 3:** Eero Jormalainen, eero.jormalainen@tuni.fi, H292260, responsible for:
    1. Reading and writing to a file with class `JsonReaderWriter` to save or read the progress of each student.
    2. Implementing unit tests for `JsonReaderWriter` class.
    3. Setting up the curriculum view for the application after logging in.
    4. Working with Elsa Seppänen in implementing unit tests for the interface.

## Description

Sisu is a program for planning and keeping track of university studies. The program able users to investigate the degree structures of study programmes at Tampere University.

-	Only English interface is implemented in this version.
-	When the user opens Sisu application, a first dialogue view will appear for logging in or signing up.
-	A student setting tab view is implemented: Users can view their own information and change their expected graduation year (if needed). In this view, they can also choose or change their degree programme.
-	Only courses that are still available for students (whose minimum credits are not 0) are fetched for users to choose.
-	The users’ progress over their studies can be viewed. 
-	The users can mark courses completed as studies go forward.

## Project structure

The **UML class diagram** of our application: `./class-diagram.jpg`. Classes are also documented using javadocs. The documentation files are available in `./target/site/apidocs`.

## Tests
We used our self-defined tests to test all the functions of the key classes `Student` and classes in package `fi.tuni.prog3.sisu.modules`.
