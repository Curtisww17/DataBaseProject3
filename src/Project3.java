import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class Project3 {

	/**
	 * Object that stores the connection to the database
	 */
    private Connection conn;
    /**
     * This class represents a timeslot that has a starting time, day and an integer duration.
     *
     */
    class Timeslot {
    	String startingTime;
    	String day;
    	int duration;
    }
 /**
  * This constructor should create and initialize the connection to the database. 
  * @param username the mysql username
  * @param password the mysql password
  * @param schema the mysql schema
  */
    public Project3(String username, String password, String schema) {
		// Fill in code here to initialize conn so it connects to the database
		// using the provided parameters
		try {
			//Get a properties variable so we can pass the username and password to 
			// the database.
			Properties info = new Properties();
			//set the username and password appropriately
			info.put( "user", username );
			info.put( "password", password );
			//connect to the database
			conn = DriverManager.getConnection("jdbc:mysql://COMPDBS300/"+ schema, info);
			//if all goes well, this statement should print
			System.out.println("Connection successful!");	
		} catch (SQLException ex) {
			//if an exception is thrown, display the message so that we know 
			// what went wrong.
			System.out.print(ex.getMessage());
		}	
	}
    
    /**  This method implements:
     * List all projects: Display for each project, its description, 
     * the name of the organization(s) that host it and the name of its first category. 
     * Outputs: a table containing for each project, its description, the name of the organization(s)
     *  that host it and the name of its first category.
     *  @param none. 
     *  @return none.
     */
    public void listAllProjects() {
    	try {
			PreparedStatement pstmt = conn.prepareStatement("select description,  from project");
			// Specify the SQL query to run and execute the query. 
			// Store the result in a ResultSet Object
			ResultSet rst = pstmt.executeQuery();
			// Get the metadata of the query and store it in a ResultSetMetaData object
			ResultSetMetaData rsmd = rst.getMetaData();
			// Get the number of columns retrieved 
			int numberOfColumns = rsmd.getColumnCount();
			// Go over the columns and print the name of the columns. 
			for (int i =0; i< numberOfColumns; i++ )
				// Note that the columns start at 1
				System.out.print(rsmd.getColumnName(i+1)+ "\t");
			System.out.println();
			// Go over the rows in the ResultSet object
			while (rst.next()) {
				String row = "";
				// Use getString if you are reading a varchar. 
				// Again note that the column number starts at 1
				// There are getInt(), getDouble(), getDate() and many other methods to read data.
				row += rst.getString(1) + "\t";
				row += rst.getString(2) + "\t";
				row += rst.getString(3);
				System.out.println(row);	
			}
			// Make sure you close the statement object when you are done.
			pstmt.close();

			conn.close();
		} catch (SQLException e) {
			System.out.println("Oops");
		}

    }
    /**  This method implements: Find project by date: Find the projects (all attributes of project) 
     * that start on a particular date. 
     * Outputs: displays all the project attributes in a table format. 
     * Error handling: print an error message if the date inputted is not valid.
     * @param date: a string  .
     */
    public void findProjectByDate(String date) {
    }
    /** This method implements Find volunteers by location: 
     * Find the names of all the volunteers who live in a town or a city. 
     * Outputs: displays all the volunteer names. 
     * Action: using a single SQL query, this method searches
     *  the database for volunteers whose address contains the specified town or city
     * @param town a string  .
     * 
     */
    public void findVolunteersByLocation(String town) {
    }
    /** Search by category: Display all the project IDs and descriptions of projects that belong to 
     * the specified category. 
     * Error handling: print an error message if the category cannot be found.
     * @param name of the category as  .
     */
    public void searchByCategory(String name) {
    }
    /** Search by keywords: Display all the project IDs and descriptions of 
     * projects whose description matches the keywords entered. 
     * Outputs: displays project IDs and descriptions of projects whose description matches the keywords entered 
     * Action: using a single SQL query, this method searches the database for the requested data.
     * Error handling: print an error message if there are no projects that match the keywords. 
     * 
     * @param keywords one or more keywords as  .
	 */
    public void searchByKeywords(String[] keywords) {
    }
    /** List needed volunteers: This should allow the user to view the number of
     *  volunteers still needed for each timeslot belonging to a particular project.
     *  Outputs: a table containing the date and starting time for each timeslot along with the number of volunteers still needed. 
     *  Action: This method should use a single SQL query to search the database for the timeslots belonging to a project and calculating the number of volunteers needed. 
     *  Error handling: print an error message if the projID is not valid. 
     *  @param projID int value representing a valid projID.
	*/
    public void neededVolunteers(int projID) {
    }

    /** Change the duration of a timeslot: change the duration of a timeslot to a new value. 
     * Outputs: displays a message that the duration was changed. Returns true if successful, false otherwise 
     * Action: updates the duration of the specified timeslot in the database 
     * Error handling: print an error message if the timeslot cannot be found.
     * @param projID: project ID
     * @param date: date of timeslot
     * @param time: starting time of timeslot 
     * @param newDuration: new duration as inputted from the user.
     */
    public boolean changeDuration(int projID, String date, String time, int newDuration) {
    	return false;
    }
    /** Volunteer for a timeslot: this method allows the user to volunteer for a timeslot. 
     * Outputs: displays a message that the sign up was complete/incomplete.
     * Action: this method has two actions:
     * Make sure that the timeslot is available: It should check if the status of the project is “open” and then compare the number of volunteers registered to the number of volunteers needed for the timeslot. If more volunteers are needed for this timeslot, then proceed to Action ii. Otherwise, display a message notifying the volunteer that this timeslot is fulfilled. 
     * Insert the necessary data into the database. 
     * Error handling: print an error message if the timeslot cannot be found or the volunteer ID cannot be found.
     * @param project ID
     * @param date of timeslot
     * @param starting time of timeslot 
     * @param volunteer ID as inputted from the user.	
     */
    public boolean volunteer (int projID, String date, String time, int VolID) {
    	return false;
    }
    /** Add a project: the user needs to specify the information about the project. 
     * Then, the application adds the new project to the database. 
     * Output: a confirmation message when the project has been inserted
     * Action: this method inserts the project into the database. There are multiple insertions and queries as part of this method. You can use helper methods to split up the work. 
     * Error handling: print an error message if the enddate is earlier than startingDate or if startingDate is earlier than today’s date or if the user specified a category that does not exist.
     * @param Organization number  
     * @param projID calculated by the application by incrementing the highest projID in the database.
     * @param description  
     * @param startingDate  
     * @param endDate  
     * @param location  
     * @param number of volunteers needed  
     * @param one or more categories   as category name. If more than one category is inputted, the order by which they are inputted indicates their ranking, e.g., the first category has ranking 1.
     * @param one or more timeslots  
     */
    
    public boolean addProject(int orgNo, String description, String startingDate, String endDate, String location, int nVolunteers, String [] categories, Timeslot[] timeslots) {
    	return true;
    }
    /**
     * This method implements the functionality necessary to exit the application: 
     * this should allow the user to cleanly exit the application properly.
     * This should close the connection and any prepared statements.  
     */
    public void exitApplication() {
    }
    
    
    public static void main(String args[]) {
		Project3 myProject = new Project3("u209505","p209505","schema209505");
	}
    
}

 class Driver {
	
}

//String username = "u209505";
//String pass = "p209505";
//String schema = "schema209505";
