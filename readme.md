# SE 311 Homework 4 Calculator

This project is the culmination of SE 311: Software Design 2.

## Diagrams
The diagrams for this project can be seen in Diagrams/ and the
source for each is the corresponding plantuml file.

## Execution
TODO Create maven targets.
Currently to run the project you must import the jar generated by
CalculatorCommon into CalculatorClient and CalculatorServer.

I'm still figuring this out. Java is not my first language.

## Design Patterns
### State Pattern
The DFA defined at the end of the Assignment 4.pdf was used
to define the state and transitions from the abstract class
States/State. Each state contains a context (specifically operand1,
operator, and operand2).

### Visitor Pattern
In conjunction with Composite Pattern, calculation results and ASCII
tree diagrams were generated using concrete visitors. Expressions
are required to have an accept(Visitor) function and Visitors are
required to have a visit(Expression) function.

### Observer Pattern
The CalculatorInputHandler is the observer to CalculatorInputWindow (subject).
Implicit invokation is used in the form of the builtin ActionEvent
and ActionListener for whenever an event occurs.

### Composite Pattern
Pattern used in CalculatorCommon/Expressions
Operand is a Concrete Class of type Expression
Operator is a Concrete Class but is also composed of a
left and right operand.