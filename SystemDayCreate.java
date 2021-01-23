public class SystemDayCreate extends RecordedCommand
{
    String org;
    String ndat;
    @Override
    public void execute(String[] cmdParts)
    {
        SystemDate ins = SystemDate.getInstance();
        org = ins.toString();
        ins.set(cmdParts[1]);
        ndat = cmdParts[1];
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    public void undoMe()
    {
        SystemDate ins = SystemDate.getInstance();
        ins.set(org);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        SystemDate ins = SystemDate.getInstance();
        ins.set(ndat);
        addUndoCommand(this);
    }
}
