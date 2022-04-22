# Makefile for Turing Machine project
default: turingMachine.class

#Turing machine
turingMachine.class: turingMachine.java
	javac turingMachine.java

clean:
	rm -rf *.class \