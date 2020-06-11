
package com.DFA;

import java.io.IOException;
import java.util.Scanner;

public class Main_stringMatching {
static String currentChar;
static String currentState;
static String[] LineCharArray;
static int LineCharArrayCount = 0;
    
    public static void main(String[] args) throws IOException {
        Scanner inputScanner = new Scanner(System.in);

        String pathOfFile = "C:\\Users\\tahuora\\Desktop\\fisrtProject\\Simulation-Of-DFA-master\\Simulation-Of-DFA\\src\\com\\DFA\\test.txt";
        DFA.GetDFATable(pathOfFile);
        
        boolean userSatisfied = false;
        while( ! userSatisfied){
            System.out.println("Enter Desired Input String like:0 0 (put a apace between each character)");
            String inputString = inputScanner.nextLine();
            if(inputString.contentEquals("no"))
                break;
            else{
                //converting into readable format
                LineCharArray = inputString.split(" ");
                LineCharArrayCount = 0;
                //transitions start
                Algorithm();    //string matching algorithm
            }
        }
    }
    
    private static void Algorithm() {
            //Algorithm used to match a DFA Table against a string
        currentState = DFA.InitialState();     //denotes current state
        currentChar = String.valueOf(NextChar());       //current character from the input string
        while(!currentChar.equals("$")){
            currentState = Move(currentState,currentChar);
            currentChar = NextChar();
        }
        //checking if input is correct or not
        System.out.println(FinalStateChecker());
    }
    
    private static String  NextChar() {
        if(LineCharArray == null || LineCharArrayCount  == LineCharArray.length){
            return "$";
        }else{
            return LineCharArray[LineCharArrayCount++];
        }
    }
    
    private static String Move(String currentstate,String currentchar) {
        return DFA.NextState(currentstate,currentchar);
    }

    private static String FinalStateChecker() {
            if(DFA.FinalState(currentState)) {
                return "yes";
            }else {
                return "no";
            }
        }
}