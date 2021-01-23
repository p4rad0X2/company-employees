import java.util.*;
public class Team implements Comparable<Team>
{
    private String teamName;
    private Employee head;
    private Day dateSetup;   
    private ArrayList<Employee> members;
    public Team(String n, Employee hd) 
    {
        teamName = n;
        head = hd;
        dateSetup = SystemDate.getInstance().clone();
        members = new ArrayList<>();
    }

    //check method again maybe
    public static void list(ArrayList<Team> list) 
    {
        System.out.printf("%-15s%-10s%-14s%-20s\n", "Team Name", "Leader", "Setup Date", "Members");
        for (Team t : list)
        System.out.printf("%-15s%-10s%-14s%-20s\n", t.teamName, t.head.getName(), t.dateSetup.toString(), t.getTeamMembers()); 
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void addTeamMember(Employee e)
    {
        members.add(e);
        Collections.sort(members);
    }

    public ArrayList<Employee> getMemberList()
    {
        return members;
    }

    public String getTeamMembers()
    {
        String s = "";
        if(members.size()>0)
        {
            for(Employee e : members)
            {
                s += e.getName();
                s += " ";
            }
            s.trim();
        }

        else if(members.size()==0)
        {
           s =  "(no member)";
        }

        return s;

    }

    public String getEmpName()
    {
        return head.getName();
    }

    public int getNoOfMembers()
    {
        int ct = 1;
        ct += members.size();
        return ct;
    }

    public int compareTo(Team another) 
    {
        return this.teamName.compareTo(another.teamName);
    }
}
