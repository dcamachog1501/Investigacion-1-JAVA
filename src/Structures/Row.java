package Structures;

public class Row
{
    private Cell head;
    private int length;
    private Row next;

    public Row()
    {
        this.head=null;
        this.length=0;
        this.next=null;
    }
    public Cell getHead()
    {
        return this.head;
    }
    public int getLength()
    {
        return length;
    }
    public Row getNext()
    {
        return next;
    }
    public void setNext(Row next)
    {
        this.next = next;
    }
    public void addCell()
    {
        if(this.head==null)
        {
            this.head=new Cell();
        }
        else
        {
            Cell temp = this.head;
            while(temp.getNext()!=null) {temp=temp.getNext();}
            temp.setNext(new Cell());
        }
        this.length++;
    }
}
