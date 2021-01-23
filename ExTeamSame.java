public class ExTeamSame extends Exception
{
    private static final long serialVersionUID = 1L;

    public ExTeamSame() 
    {
        super("The old and new teams should not be the same.");
    }
    
    public ExTeamSame(String message) { super(message); }
}