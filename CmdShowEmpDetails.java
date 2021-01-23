public class CmdShowEmpDetails extends RecordedCommand
{
    Employee e;
    @Override
    public void execute(String[] cmdParts)
    {
        Company company = Company.getInstance();
        e = company.findEmployee(cmdParts[1]);
        System.out.printf("The teams that %s has joined: \n", e.getName());
        e.showTeamDetails();

    }    
    
    @Override
    public void undoMe()
    {}

    @Override
    public void redoMe()
    {}
}