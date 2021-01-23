public class ExDayNotExist extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ExDayNotExist() 
    {
        super("The day does not exist.");
    }
    public ExDayNotExist(String message) { super(message); }
}