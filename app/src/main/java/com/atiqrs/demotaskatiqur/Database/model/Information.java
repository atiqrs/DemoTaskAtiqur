package com.atiqrs.demotaskatiqur.Database.model;

public class Information {
    public static final String TABLE_NAME = "info";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MOBILE = "mobile";
    public static final String COLUMN_EMAIL = "email";

    private int id;
    private String name;
    private String mobile;
    private String email;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_MOBILE + " TEXT,"
                    + COLUMN_EMAIL + " TEXT"
                    + ")";

    public Information() {
    }

    public Information(int id, String name, String mobile, String email) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMobile() {
        return mobile;
    }
    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
