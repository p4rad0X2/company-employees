public class CmdCreateProject extends RecordedCommand
{
    private Project p;
    private int n;

    @Override
    public void execute(String[] cmdParts)
    {
        try
        {
            if(cmdParts.length<3)
            {
                throw new ExInsufficientArguments();
            }

            Company company = Company.getInstance();
            n = Integer.parseInt(cmdParts[2]);
            if(n<=0)
            {
                throw new ExNegativeOrZero();
            }

            String pcode = cmdParts[1];
            p = new Project(pcode, n);
            if(company.ProjectCodeCheck(p))
            {
                throw new ExProjectNameExists();
            }


            company.addProject(p);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        }

        catch(NumberFormatException e)
        {
            System.out.println("Wrong number format -- Please enter an integer.");
        }

        catch(ExNegativeOrZero e)
        {
            System.out.println(e.getMessage());
            System.out.printf("Consider changing %d to a positive non-zero amount.\n", n);
        }

        catch(ExProjectNameExists e)
        {
            System.out.println(e.getMessage());
        }

        catch (ExInsufficientArguments e)
		{
			System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeProject(p);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.addProject(p);
        addUndoCommand(this);
    }
}
