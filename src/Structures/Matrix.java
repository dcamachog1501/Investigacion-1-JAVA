package Structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Matrix
{
    private Row firstRow;
    private int rows;
    private int columns;

    public Matrix()
    {
        this.firstRow=null;
        this.rows=0;
        this.columns=0;
    }
    public void addRow(Row row)
    {
        if(this.firstRow==null)
        {
            this.firstRow=row;
        }
        else
        {
            Row temp = this.firstRow;
            while(temp.getNext()!=null) {temp=temp.getNext();}
            temp.setNext(row);
        }
        this.rows++;
    }
    public void readSudoku(String filename)
    {
        File sudoku= new File("Sudokus/"+filename);
        Scanner scanner;
        try
        {
            scanner= new Scanner(sudoku);
            int rows=0;
            while(scanner.hasNextLine())
            {
                String[] rowValues=scanner.nextLine().split(",");
                Row row= new Row();
                for (int i = 0; i < rowValues.length; i++)
                {
                    row.addCell(Integer.parseInt(rowValues[i]),rows,i);

                }
                rows++;
                this.addRow(row);
            }
            scanner.close();
            this.columns=this.firstRow.getLength();
        } catch (FileNotFoundException e)
        {
            System.out.println("ERROR: No se pudo leer el archivo!\n");
        }

    }
    public Cell getCell(int row,int column)
    {
        int rows=0;
        int columns=0;

        Row tempRow= this.firstRow;
        while(rows!=row)
        {
            tempRow=tempRow.getNext();
            rows++;
        }
        Cell tempCell=tempRow.getHead();
        while (columns!=column)
        {
            tempCell=tempCell.getNext();
            columns++;
        }
        return tempCell;
    }
    public Cell mostOptimalCell()
    {
        int solutions=9;
        Cell cell=null;

        Row tempRow= this.firstRow;
        while(tempRow!=null)
        {
            Cell tempCell=tempRow.getHead();
            while (tempCell!=null)
            {
                if(tempCell.getValue()==0)
                {
                    int solutionsVecSize = solutionsFinder(tempCell).size();
                    if (solutionsVecSize == 1) {
                        return tempCell;
                    }
                    if (solutionsVecSize < solutions) {
                        solutions = solutionsVecSize;
                        cell = tempCell;
                    }
                }
                tempCell=tempCell.getNext();
            }
            tempRow=tempRow.getNext();
        }
        return cell;
    }
    public Vector<Integer> solutionsFinder(Cell cell)
    {
        Vector<Integer> solutions=new Vector();
        solutions.add(1);
        solutions.add(2);
        solutions.add(3);
        solutions.add(4);
        solutions.add(5);
        solutions.add(6);
        solutions.add(7);
        solutions.add(8);
        solutions.add(9);

        //Evaluando columnas
        int row=cell.getRow();
        int column=cell.getColumn();
        for (int i = 0; i <this.rows; i++)
        {
            Cell tempCell=getCell(i,column);
            if(tempCell.getValue()!=0)
            {
                solutions.remove(Integer.valueOf(tempCell.getValue()));
            }
        }
        //Evaluando filas
        for (int i = 0; i <this.columns; i++)
        {
            Cell tempCell=getCell(row,i);
            if(tempCell.getValue()!=0)
            {
                solutions.remove(Integer.valueOf(tempCell.getValue()));
            }
        }
        //Evaluando cuadrante
        int cuadBegRow=(row/3)*3;
        int cuadEndRow=(row/3)*3+2;

        int cuadBegCol=(column/3)*3;
        int cuadEndCol=(column/3)*3+2;

        for (int i = cuadBegRow; i <= cuadEndRow; i++)
        {
            for (int j = cuadBegCol; j <= cuadEndCol; j++)
            {
                Cell tempCell=getCell(i,j);
                if(tempCell.getValue()!=0)
                {
                    solutions.remove(Integer.valueOf(tempCell.getValue()));
                }
            }
        }
        return solutions;
    }
    public void solve()
    {
        if(backTrackSolver())
        {
            System.out.println("--> Solucion del Sudoku:\n\n"+this);

        }
        else
        {
            System.out.println("ERROR: No es posible resolver el sudoku!");
        }
    }
    public boolean backTrackSolver()
    {
        Cell optimalCell=mostOptimalCell();
        if(optimalCell==null)
        {
            return true;
        }
        Vector<Integer> solutions=solutionsFinder(optimalCell);
        if(solutions.size()==0)
        {
            return false;
        }
        for (int i = 0; i < solutions.size(); i++)
        {
            optimalCell.setValue(solutions.get(i));
            if(backTrackSolver())
            {
                return true;
            }
        }
        optimalCell.setValue(0);
        return false;
    }
    public String toString()
    {
        String out="";
        Row tempRow=this.firstRow;
        String top="";
        int row=0;
        while(tempRow!=null)
        {
            Cell tempCell=tempRow.getHead();
            String middle="|";
            String inter="|";
            int column=0;
            while(tempCell!=null)
            {
                top+="     ";
                if(tempCell.getValue()!=0)
                {
                    middle += " " + Integer.toString(tempCell.getValue()) + " |";
                }
                else
                {
                    middle += "   |";
                }
                if((column+1)%3==0 &&(column+1)!=9)
                {
                    middle+="    |";

                }
                column++;
                tempCell=tempCell.getNext();
            }
            out+=top+"\n"+middle+"\n";

            if((row+1)%3==0 && (row+1)!=9) {out+="\n";}
            if(tempRow.getNext()==null){out+=top;}
            top="";
            tempRow=tempRow.getNext();
            row++;
        }
        return (out+top);
    }
}
