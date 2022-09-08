package compare;

//import com.sun.jdi.connect.spi.Connection;
import org.testng.Assert;

import java.sql.*;

public class CompareTables {

    //This method will open and return DB connection
    private Connection openDbConnection() {
        Connection connection = null;

        try {
            String dbURL = "jdbc:sqlserver://GUY\\MSSQLSERVER_GUY;databaseName=Test_DB;encrypt=false";
            String user = "test_user";
            String pass = "test_pass";
            connection = DriverManager.getConnection(dbURL, user, pass);
            Statement statement = connection.createStatement();
            if (connection != null) {
                System.out.println("Connection is Open");
                DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    // This method will receive DB connection instance and close it
    private void closeDbConnection(Connection connection) {
        try {
            // If the received connection is Open then Close it
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection is Closed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //This methode will call Open DB connection method, execute SQL query and will call Close DB connection method
    public void compareTables() throws SQLException {
        // Open Connection to the DB
        Connection connection = openDbConnection();

        // Execute SQL Query
        ResultSet resultSet = null;
        String selectSql = "EXEC spCompareClasses";
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(selectSql);
        int rowcount = 0;
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            rowcount++;
        }
        System.out.println("Number of records: " + rowcount);
        Assert.assertEquals(rowcount, 0);

        // Close Connection to the DB
        closeDbConnection(connection);
    }
}
