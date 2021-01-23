public class CmdTeamList extends RecordedCommand
{
    @Override
    public void execute(String[] cmdParts)
    {
        Company company = Company.getInstance();
        company.listTeams();

    }    

    @Override
    public void undoMe()
    {}

    @Override
    public void redoMe()
    {}

    

}
