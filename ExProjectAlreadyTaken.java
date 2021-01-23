public class ExProjectAlreadyTaken extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExProjectAlreadyTaken() 
    {
        super("Project has already been assigned to a team.");
    }
    
    public ExProjectAlreadyTaken(String message) { super(message); }
}