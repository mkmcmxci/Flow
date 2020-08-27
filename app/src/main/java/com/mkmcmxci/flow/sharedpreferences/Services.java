package com.mkmcmxci.flow.sharedpreferences;

public class Services {

    final static String slash = "/";

    public static String getAllQuestions() {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getallquestions/";

        return urlBody;

    }

    public static String getQuestionByAnswer() {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/getquestionsbyanswer/";

        return urlBody;

    }

    public static String addQuestion(String title, String content, int catID, int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/addquestion/";
        String urlVar = title + slash + content + slash + catID + slash + userID;

        return urlBody + urlVar;

    }

    public static String deleteQuestion(int questionID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/deletequestion/";
        String urlVar = String.valueOf(questionID);

        return urlBody + urlVar;

    }

    public static String questionByUser(int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbyuser/";
        String urlVar = String.valueOf(userID);

        return urlBody + urlVar;

    }

    public static String questionByCategory(int categoryID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/questionsbycategory/";
        String urlVar = String.valueOf(categoryID);

        return urlBody + urlVar;

    }


    public static String answerByQuestion(int questionID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/answerbyquestion/";
        String urlVar = String.valueOf(questionID);

        return urlBody + urlVar;

    }

    public static String editQuestion(int questionID, String title, String content) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/questionwebservices/editquestion/";
        String urlVar = String.valueOf(questionID) + slash + title + slash + content;

        return urlBody + urlVar;

    }

    public static String answerByUser(int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/answerbyuser/";
        String urlVar = String.valueOf(userID);

        return urlBody + urlVar;

    }

    public static String addAnswer(String content, int questionID, int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/addanswer/";
        String urlVar = content + slash + questionID + slash + userID;

        return urlBody + urlVar;

    }

    public static String deleteAnswer(int answerID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/answerwebservices/deleteanswer/";
        String urlVar = String.valueOf(answerID);

        return urlBody + urlVar;

    }

    public static String getUserByMailAndPassword(String mail, String password) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/getuserbymailandpassword/";
        String urlVar = mail + slash + password;

        return urlBody + urlVar;

    }

    public static String getUserByID(int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/getuserbyid/";
        String urlVar = String.valueOf(userID);

        return urlBody + urlVar;

    }

    public static String registerUser(String mail, String username, String password) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/registeruser/";
        String urlVar = mail + slash + username + slash + password;

        return urlBody + urlVar;

    }

    public static String deleteUser(int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/deleteuser/";
        String urlVar = String.valueOf(userID);

        return urlBody + urlVar;

    }

    public static String updateUser(int userID, String mail, String username) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/updateuser/";
        String urlVar = String.valueOf(userID) + slash + mail + slash + username;

        return urlBody + urlVar;

    }

    public static String updateUserPassword(int userID, String password) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/userwebservices/updateuserpassword/";
        String urlVar = String.valueOf(userID) + slash + password;

        return urlBody + urlVar;

    }

    public static String addConversation(int senderID, int receiverID, String reply) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/messagewebservices/addconversation/";
        String urlVar = String.valueOf(senderID) + slash + String.valueOf(receiverID) + slash + reply;

        return urlBody + urlVar;

    }

    public static String getMessages(int senderID, int receiverID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/messagewebservices/getmessages/";
        String urlVar = String.valueOf(senderID) + slash + String.valueOf(receiverID);

        return urlBody + urlVar;

    }

    public static String getConversation(int userID) {

        String urlBody = "http://10.0.2.2:8080/BulletinBoard/rest/messagewebservices/getconversation/";
        String urlVar = String.valueOf(userID);

        return urlBody + urlVar;

    }

}

