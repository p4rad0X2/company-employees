public class ExEmployeeOtherTeam extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExEmployeeOtherTeam() 
    {
        super("Employee has joined a team already.");
    }
    
    public ExEmployeeOtherTeam(String message) { super(message); }
}