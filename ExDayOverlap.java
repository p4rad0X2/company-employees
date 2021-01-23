public class ExDayOverlap extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ExDayOverlap() 
    {
        super("The team is not available during the period ");
    }
    public ExDayOverlap(String message) { super(message); }
}