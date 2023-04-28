# SISU

Welcome to SISU, a program for handling degree data to get an easy way to keep track of where students in Tampere University are at regarding completing a degree.

## Group information: Member and workshare

**Member 1:** Thuy Le, thuy.t.le@tuni.fi, 150533634, responsible for:
-	Implementing classes `DegreeProgramme`, `GroupingModule`, `StudyModule`, and `Course` (using the abstract class `DegreeModule`) to handle degree structures. Implementing class `Student` to handle information of users in role of a student.
-	Implementing unit tests for `fi.tuni.prog3.sisu.modules` package.
-	Fetching data from Kori API and getting degree modules or courses using fetched data by building class `Data` and interface `Builder`.
-	Combining the two views for the application after logging in.
-	Updating `README.md` and writing the documentation for the project.

**Member 2:** Elsa Seppänen, elsa.seppanen@tuni.fi, H299913, responsible for:
-	Setting up the first dialogue interface of the application.
-	Setting up the student information view for the application after logging in.
-	Writing user manual for the application.

**Member 3:** Eero Jormalainen, eero.jormalainen@tuni.fi, H292260, responsible for:
-	Reading and writing to a file with class `JsonReaderWriter` to save or read the progress of each student.
-	Implementing unit tests for `JsonReaderWriter` class.
-	Setting up the curriculum view for the application after logging in.

## Description

Sisu is a program for planning and keeping track of university studies. The program able users to investigate the degree structures of study programmes at Tampere University.

-	Only English interface is implemented in this version.
-	When the users open Sisu application, a first dialogue view will appear for logging in or signing up.
-	A student information tab view is implemented for use after the users have logged in: Users can view their own information. In this view, they can also choose or change their degree programme.
-	Only degrees that are still available for students (whose minimum credits are not 0) are fetched for users to choose.
-	The users’ progress over their studies can be viewed from the student information tab view. 
-	The users can mark courses completed as studies go forward.
-	When the users select a course that has not been completed from the curriculum view, its details about completion methods will be shown.

## Project structure

The **UML class diagram** of our application: `Documentation/class-diagram.jpg`. Classes are also documented using javadocs. The documentation files are available in `./target/site/apidocs`.

## Tests
We used our self-defined tests to test the functions of the key classes `JsonReaderWriter`, `Student` and classes in package `fi.tuni.prog3.sisu.modules`.

## Documentation

The project documentation of our application is available at `Documentation/Sisu-documentation.pdf`.
