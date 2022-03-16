package Main;

import Structures.Matrix;

public class Main {

    public static void main(String[] args)
    {
	    Matrix matrix=new Matrix();
        matrix.readSudoku("Hardest2.txt");
        matrix.solve();
    }
}