import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main 
{

	public static void main(String[] args) throws FileNotFoundException, ExInsufficientArguments {

		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
        Scanner inFile = null;
        
        try
        {
            inFile = new Scanner(new File(filepathname));
		
            String cmdLine1 = inFile.nextLine();
            String[] cmdLine1Parts = cmdLine1.split("\\|");
            System.out.println("\n> "+cmdLine1);
            SystemDate.createTheInstance(cmdLine1Parts[1]);
            
            while (inFile.hasNext())		
            {
                String cmdLine = inFile.nextLine().trim();
                
                if (cmdLine.equals("")) continue;  
    
                System.out.println("\n> "+cmdLine);
                
                String[] cmdParts = cmdLine.split("\\|"); 

                if (cmdParts[0].equals("hire"))
                    (new CmdHire()).execute(cmdParts);
                
                else if (cmdParts[0].equals("listEmployees"))
                    (new CmdListEmployees()).execute(cmdParts);
            
                else if (cmdParts[0].equals("startNewDay"))
                    (new SystemDayCreate()).execute(cmdParts);
                
                else if (cmdParts[0].equals("setupTeam"))
                    (new CmdTeamCreate()).execute(cmdParts);
                
                else if (cmdParts[0].equals("listTeams"))
                    (new CmdTeamList()).execute(cmdParts);
                
                else if (cmdParts[0].equals("createProject"))
                    (new CmdCreateProject()).execute(cmdParts);

                else if (cmdParts[0].equals("listProjects"))
                    (new CmdProjectList()).execute(cmdParts);
                
                else if (cmdParts[0].equals("joinTeam"))
                    (new CmdJoinTeam()).execute(cmdParts);

                else if (cmdParts[0].equals("changeTeam"))
                    (new CmdChangeTeam()).execute(cmdParts);
            
                else if (cmdParts[0].equals("takeProject"))
                    (new CmdTakeProject()).execute(cmdParts);

                else if(cmdParts[0].equals("suggestTeam"))
                    (new CmdSuggestTeam()).execute(cmdParts);

                else if(cmdParts[0].equals("showEmployeeDetails"))
                    (new CmdShowEmpDetails()).execute(cmdParts);
                
                else if(cmdParts[0].equals("showProjectWorkerDetails"))
                    (new CmdShowProjWorker()).execute(cmdParts);
      
                else if (cmdParts[0].equals("undo"))
                    RecordedCommand.undoOneCommand();
            
                else if (cmdParts[0].equals("redo"))
                    RecordedCommand.redoOneCommand();
                
                else if (cmdParts[0].equals("lisProjects"));
                
                else
                    throw new ExWrongCommand();
                    
            }

            
        }

        catch (FileNotFoundException e) 
        {
            System.out.println("File not found.");
        } 
        
        catch (InputMismatchException e) 
        {
            System.out.println("File content problem. Program terminated.");
        }
        
        catch (ExWrongCommand e) 
        {
            System.out.println("Unknown command - ignored.");
        } 
        
        finally 
        {
            if (inFile!=null) //If it has been opened successfully, close it
                inFile.close();
                        
            in.close();
        }


	}
}
