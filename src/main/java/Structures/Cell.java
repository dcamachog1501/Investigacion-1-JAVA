package Structures;

public class Cell
{
    private int value;
    private Cell next;
    private int row;
    private int column;

    public Cell(int value,int row,int column)
    {
        this.value=value;
        this.row=row;
        this.column=column;
        this.next=null;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }

    public Cell getNext()
    {
        return next;
    }

    public void setNext(Cell next)
    {
        this.next = next;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn()
    {
        return column;
    }
}
