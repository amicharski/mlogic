# MLogic

MLogic is a very basic logic programming language with a very simple syntax to understand.

## Table of Contents
* [Technologies](#technologies)
* [Syntax](#syntax)

## Technologies
This project was created with:
* Java 15

## Syntax
### Comments
Use a `#` to denote a single line comment. Unfortunately, at this time MLogic only supports single line comments.
### Variables & Expressions
The only data type in MLogic are boolean values that may be either TRUE, FALSE, or UNK (unknown). Declaring a variable can be done in 1 of 2 ways.
You can either implicitly declare it in an expression, or you can instantiate it with a SET statement. On the other hand, expressions can be put down into a single line.
Remember that all expressions are true.
```
#Implicit declaration of 'a' and 'b'
'a' AND 'b'
#This line denotes that the expression 'a' AND 'b' is TRUE, therefore a is true, and b is true
#Another implicit declaration
'c' OR 'd'
#This line denotes that the expression 'c' OR 'd' is TRUE, therefore either c or d is true, but also both c and d can be true.
#Yet another implicit declaration
NOT 'e' OR 'f'
#This line denotes that the expression NOT 'e' OR 'f' is TRUE. This is equivalent to an implies operator in symbolic logic (e -> f)

```
