public class CmdChangeTeam extends RecordedCommand
{
    private Team newTeam;
    private Team oldTeam;
    private Employee e;

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
            newTeam = company.findTeam(cmdParts[2]);

            if(e==null)
                throw new ExEmployeeNotExist();

            if(newTeam==null)
                throw new ExTeamNotExist();
            
            oldTeam = company.findTeam(company.checkTeamMember(e));

            if(oldTeam==null)
                throw new ExTeamNotExist();

            if(oldTeam.getTeamName().equals(newTeam.getTeamName()))
                throw new ExTeamSame();

            company.removeEmployeeInTeam(e);
            company.addEmpTeam(e, newTeam);
            e.nextTeam(newTeam, d);

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

        catch(ExTeamSame e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeEmployeeInTeam(e);
        company.addEmpTeam(e, oldTeam);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.removeEmployeeInTeam(e);
        company.addEmpTeam(e, newTeam);
        addUndoCommand(this);
    }
}