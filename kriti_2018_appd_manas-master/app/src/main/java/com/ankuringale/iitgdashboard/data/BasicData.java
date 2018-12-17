package com.ankuringale.iitgdashboard.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicData implements Comparable<BasicData> {
    private String sender;
    private String subject;
    private String basic;
    private String eventDate;

    public BasicData(String sender, String subject, String basic, String date) {
        this.sender = sender;
        this.subject = subject;
        this.basic = basic;
        this.eventDate = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String from) {
        this.sender = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
    public BasicData()
    {}

    public int compareTo(BasicData person) {
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.getEventDate());
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(person.getEventDate());
            return date1.compareTo(date2)*(-1);
        }
        catch(Exception e){
            e.printStackTrace();
            return -99;
        }
    }
}
