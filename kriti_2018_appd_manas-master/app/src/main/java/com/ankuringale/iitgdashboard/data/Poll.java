package com.ankuringale.iitgdashboard.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Poll implements Comparable<Poll> {
    private String question;
    private int numberYes;
    private int numberNo;
    private String dateOfCreation;

    public Poll() {

    }

    public Poll(String question, int numberYes, int numberNo, String dateOfCreation) {
        this.question = question;
        this.numberYes = numberYes;
        this.numberNo = numberNo;
        this.dateOfCreation = dateOfCreation;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumberYes() {
        return numberYes;
    }

    public int getNumberNo() {
        return numberNo;
    }



    public String getDateOfCreation() {
        return dateOfCreation;
    }


    public int compareTo(Poll poll){
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(this.getDateOfCreation());
            Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(poll.getDateOfCreation());
            return (-1)*date1.compareTo(date2);
        }catch(Exception e) {
            e.printStackTrace();
            return -99;
        }
    }
}
