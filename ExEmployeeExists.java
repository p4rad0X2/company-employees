public class ExEmployeeExists extends Exception 
{
    private static final long serialVersionUID = 1L;

    public ExEmployeeExists() 
    {
        super("Employee name already exists.");
    }
    public ExEmployeeExists(String message) { super(message); }
}