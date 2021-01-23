public class CmdJoinTeam extends RecordedCommand
{
    private Employee e;
    private Team t;
    @Override
    public void execute(String[] cmdParts)
    {
        try
        {
            SystemDate sd = SystemDate.getInstance();
            Day d = new Day(sd.getYear(), sd.getMonth(), sd.getDay());
            if(cmdParts.length<3)
            {
                throw new ExInsufficientArguments();
            }

            Company company = Company.getInstance();

            e = company.findEmployee(cmdParts[1]);
            t = company.findTeam(cmdParts[2]);

            if(e==null)
                throw new ExEmployeeNotExist();

            if(t==null)
                throw new ExTeamNotExist();
            
            if(company.checkTeamMember(e)!=null)
            {
                    throw new ExEmployeeOtherTeam();
            }
            e.addtoTeam(t, d);
            t.addTeamMember(e);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");

        }
        catch(ExInsufficientArguments e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExEmployeeNotExist e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExTeamNotExist e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExEmployeeOtherTeam e)
        {
            System.out.println(e.getMessage());
        }
    }

    
    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeEmployeeInTeam(e);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.addEmpTeam(e, t);
        addUndoCommand(this);
    }


}
