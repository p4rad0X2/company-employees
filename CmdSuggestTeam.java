public class CmdSuggestTeam extends RecordedCommand
{
    Project p;
    int n;
    Team t;
    Day startDay;
    Day endDay;
    @Override
    public void execute(String[] cmdParts)
    {
        Company company = Company.getInstance();
        SystemDate sd = SystemDate.getInstance();
        p = company.findProject(cmdParts[1]);
        n = p.getPlen();
        Day d = new Day(sd.getYear(), sd.getMonth(), sd.getDay());
        company.suggest(n, d, p);
    }

    @Override
    public void undoMe()
    {}

    @Override
    public void redoMe()
    {}

}
