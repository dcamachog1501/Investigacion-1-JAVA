package Main;

import Structures.SudokuMatrix;

public class Main {

    public static void main(String[] args)
    {
	    SudokuMatrix matrix=new SudokuMatrix();
        int [][] i=matrix.solve("Hardest2.txt");
    }
}