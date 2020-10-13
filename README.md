# Advanced Programming Project

## Description

The Common Lisp Object System (CLOS) is an object-oriented layer for Common Lisp that is very diferent
from the typical object-oriented languages such as Java. In CLOS, the programmer organizes its program using
the concept of generic function. A generic function is a function containing diferent specialized implementations
for diferent types of arguments. Each specialization of a generic function is a method.
Everytime a generic function is applied to a set of arguments, the actual types of the arguments are used to
find the set of applicable methods, which are sorted according to its specifcity and, then, combined to create
the efective method. The application of the efective method to the provided arguments will then produce
the intended results. This form of operation supports multiple dispatch, where the applicable methods are
determined based on the types of all arguments.

## Goals

The main goal of this project is the implementation of generic functions in Java, following the syntax and
semantics previously described. The classes, interfaces, and annotations should be implemented in the package
ist.meic.pa.GenericFunctions.
You must also implement a Java class named ist.meic.pa.GenericFunctions.WithGenericFunctions
containing a static method main that accepts, as arguments, the name of another Java program (i.e., a Java class
that also contains a static method main) and the arguments that should be provided to that program. The class
should (1) operate the necessary transformations to the loaded Java classes so that when the classes are executed,
the semantics of method calls to methods defined in classes or interfaces annotated as @GenericFunction follow
CLOS semantics, and (2) should transfer the control to the main method of the program.
Note that the implementation only needs to support standard method combination, with primary, before,
and after methods. It is not necessary to implement around methods or mechanisms to call the next applicable
method (call-next-method, in CLOS parlance).
Note also that the specifcity of methods should be based on the Java type hierarchy.
