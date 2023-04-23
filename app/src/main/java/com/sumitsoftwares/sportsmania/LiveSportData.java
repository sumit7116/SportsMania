package com.sumitsoftwares.sportsmania;

public class LiveSportData {
    public String sportName;
    public String sportImage;

    public LiveSportData(String sportName, String sportImage) {
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
