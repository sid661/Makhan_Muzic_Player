package com.JukeBox.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {


    public  Connection getConnection()
    {
        String url="jdbc:mysql://localhost:3306/makhan_muzic";
        String username="root";
        String pwd="sid@123";
        try
        {
            Connection con = DriverManager.getConnection(url,username,pwd);
            return con;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }


}
