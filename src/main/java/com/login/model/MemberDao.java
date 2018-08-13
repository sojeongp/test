package com.login.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.*;


@Configuration
@Repository
@Transactional
public class MemberDao {

    @Autowired
    private EntityManager entityManager;

    public void connectionTest() throws ClassNotFoundException, SQLException {
        // JDBC driver name and database URL
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:mem:testdb";

        //  Database credentials
        String USER = "sa";
        String PASS = "";


            Connection conn = null;
            Statement stmt = null;
            try {
                // STEP 1: Register JDBC driver
                Class.forName(JDBC_DRIVER);

                //STEP 2: Open a connection
                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                //STEP 3: Execute a query
                System.out.println("Creating table in given database...");
                stmt = conn.createStatement();
                String sql =  "CREATE TABLE   REGISTRATION " +
                        "(id INTEGER not NULL, " +
                        " first VARCHAR(255), " +
                        " last VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY ( id ))";
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");

                // STEP 4: Clean-up environment
                stmt.close();
                conn.close();
            } catch(SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch(Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            } finally {
                //finally block used to close resources
                try{
                    if(stmt!=null) stmt.close();
                } catch(SQLException se2) {
                } // nothing we can do
                try {
                    if(conn!=null) conn.close();
                } catch(SQLException se){
                    se.printStackTrace();
                } //end finally try
            } //end try
            System.out.println("Goodbye!");
    }


    public Member findMember(String id) throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:mem:testdb";

        String USER = "sa";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        Member member = new Member();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM MEMBER WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                member.setId(rs.getString("id"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
            }

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        if(member != null)
            return member;
        else
            return null;
    }

    public boolean findId(String id) throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "org.h2.Driver";
        String DB_URL = "jdbc:h2:mem:testdb";

        String USER = "sa";
        String PASS = "";

        Connection conn = null;
        Statement stmt = null;
        Member member = new Member();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            String sql = "SELECT * FROM MEMBER WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                member.setId(rs.getString("id"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
            }

            stmt.close();
            conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
        if(member.getId() == null)
            return false;
        else
            return true;
    }
}
