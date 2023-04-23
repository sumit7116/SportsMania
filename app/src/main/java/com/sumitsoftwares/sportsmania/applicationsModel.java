package com.sumitsoftwares.sportsmania;

public class applicationsModel {
    private String UserFirstName;
    private String UserLastName;
    private String UserGender;
    private String UserFatherName;
    private String UserEmail;
    private String UserMobileNo;
    private String UserSportAchievement;
    private String UserSportApply;
    private String UserTransactionID;

    public applicationsModel() {
    }

    public applicationsModel(String userFirstName, String userLastName, String userGender, String userFatherName, String userEmail, String userMobileNo, String userSportAchievement, String userSportApply, String userTransactionID) {
        UserFirstName = userFirstName;
        UserLastName = userLastName;
        UserGender = userGender;
        UserFatherName = userFatherName;
        UserEmail = userEmail;
        UserMobileNo = userMobileNo;
        UserSportAchievement = userSportAchievement;
        UserSportApply = userSportApply;
        UserTransactionID = userTransactionID;
    }


    public String getUserFirstName() {
        return UserFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        UserFirstName = userFirstName;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public void setUserLastName(String userLastName) {
        UserLastName = userLastName;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public String getUserFatherName() {
        return UserFatherName;
    }

    public void setUserFatherName(String userFatherName) {
        UserFatherName = userFatherName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserMobileNo() {
        return UserMobileNo;
    }

    public void setUserMobileNo(String userMobileNo) {
        UserMobileNo = userMobileNo;
    }

    public String getUserSportAchievement() {
        return UserSportAchievement;
    }

    public void setUserSportAchievement(String userSportAchievement) {
        UserSportAchievement = userSportAchievement;
    }

    public String getUserSportApply() {
        return UserSportApply;
    }

    public void setUserSportApply(String userSportApply) {
        UserSportApply = userSportApply;
    }

    public String getUserTransactionID() {
        return UserTransactionID;
    }

    public void setUserTransactionID(String userTransactionID) {
        UserTransactionID = userTransactionID;
    }
}
