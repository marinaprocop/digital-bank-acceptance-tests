package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    //This method is used to establish connection with Data Base
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    public static void establishConnection(){
        String url = "jdbc:mysql://3.249.240.23:3306/marinapr1214";
        String username = "marinapr1214";
        String password = "omverfbrtikyltan";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //A method that can dynamically sent Select Statements
    //and return a list of map of all columns
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){
        List<Map<String, Object>> dbResultList = new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            //getMetaData method returns the info about your info
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            while(resultSet.next()){
                Map<String, Object> rowMap = new HashMap<>();
                for(int col = 1; col<= columnCount; col++){
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dbResultList;
    }

    //delete or truncate method
    public static int runSQLUpdateQuery(String sqlQuery){
        int rowsAffected = 0;
        try {
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsAffected;
    }


    //method to close the connection
    public static void closeConnection(){
        try{
            if(resultSet != null ){
             resultSet.close();
            }
            if(statement != null ) {
                statement.close();
            }
            if(connection != null ) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
