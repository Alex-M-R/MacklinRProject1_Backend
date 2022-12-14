package dev.macklinr.daos;

import dev.macklinr.entities.Meeting;
import dev.macklinr.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoDB implements MeetingDAO
{
    String tableName;

    public MeetingDaoDB()
    {
        super();
        tableName = "meeting";  // default name
    }

    public MeetingDaoDB(String tableName) {this.tableName = tableName;}

    @Override
    public Meeting createMeeting(Meeting meeting)
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "insert into " + this.tableName + " values (default, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, meeting.getAddress());
            preparedStatement.setLong(2, meeting.getScheduledDate());
            preparedStatement.setString(3,meeting.getSummary());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys(); // returns the id that was created
            rs.next();   // have to move cursor to the first valid record

            int generatedKey = rs.getInt("id");

            meeting.setId(generatedKey);
            return meeting;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Meeting getMeetingByID(int id)
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from " + this.tableName + " where id= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            Meeting meeting = new Meeting();

            meeting.setId(rs.getInt("id"));
            meeting.setAddress(rs.getString("address"));
            meeting.setScheduledDate(rs.getLong("time"));
            meeting.setSummary(rs.getString("summary"));

            return meeting;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Meeting> getAllMeetings()
    {
        try(Connection conn = ConnectionUtil.createConnection())
        {
            String sql = "select * from " + this.tableName;

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Meeting> meetingsList = new ArrayList<Meeting>();
            while(rs.next())
            {
                Meeting meeting = new Meeting();
                meeting.setId(rs.getInt("id"));
                meeting.setAddress(rs.getString("address"));
                meeting.setScheduledDate(rs.getInt("time"));
                meeting.setSummary(rs.getString("summary"));

                meetingsList.add(meeting);
            }

            return meetingsList;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<Meeting>();
        }
    }
}
