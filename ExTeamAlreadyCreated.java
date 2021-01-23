public class ExTeamAlreadyCreated extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExTeamAlreadyCreated() 
    {
        super("Team name already exists.");
    }
    
    public ExTeamAlreadyCreated(String message) { super(message); }
}