# LittlePrologInterpreter
An interactive Android application for high-school students to learn the Prolog programming language.

Overview of the product and project:

LittleProlog is a friendly and interactive Android application that is targeted at high school students and teachers to assist and improve the learning experience of learning the fundamentals of the Prolog programming language. 
With it’s interactive Graphical User Interface, users would be able to code or to create predicates with constants using the Drag ‘n Drop feature, where it makes this application very highly interactive, so that the students who do not have any programming language are able to learn them without any difficulty, allowing users of the application to be able to learn and grow at their own pace.

Team members: 

1. Yen Han Sern - Responsible for implementing the user stories into the LittleProlog Application and coding the application.
2. Jonathan Raj  - Responsible for all documentations for the project, including the test plan, time log as well as overlooking the coding process with Han Sern

Release notes (v0.1.1):

1. Ability to add meta-data (author's info) such as author, e-mail and description.
2. Ability to save and load file

Known bugs (v0.1.1):

1. Save and loading of the file might not work occasionally

Previous release notes (v0.1.0):

1. Home screen: Consists of three buttons, which are “Start LittleProlog v0.1”, “Instruction” and “Exit LittleProlog v0.1”. Users are able to start the LittleProlog application, or to read the instructions before proceeding, or exit the application.
2. Creating a new, blank LittleProlog program: A “Reset” button was implemented where user can reset/clear all predicates and the databases and start a new program.
3. Search query on the predicates and with program output appearing on the console: User can click the “Search Query” button and a block of object will appear for the user to drag and insert the predicates and constants that they want to search for. 
   The program will output on the console “true” if it exists in the database, false otherwise.
4. Modifying predicates/arguments: Users are able to edit predicate that has been created(dropped into the drop target) by clicking the the constant’s edit text field on the drop target, once the predicate/arguments were edited, click “Run” and it will be saved. 
   Users may also delete existing predicates.

Known Bugs (v0.1.0):

1. When multiple predicates are created, query search might return an invalid output.
2. Deleting predicates might occasionally not work.

Getting Started:

1. i. When the application is launched, users will be presented with the homepage of the application. Three buttons will be displayed and they are:
   
   ii. "Start LittleProlog v0.1" - It switches to the interface where users are able to create new predicates with constants or perform a query search by using the drag and drop feature.
   
   iii. “Instruction” - A sets of instructions or guides on how to use this application
   
   iv. "Exit LittleProlog v0.1" - users are able to exit the application.

2. i. When the user chooses to start the application, the interface will change to a page where there are text fields to key in the author's name, e-mail and description of the file.
   
   ii. Once the author's details are keyed in, the following will appear: drop target, an output console, a block of predicates/query search, save/load button, instruction button and a few other buttons required to run the application.

3. To create a predicate, users are required to drag the [ (  ) . ] button onto the drop target (the blank, non-white space). 
   Users are then required to fill in the spaces with a predicate and constant (e.g animal(lion). ). The user may repeat this for as many predicates he/she wishes to create. 
   Following this, the user is required to tap on “RUN” for every predicate they wish to create. It will be created in order of when the buttons were dragged into the drop target. 
   The text console will then output “The predicate XX(XX). has been created”. An error message will appear for invalid predicates (such as predicates with arguments that begin with capital letters).

4. To modify a predicate, the user may tap on an existing predicate, modify it and then tap on “RUN” to save the changes.

5. To delete a predicate, the user may tap and hold on an existing predicate until it is removed from the screen.

Known Bugs:

1. When multiple predicates are created, query search might return an invalid output.

2. Deleting predicates might occasionally not work.
