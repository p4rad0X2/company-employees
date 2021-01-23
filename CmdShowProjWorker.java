public class CmdShowProjWorker extends RecordedCommand
{
    Project p;
    @Override
    public void execute(String[] cmdParts)
    {
        Company company = Company.getInstance();
        p = company.findProject(cmdParts[1]);
        p.showDetails();

    }    
    
    @Override
    public void undoMe()
    {}

    @Override
    public void redoMe()
    {}    
}
