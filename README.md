📌 Calculator – Java Swing Project

Smart desktop calculator built as my first larger Java project


 Overview

This project represents my first serious Java application developed after learning core programming fundamentals at university, including:
-Control flow (loops, conditionals)
-Arrays & matrices
-Basic OOP (classes, objects, abstract classes)
-Lists & collections
-Working with text files and serialization
-Introduction to GUI programming
The goal of the project was to build a fully functional calculator, inspired by the standard Windows calculator, but extended with:
Support for parentheses
Automatic closing of parentheses
Advanced event handling (each button changes behavior depending on previous input)

It was written in Eclipse, using pure Java Swing, without FXML, Scene Builder, or any external libraries.

Features

- Basic arithmetic operations
- Parentheses support ()
- Automatic bracket closing
- Memory operations
- Clear / All Clear logic
- Input validation
- Handling of multiple chained operations
- Complex button behavior depending on previous context
- Fully custom layout designed using Swing components

Technologies Used

Java 17
Java Swing for GUI
Eclipse IDE
OOP principles
Lambda expressions & Streams (used later in development with assistance)

What This Project Represents

This calculator is the first project where I:
Built a complex GUI from scratch
Handled large amounts of user events
Implemented custom logic behind many interacting buttons
Connected UI and business logic manually (without MVC)
Structured a larger Java project on my own
Applied early Java knowledge in a real-world scenario
It marks the point where my Java development skills started evolving from basic exercises toward full applications.

⚠ Known Limitations

This project is intentionally left as it originally was — to show how my skills looked at that time.
Some limitations include:
🟡 Logic is not separated using MVC:
A lot of computation and state management sits inside the GUI classes.
🟡 Method names and comments are written in Serbian, which is not ideal for professional code bases.
Despite these points, the project fulfills its purpose as an educational milestone and portfolio piece.

🚀 Running the Application

Clone or download the repository.
Open the project in Eclipse (or any Java IDE).
Run the Calculator.java main class.
No additional dependencies are required.

Future Improvements (optional ideas)

Although the project is complete, future versions might include:
Migrating the UI to JavaFX
Introducing proper MVC architecture
Better input parsing
Reorganized button handlers
Unit testing for expression evaluation

🙌 Acknowledgements

Some parts of the project (especially minor refactors using Streams and Lambdas) were improved with help from ChatGPT, who assisted in simplifying logic and solving specific problems during development.
