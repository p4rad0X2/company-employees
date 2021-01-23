public class ExNegativeOrZero extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExNegativeOrZero() 
    {
        super("Estimated manpower should not be zero or negative. ");
    }
    
    public ExNegativeOrZero(String message) { super(message); }
}