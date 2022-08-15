package dev.macklinr.daotests;

import dev.macklinr.daos.ComplaintDAO;
import dev.macklinr.daos.ComplaintDaoDB;
import dev.macklinr.utils.ConnectionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComplaintTests
{
    private static final String complaintTable = "testComplaint";
    ComplaintDAO complaintDAO = new ComplaintDaoDB(complaintTable);


    @BeforeAll
    static void setup()
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "<pending ERD approval>";

            Statement statement = conn.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Test cases pending ERD approval



    @AfterAll
    static void teardown()
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "drop table " + complaintTable + ";";

            Statement statement = conn.createStatement();
            statement.execute(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
