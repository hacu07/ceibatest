package co.com.ceiba.mobile.pruebadeingreso.utils;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;

public interface Constants {
    int SUCCESS = 0;
    int DATA_ERROR = 100;
    int RESPONSE_ERROR = 101;
    int CONNECTION_ERROR = 102;
    String CREATE_USERS_TABLE  = "CREATE TABLE " + User.Companion.getTABLE_NAME() + "( " +
    User.Companion.getID()    + " INTEGER, "  +
    User.Companion.getNAME()  + " TEXT, "     +
    User.Companion.getEMAIL() + " TEXT,"      +
    User.Companion.getPHONE() + " TEXT,"      +
    "PRIMARY KEY ("+ User.Companion.getID() +") )";
    String DB_NAME = "users_db";
}
