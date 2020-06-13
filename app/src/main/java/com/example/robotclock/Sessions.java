package com.example.robotclock;

public class Sessions {
    /*
    private String answerValue;
    private String clockNum;
     */
    /*
    private String tile;
    private String author;
    private String code;
    private String category;
     */
    private String Name;
    private String Time;
    // tak crash
    private int correct;
    private int incorrect;
    private int noRespond;



    public Sessions() {
    }

    public Sessions(String name, String time, int correct, int incorrect, int noRespond) {
        Name = name;
        Time = time;
        this.correct = correct;
        this.incorrect = incorrect;
        this.noRespond = noRespond;
    }

    public String getName() {
        return Name;
    }

    public String getTime() {
        return Time;
    }

    public int getCorrect() {
        return correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public int getNoRespond() {
        return noRespond;
    }

    // punca crash?





}

