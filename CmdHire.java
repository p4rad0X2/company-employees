public class CmdHire extends RecordedCommand
{
    private Employee e;
    @Override
    public void execute(String[] cmdParts)
    {
        try
        {
            if(cmdParts.length<2)
            {
                throw new ExInsufficientArguments();
            }
            
            Company company = Company.getInstance();

            e = new Employee(cmdParts[1]);
            if (company.EmployeeCheck(e))
            {
                throw new ExEmployeeExists();
            }

           company.addEmployee(e);

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
        
        catch (ExEmployeeExists e)
        {
            System.out.println(e.getMessage());
        }

    }    

    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeEmployee(e);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.addEmployee(e);
        addUndoCommand(this);
    }


}
