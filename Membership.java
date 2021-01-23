import java.util.ArrayList;
public class Membership
{
    private String empname;
    private ArrayList<Team> teamlist;
    private ArrayList<Day> startlist;
    private ArrayList<Day> endlist;

    public Membership(String name)
    {
        empname = name;
        teamlist = new ArrayList<>();
        startlist = new ArrayList<>();
        endlist = new ArrayList<>();
    }

    public void addtoTeam(Team t, Day d)
    {
        teamlist.add(t);
        startlist.add(d);
    }

    public void nextTeam(Team t, Day d)
    {
        teamlist.add(t);
        startlist.add(d);
        endlist.add(d.previous(d));
    }
    
    public void showdetails()
    {
        for(int i=0; i<teamlist.size(); i++)
        {
            System.out.printf("%s (%s to ",teamlist.get(i).getTeamName(), startlist.get(i).toString());
            if(i==teamlist.size()-1)
            {
                System.out.print("--)");
            }
            else
            {
                System.out.printf("%s)", endlist.get(i).toString());
            }

            if(teamlist.get(i).getEmpName().equals(empname))
            {
                System.out.printf(", as a leader\n");
            }
            else
                System.out.printf("\n");
        }
    }

    public Day showdetailsforProj(String s, Day d)
    {
        if(s.equals(teamlist.get(teamlist.size()-1).getTeamName()))
        {
            return startlist.get(teamlist.size()-1);
        }
        return null;
    }
}