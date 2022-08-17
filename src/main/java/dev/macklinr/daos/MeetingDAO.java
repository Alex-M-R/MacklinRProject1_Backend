package dev.macklinr.daos;

import dev.macklinr.entities.Meeting;

import java.util.List;

public interface MeetingDAO
{
    Meeting createMeeting(Meeting meeting);

    List<Meeting> getAllMeetings();
}
