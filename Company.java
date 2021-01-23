import java.util.ArrayList;
import java.util.Collections;
public class Company 
{
    private ArrayList<Employee> allEmployees;
    private ArrayList<Team> allTeams;
    private ArrayList<Project> allProjects;

    private static Company instance = new Company();
    

    private Company() 
    { 
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
        allProjects = new ArrayList<>();
    }
    
    public static Company getInstance() 
    { 
        return instance; 
    }

    public void listTeams() 
    { 
        Team.list(allTeams); 
    }

    public void listProjects()
    {
        Project.list(allProjects);
    }

    public Employee createEmployee(String name) 
    {
	    Employee e = new Employee(name);
	    allEmployees.add(e);
	    Collections.sort(allEmployees); //allEmployees
	    return e;
    }


    public Team createTeam(String gname, String ename) 
    { 
        Employee e = Employee.searchEmployee(allEmployees, ename);
        Team t = new Team(gname, e);
        allTeams.add(t);
        Collections.sort(allTeams); // allTeams
        return t; // the return value is useful for later undoable command.
    }

    public void addEmployee(Employee e) 
    {
        allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public void listEmployee()
    {
        Employee e;
        String s;
        for(int i=0; i<allEmployees.size(); i++)
        {
            e = allEmployees.get(i);
            s = checkTeamMember(e);
            if(s==null)
                System.out.println(e.getName());
            else
            {
                System.out.printf("%s (%s)\n",e.getName(), s);

            }


        }
    }

    public void removeEmployee(Employee e) 
    {
        allEmployees.remove(e); 
    }

    public void removeTeam(Team t)
    {
        allTeams.remove(t);
    }

    public void addTeam(Team t)
    {
        allTeams.add(t);
        Collections.sort(allTeams);
    }

    public Boolean EmployeeCheck(Employee e)
    {
        Boolean flag = false;

        for(int i=0; i<allEmployees.size();i++)
        {
            if(e.getName().equals(allEmployees.get(i).getName()))
            {
                flag = true;
            }
        }

        return flag;
    }

    
    public Boolean TeamEmployeeCheck(Employee e)
    {
        Boolean flag = false;

        for(int i=0; i<allTeams.size(); i++)
        {
            if(e.getName().equals(allTeams.get(i).getEmpName()))
            {
                flag = true;
            }
        }

        return flag;
    }

    public Boolean TeamCheck(Team t)
    {
        Boolean flag = false;

        for(int i=0; i<allTeams.size(); i++)
        {
            if(t.getTeamName().equals(allTeams.get(i).getTeamName()))
            {
                flag = true;
            }
        }

        return flag;
    }



    public Boolean ProjectCodeCheck(Project p)
    {
        Boolean flag = false;

        for(int i=0; i<allProjects.size(); i++)
        {
            if(p.getProjectName().equals(allProjects.get(i).getProjectName()))
            {
                flag = true;
            }
        }

        return flag;
    }

    public Project createProject (String pname, int plen)
    {
        Project p = new Project(pname, plen);
        allProjects.add(p);
        Collections.sort(allProjects);
        return p;
    }

    public void addProject(Project p)
    {
        allProjects.add(p);
        Collections.sort(allProjects);
    }

    public void removeProject(Project p)
    {
        allProjects.remove(p);
    }

    public String checkTeamMember(Employee e)
    {

        ArrayList<Employee> emplist = new ArrayList<>();
        for(int i=0; i<allTeams.size(); i++)
        {
            emplist = allTeams.get(i).getMemberList();
            if(e.getName().equals(allTeams.get(i).getEmpName()))
            {
                return allTeams.get(i).getTeamName();
            }
            for(int j=0;j<emplist.size();j++)
            {
                if(e.getName().equals(emplist.get(j).getName()))
                {
                    return allTeams.get(i).getTeamName();
                }

            }
        }

        return null;
  
    }

    public void addEmpTeam(Employee e, Team t)
    {
        for(int i=0; i<allTeams.size(); i++)
        {
            if(allTeams.get(i).getTeamName().equals(t.getTeamName()))
            {
                allTeams.get(i).addTeamMember(e);
            }
        }
    }

    public Employee findEmployee(String name)
    {
        for(int i=0;i<allEmployees.size();i++)
        {
            if(name.equals(allEmployees.get(i).getName()))
                return allEmployees.get(i);
        }
        return null;
    }

    public Team findTeam(String name)
    {
        for(int i=0; i<allTeams.size();i++)
        {
            if(name.equals(allTeams.get(i).getTeamName()))
            {
                return allTeams.get(i);
            }
        }
        return null;
    }

    public Project findProject(String name)
    {
        for(int i=0; i<allProjects.size(); i++)
        {
            if(name.equals(allProjects.get(i).getProjectName()))
            {
                return allProjects.get(i);
            }
        }
        return null;
    }

    public void removeEmployeeInTeam(Employee e)
    {
        ArrayList<Employee> emplist = new ArrayList<>();
        for(int i=0; i<allTeams.size(); i++)
        {
            emplist = allTeams.get(i).getMemberList();
            if(e.getName().equals(allTeams.get(i).getEmpName()))
            {
                //print cant remove head from team
            }
            for(int j=0;j<emplist.size();j++)
            {
                if(e.getName().equals(emplist.get(j).getName()))
                {
                    allTeams.get(i).getMemberList().remove(e);
                }

            }
        }
    }

    public Boolean checkifHead(Employee e)
    {
        Boolean flag = false;
        for(int i=0; i<allTeams.size(); i++)
        {
            if(e.getName().equals(allTeams.get(i).getEmpName()))
            {
                flag = true;
            }
        }
        return flag;
    }

    public void setProjectTeam(Project p, Team t, Day start, Day end)
    {
        p.setTeam(t);
        p.setDates(start, end);
    }


    public void removeTeamFromProject(Project p)
    {
        p.setTeam(null);
        p.setDates(null, null);
    }

    public Boolean checkOverlap(Team t, Day start, Day end)
    {
        Boolean flag = false;
        Day projst;
        Day projen;

        for(int i=0; i<allProjects.size(); i++)
        {
            if(allProjects.get(i).teamnameget().equals(t.getTeamName()))
            {
                projst = allProjects.get(i).getStartDay();
                projen = allProjects.get(i).getEndDay();
                if((projst.checklen(projst)>=start.checklen(start)&&projst.checklen(projst)<=end.checklen(end)) || (projen.checklen(projen))>=start.checklen(start)&&projen.checklen(projen)<=end.checklen(end))
                {
                    if((start.checklen(start)==end.checklen(end)) && (projst.checklen(projst)<=start.checklen(start))&&(projen.checklen(projen)>=end.checklen(end)))
                    {
                        return true;
                    }

                    return true;
                    
                }
                else if((start.checklen(start)==end.checklen(end)) && (projst.checklen(projst)<=start.checklen(start))&&(projen.checklen(projen)>=end.checklen(end)))
                {
                    return true;
                }
            }
        }
        return flag;
    }

    public ArrayList<Project> findProjectOfTeam(Team t)
    {
        ArrayList<Project> projtlist = new ArrayList<>();
        for(int i=0; i<allProjects.size(); i++)
        {
            if(t.getTeamName().equals(allProjects.get(i).teamnameget()))
            {
                projtlist.add(allProjects.get(i));
            }
        }
        return projtlist;
    }

    public void suggest(int n, Day today, Project search)
    {
        Day start = today.next(today);
        Day end = start.getEndDay(start, 100);
        ArrayList<Project> p = new ArrayList<>();
        for(int i=0; i<allTeams.size();i++)
        {
            Day start1, end1;
            int plen;
 
            p = findProjectOfTeam(allTeams.get(i));
            plen = search.getPlen();

            if(plen%allTeams.get(i).getNoOfMembers()!=0)
            {
                plen = (plen/allTeams.get(i).getNoOfMembers()) + 1;
            }
            else
            {
                plen = plen/allTeams.get(i).getNoOfMembers();
            }

            start1 = start;
            end1 = start.getEndDay(start1, plen);
            if(checkOverlap(allTeams.get(i), start1, end1))
            {

                Day starttest = start;
                Day endtest = end;
                if(checkOverlap(allTeams.get(i), start1, end1))
                {
                    int flag=1;
                    int ct = 0;
                    while(flag==1)
                    {
                        start1 = start.next(p.get(ct).getEndDay());
                        end1 = start.getEndDay(start1, plen);
                        if(checkOverlap(allTeams.get(i), start1, end1) == false)
                        {
                            flag = 0;
                        }
                        ct++;
                    }
                }
            }

            if(end.checklen(end)>end1.checklen(end1))
            {
                end = end1;
            }


        }

        for(int i=0; i<allTeams.size(); i++)
        {
            Day start1, end1;
            int plen;
            p = findProjectOfTeam(allTeams.get(i));

            plen = search.getPlen();
            if(plen%allTeams.get(i).getNoOfMembers()!=0)
            {
                plen = (plen/allTeams.get(i).getNoOfMembers()) + 1;
            }
            else
                plen = plen/allTeams.get(i).getNoOfMembers();

            start1 = start;
            end1 = start.getEndDay(start1, plen);
            if(checkOverlap(allTeams.get(i), start1, end1))
            {
                int flag=1;
                int ct = 0;
                while(flag==1)
                {
                    start1 = start.next(p.get(ct).getEndDay());
                    end1 = start.getEndDay(start1, plen);
                    if(checkOverlap(allTeams.get(i), start1, end1) == false)
                    {
                        flag = 0;
                    }
                    ct++;
                }
            }

            if(end.checklen(end)==end1.checklen(end1))
            {
                System.out.printf("%s (Work period: %s to %s)\n",allTeams.get(i).getTeamName(), start1.toString(), end1.toString());
            }
            }


    }

}