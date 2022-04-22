/**
* Written by Lance Kevin on 11/30/2020
* Simulates a turning machine
*/
import java.io.*;
import java.util.Scanner;

public class turingMachine {

	//variables used to simulate the actions of the TM
	private String[] tape;
	private int tapeheadPos;
	private String state;

	//the file which contains the transitions of the TM
	private File programFile;


	//constructor which sets the program file
	public turingMachine(String filePath) {
		programFile = new File(filePath);
	}

	//continuously reads the program file until a final state is reached
	public String execute(String input) {
		tapeheadPos = 1;
		state = "START";

		System.out.println("starting execute");

		//instantiate tape to 3xinput length, 3 is a magic number
		int inLen = input.length();
		tape = new String[3*inLen];

		//copy the input onto the input tape
		char[] inArr = input.toCharArray();
		for(int i = 0; i < inArr.length; ++i) {
			tape[i] = Character.toString(inArr[i]);
		}
		for(int i = inArr.length; i < tape.length; ++i) {
			tape[i] = "b";
		}

		//output copied tape
		for(int i = 0; i < tape.length; ++i)
			System.out.print(tape[i]);
		System.out.println("");

		//loop throughout the transitions until a final state is reached
		while(!state.contains("FINAL")) {

			System.out.print("Curr tape: ");

			for(int i1 = 0; i1 < tape.length; ++i1)
				System.out.print(tape[i1]);

			System.out.println("curr state: "+state+" reading "+tape[tapeheadPos]+" at index "+tapeheadPos);

			//finding the correct transition
			Scanner fileReader;
			try {
				fileReader = new Scanner(programFile);
				String currLine = null;
				String [] splitLine = null;
				int lineNum = 0;

				//find current state name and correct input char line in file
				while(fileReader.hasNextLine()) {
					Boolean found = false;

					do {
						++lineNum;
						currLine = fileReader.nextLine();
						splitLine = currLine.split("\t");
					} while(splitLine.length != 5);


					//checking for correct state
					if(splitLine.length == 5 && splitLine[0].contains(state)) {

						//ensuring the line is for the specified input on the tape
						if(splitLine[1].equals(tape[tapeheadPos])) {
							System.out.println("the line at "+lineNum+" is the correct transition for this input:");
							System.out.println(currLine);
							break;
							}
					}
				}


				//do the operations
				state = splitLine[2];
				tape[tapeheadPos] = splitLine[3];

				if(splitLine[4].equals("RIGHT"))
					++tapeheadPos;
				else
					--tapeheadPos;

				System.out.println("new state = " + state);
				System.out.println("tape head at index "+tapeheadPos+"\n");

				fileReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		}

		//converting the tape to a string to be returned
		String result = "";

		for(int i1 = 0; i1 < tape.length; ++i1)
			result = result + tape[i1];

		System.out.println(" result: "+result);

		return result;
	}

	public static void main(String[] args) {

		PrintStream std = System.out;
		PrintStream log;
		try {
			turingMachine TM = new turingMachine(args[0]);
			File inputs = new File(args[1]);
			Scanner inputReader = new Scanner(inputs);
			while(inputReader.hasNextLine()) {
				//setting standard out so program will print extraneous output to log file
				log = new PrintStream(new File("Log.txt"));
				System.setOut(log);

				String currLine = inputReader.nextLine();
				String output = TM.execute(currLine);

				log.close();

				//printing the output to the console
				System.setOut(std);
				System.out.println(output);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
