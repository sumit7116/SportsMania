package com.sumitsoftwares.sportsmania;

public class LiveCourseData {
    public String sportName;
    public String sportImage;


    public LiveCourseData(String sportName, String sportImage) {
        this.sportName = sportName;
        this.sportImage = sportImage;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportImage() {
        return sportImage;
    }

    public void setSportImage(String sportImage) {
        this.sportImage = sportImage;
    }
}
