to compile the program:
Make

to run the program:
./onesCompliement [input file name]
./increment [input file name]


write how to write a valid TM input for the program
[current state] [symbol being read] [next state] [symbol being written] [LEFT\RIGHT]

this TM uses the String.contains method to find the correct state
  the start state must contain the substring START (all caps)
  the final state must contain the substring FINAL (all caps)

  the LEFT or RIGHT field must also be all caps

  becasue it uses contains the program will be entirely case sensitive
  each field is separated with a tab

the inputs to the TM must be correctly formatted strings
b[input sequence]b

each input string must be on a different line
