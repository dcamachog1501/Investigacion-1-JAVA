package Structures;

public class Cell
{
    private int value;
    private Cell next;

    public Cell()
    {
        this.value=0;
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
}
