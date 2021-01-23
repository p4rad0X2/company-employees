public class ExDayNotSame extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ExDayNotSame() 
    {
        super("The earliest start day is tomorrow.");
    }
    public ExDayNotSame(String message) { super(message); }
}