import java.util.*;
public class Project implements Comparable<Project> 
{
    private String pname;
    private int plen;
    private Day startDay;
    private Day endDay;
    private Team t;

    public Project(String pname, int plen)
    {
        this.pname = pname;
        this.plen = plen;
    }

    public String getProjectName()
    {
        return pname;
    }

    public void setTeam(Team t)
    {
        this.t = t;
    }

    public String teamnameget()
    {
        if(t == null)
            return "(Not Assigned)";

        else
            return t.getTeamName();
    }
    

    public int getPlen()
    {
        return plen;
    }

    public Day getStartDay()
    {
        return startDay;
    }

    public Day getEndDay()
    {
        return endDay;
    }

    public String startdayget()
    {
        if(startDay == null)
            return "";
        else
            return startDay.toString();
    }

    public String enddayget()
    {
        if(startDay == null)
            return "";
        else
            return endDay.toString();
    }

    public void setDates(Day start, Day end)
    {
        startDay = start;
        endDay = end;
    }

    public static void list(ArrayList<Project> list)
    {
        String s = "";
        System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", "Project", "Est manpower", "Team", "Start Day", "End Day");
        for (Project p : list)
        {
            s = "";
            s += p.getPlen();
            s += " man-days";
            System.out.printf("%-9s%-14s%-13s%-13s%-13s\n", p.pname, s, p.teamnameget(), p.startdayget(), p.enddayget());
        }
        
    }

    public void showDetails()
    {
        Day d;
        ArrayList<Employee> s= new ArrayList<>();
        System.out.printf("Est manpower : %s man-days\n", plen);
        System.out.printf("Team : %s (Leader is %s)\n", teamnameget(), t.getEmpName());
        System.out.printf("Work period : %s to %s\n", startDay.toString(), endDay.toString());
        System.out.println("Members: ");
        s = t.getMemberList();
        for(int i=0; i<s.size(); i++)
        {

            System.out.printf("%s (",s.get(i).getName());
            d = s.get(i).showDetailsforProj(teamnameget(), startDay);
            if(d.checklen(d)<startDay.checklen(startDay))
            {
                System.out.printf("%s to", startDay.toString());
            }
            else
            {
                System.out.printf("%s to", d.toString());
            }
            System.out.printf(" %s)\n", endDay.toString());
        }

    }

    @Override
    public int compareTo(Project another) 
    {
        return this.pname.compareTo(another.pname);
    }
    
}
