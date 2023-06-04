package com.example.chatapp_idan;

public class itemMessage {
    public String userId;
    public String userName;
    public String userPhoto;
    public String message;
    public String time;

    public itemMessage(String userId, String userName, String userPhoto, String message, String time) {
        this.userId = userId;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.message = message;
        this.time = time;
    }
}
