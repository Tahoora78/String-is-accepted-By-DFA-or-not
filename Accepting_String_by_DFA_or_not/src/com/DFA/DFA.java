
package com.DFA;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.SplittableRandom;

public class DFA {
    static String DfaTable[][];
    static String initializeState;
    static int noOfStates;
    static int noOfTerminals;
    static String[] finalStates;
    public static void GetDFATable(String pathOfFile) throws IOException {
        File file = new File(pathOfFile);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

//1
        st = br.readLine();
        String[] terminal = st.split(" ");
        noOfTerminals = terminal.length+1;


//2
        st = br.readLine();
        String[] state = st.split(" ");
        noOfStates = state.length+1;
        DfaTable = new String[noOfStates][noOfTerminals];
        String[] nstate = new String[noOfStates];
        nstate[0] = " ";

        for (int h=0;h<noOfStates-1;h++){
            nstate[h+1] = state[h];
        }

        DfaTable[0][0]=" ";

        for (int c=1;c<noOfStates;c++){
            DfaTable[c][0] = state[c-1];
        }

        DfaTable[0][0] = " ";
        for (int x=0;x<noOfTerminals-1;x++){
            DfaTable[0][x+1] = terminal[x];
        }
//3
        st = br.readLine();
        initializeState = st;
//4
        st = br.readLine();
        finalStates = st.split(" ");
        String[] g ;
        while ((st = br.readLine()) != null){
            g = st.split(" ");
            for (int zz =0;zz<nstate.length;zz++){
                if (g[0].equals(nstate[zz])){
                    for(int zzz=0;zzz<DfaTable[0].length;zzz++){
                        if (g[1].equals(DfaTable[0][zzz])){
                            DfaTable[zz][zzz] = g[2];
                            break;
                        }
                    }
                }
            }
        }
        for(int m=0;m<noOfStates;m++) {
            for (int n = 0; n < noOfTerminals; n++) {
                System.out.print(DfaTable[m][n]);

            }
            System.out.println();
        }


    }


    public static String InitialState() {
        return initializeState;
    }
    
    public static String NextState(String currentstate,String currentchar){
        for(int i=1;i<noOfStates;i++){
            if(currentstate.equals(DfaTable[i][0])){
                for(int j=1;j<noOfTerminals;j++){
                    if(currentchar.equals(DfaTable[0][j])){
                        return DfaTable[i][j];
                    }
                }
            }
        }
        return "$";
    }

//        return DfaTable[noOfStates-1][0];
    
    public static boolean FinalState(String currentState) {
        for(String value : finalStates){
            String charValue = value;
            if(charValue.equals(currentState)){
                return true;
            }
        }
        return false;
    }
}