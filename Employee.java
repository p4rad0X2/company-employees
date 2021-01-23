import java.util.*;
public class Employee implements Comparable<Employee>
{
    private Membership mem;
    private String name;

    
    public Employee(String n)
    {
        name = n;
        mem = new Membership(name);
    }

    public String getName()
    {
        return name;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) 
    {
        int flag = -1;
        for(int i=0; i<list.size(); i++)
        {
            if(nameToSearch.equals(list.get(i).getName()))
            {
                flag = i;
            }
        }
        if(flag!=-1)
            return list.get(flag);
        else 
            return null;
    }

    public static void list(ArrayList<Employee> list)
    {
        for(Employee e : list)
        {
            System.out.println(e.getName());
        }
    }

    public int compareTo(Employee another) 
    {
        return this.name.compareTo(another.name);
    }

    public void addtoTeam(Team t, Day d)
    {
        mem.addtoTeam(t, d);
    }

    public void showTeamDetails()
    {
        mem.showdetails();
    }

    public Day showDetailsforProj(String s, Day d)
    {
        return mem.showdetailsforProj(s, d);
    }

    public void nextTeam(Team t, Day d)
    {
        mem.nextTeam(t,d);
    }


}
