## NFP121: Final Project (documentation)

Instructors: Dr. Chadi Kallab & Dr. Adel Awad
Executed by: Marc Fadi BITAR (1725bek) & Georges Pierre Ghafary (2129n)
Project ID: NFP121-2129n-1725bek-P4

> Project Aim

The scope of the project is to create an interface that lets the user build an appropriate schedule for a given establishment. First, the user would have to go fill out a set of basic data consisting of professors, courses, classes and rooms and then assign them all to each other respectively (couples of professor, class, room, day and time) and automatically visualize the schedule of each campus in an independant table.

> Design Patterns

The two design patterns implemented in this project are the following :

- In the data entry process, the user inputs the info of the classes such as the course of the class, the number of students and the campus. Then in the schedule building process, he couples that class with a professor and a room and it becomes a different entity, or a coupled class as referred to in the code. For that reason, we decided to implement the `Factory Method` in the class creating process, the two kinds being "ClasseNonCouple" and "ClasseCouple".

- Since we only needed one instance of the factory that creates the class instances, we extended it further by implementing into it the `Singleton` design pattern.

> Task Distribution

We, as a team, relied very heavily on teamwork and collaboration during the execution of the project, hence most of the work was done by both of the us working on it simultaneously.

The main parts that were done by either one of us individually were the Swing elements and GUI interfaces which were executed by Marc (1725bek), and the backend functionality of selecting object instances and editing them which was executed by Georges (2129n).

> Assumptions

In the planning phase of the project, we decided to also implement the `Observer` and `Command` design patterns.

We assumed that the `Observer` design pattern would work well with all the button click functions such as creating and editing an instance. We then realised it would be overkill and too complex and just referred to the default `ActionListener` and `actionPerformed` convention.

As for the `Command` design pattern, we thought of implementing it either on the schedule creation where there are various validation functions that are fired before the creation of the "ClasseCouple" or at the saving of data since we are saving many arrays of objects into files. We then realised it would be rather pointless since all the steps mentioned were already inside the respective classes which seemed like appropriate candidates for the `Command` design pattern.

> Difficulties Encountered

One of the main difficulties we encountered was the validation process that is fired when the user creates a new "ClasseCouple" to build the schedule. At the click of the "Enregistrer" button, the algorithm cycles through all the "ClasseCouple" instances in all the campuses to check the availibility of the professor and the room on the chosen date and time to then finally dictate if the instance can be inserted in the respective table cell or not.

We were also saving the schedule's table distribution in a two-dimensional array (5 days and 4 time intervals), but since there was the possibility for the same campus to have multiple classes of different courses with different professors and in different rooms at the same day and time, we extended that two-dimensional array by making it contain ArrayLists of "ClasseCouple" instances so we could add as many classes in the same time interval as possible as long as the validation process was successful.

> Future Works & Possible Improvements

One thing we had a lot of issues with achieving and eventually cancelled from the project plan was the implementation of the `Memento` design pattern in the schedule creating process which would allow the user to switch back and forth between states of the table he'd be building. However, we believe that if we were to simplify the architecture of our Object classes and the way we're stocking and accessing all the instances, the addition of the `Memento` design pattern would greatly improve the overall user experience and give them more freedom to come up with a better schedule.
