public class CmdTeamCreate extends RecordedCommand
{
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

            Employee e = company.findEmployee(cmdParts[2]);

            if (e==null)
            {
                throw new ExEmployeeNotExist();
            }
            t = new Team(cmdParts[1], e);

            if (company.TeamEmployeeCheck(e))
            {
                throw new ExEmployeeOtherTeam();
            }

            if (company.TeamCheck(t))
            {
                throw new ExTeamAlreadyCreated();
            }
            e.addtoTeam(t, d);
            company.addTeam(t);

            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        }

        catch (NumberFormatException e) 
		{
			System.out.println("Wrong number format.");
		}

		catch (ExInsufficientArguments e)
		{
			System.out.println(e.getMessage());
        }

        catch (ExTeamAlreadyCreated e)
        {
            System.out.println(e.getMessage());
        }

        catch (ExEmployeeNotExist e)
        {
            System.out.println(e.getMessage());
        }
        
        catch (ExEmployeeOtherTeam e)
        {
            System.out.println(e.getMessage());
        }


    }

    
    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeTeam(t);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.addTeam(t);
        addUndoCommand(this);
    }
}
