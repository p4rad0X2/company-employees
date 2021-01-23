public class ExProjectNameExists extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExProjectNameExists() 
    {
        super("Project code already exists.");
    }
    
    public ExProjectNameExists(String message) { super(message); }
}