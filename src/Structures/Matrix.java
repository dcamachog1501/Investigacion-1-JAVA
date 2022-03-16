package Structures;

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
    public void fill(int rows,int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            Row row= new Row();
            for (int j = 0; j < columns; j++)
            {
                row.addCell();
            }
            this.addRow(row);
        }
        this.columns=columns;
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
                middle+=" "+Integer.toString(tempCell.getValue())+" |";

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
