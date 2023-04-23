# SISU

Welcome to SISU, a program for handling degree data to get an easy way to keep track of where students in Tampere University are at regarding completing a degree.

## Group information: Member and workshare

**Member 1:** Thuy Le, thuy.t.le@tuni.fi, 150533634, resposible for:
    1. Creating classes `DegreeProgramme`, `GroupingModule`, `StudyModule`, and `Course` (using the abstract class `DegreeModule`) to handle degree structures.
    2. Creating class `Student` to handle information of users in role of a student.
    3. Fetching data from Kori API and create degree modules or courses using fetched data by building class `Data` and interface `Builder`.
    4. Updating `README.md`to document the project.

**Member 2:** Elsa Seppänen, , , responsible for:
    1. Setting up the first dialogue interface of the application.
    2. 

**Member 3:** Eero Jormalainen, , , responsible for:
    1. Reading and writing to a file with class `SisuReaderWriter` to save or read the progress of each student.
    2. 

## Description

Sisu is a program for planning and keeping track of university studies. The program able users to investigate the degree structures of study programmes at Tampere University.

- Only English interface is implemented in this version.
- A user setting tab view is implemented: User can view their own information and change their expected graduation year (if needed).
- Only courses that are still available for students (whose minimum credits are not 0) are fetched for users to choose.
- The user’s progress over their studies can be viewed. 
- The user can define their study programme and mark courses completed as studies go forward.

## Project structure

The **UML class diagram** of our application: `./class-diagram.png`. Classes are also documented using javadocs. The documentation files are available in `./target/site/apidocs`.

## Tests

