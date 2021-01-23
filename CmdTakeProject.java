public class CmdTakeProject extends RecordedCommand
{
    private Project p;
    private Team t;
    private int n;
    private Day startDay;
    private Day endDay;

    @Override
    public void execute(String[] cmdParts)
    {
        try
        {
            if(cmdParts.length<4)
            {
                throw new ExInsufficientArguments();
            }

            Company company = Company.getInstance();
            SystemDate sd = SystemDate.getInstance();
            p = company.findProject(cmdParts[2]);
            t = company.findTeam(cmdParts[1]);

            String[] sDayParts = cmdParts[3].split("-");
            if(sDayParts.length<3)
            {
                throw new ExInvalidDate();
            }
            if(!sd.checkMonth(sDayParts[1]))
            {
                throw new ExInvalidDate();
            }

            startDay = new Day(cmdParts[3]);

            if(p == null)
            {
                throw new ExProjectNotExist();
            }

            if(t == null)
            {
                throw new ExTeamNotExist();
            }

            if(!p.teamnameget().equals("(Not Assigned)"))
            {
                throw new ExProjectAlreadyTaken();
            }

            if(!sd.valid(startDay.getYear(), startDay.getMonth(), startDay.getDay()))
            {
               throw new ExInvalidDate();
            }

            if(startDay.getDay()==sd.getDay()&&startDay.getMonth()==sd.getMonth()&&startDay.getYear()==sd.getYear())
            {
                throw new ExDayNotSame();
            }

            n = p.getPlen();
            if(n%t.getNoOfMembers()!=0)
            {
                n = (n/t.getNoOfMembers()) + 1;
            }
            else
                n = n/t.getNoOfMembers();
            endDay = sd.getEndDay(startDay, n);

            if(company.checkOverlap(t, startDay, endDay))
            {
                throw new ExDayOverlap();
            }
          
            company.setProjectTeam(p, t, startDay, endDay);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        }
        
        catch (ExInsufficientArguments e)
		{
			System.out.println(e.getMessage());
        }

        catch(NumberFormatException e)
        {
            System.out.println("Wrong number format -- Please enter an integer.");
        }

        catch(ExProjectNotExist e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExTeamNotExist e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExProjectAlreadyTaken e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExDayNotSame e)
        {
            System.out.println(e.getMessage());
        }

        catch(ExDayOverlap e)
        {
            System.out.printf(e.getMessage());
            System.out.printf("(%s to %s).\n", startDay.toString(), endDay.toString());
        }

        catch(ExInvalidDate e)
        {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void undoMe()
    {
        Company company = Company.getInstance();
        company.removeTeamFromProject(p);
        addRedoCommand(this);
    }

    @Override
    public void redoMe()
    {
        Company company = Company.getInstance();
        company.setProjectTeam(p, t, startDay, endDay);
        addUndoCommand(this);
    }
}