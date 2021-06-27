package co.com.ceiba.mobile.pruebadeingreso.users.model.dao.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;
import co.com.ceiba.mobile.pruebadeingreso.utils.Constants;
import co.com.ceiba.mobile.pruebadeingreso.utils.SQLiteHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsersDBDao {
    public SQLiteHelper sqLiteHelper;

    public UsersDBDao(Context context){
        sqLiteHelper = new SQLiteHelper(context, Constants.DB_NAME, null, 1);
    }

    public ArrayList<User> getUsersFromDatabase(){
        ArrayList<User> users = new ArrayList<>();

        String[] columns = {
                User.Companion.getID(),
                User.Companion.getNAME(),
                User.Companion.getEMAIL(),
                User.Companion.getPHONE()
        };
        SQLiteDatabase database = sqLiteHelper.getReadableDatabase();
        Cursor cursor = database.query(User.Companion.getTABLE_NAME(), columns, null,
                null, null, null, null);

        if (cursor != null){
            if (cursor.moveToFirst()){
                do{
                    users.add(new User(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    ));
                }while(cursor.moveToNext());
            }

            cursor.close();
        }

        return users;
    }

    public void insertUsers(ArrayList<User> users){
        ContentValues values = null;
        SQLiteDatabase database = sqLiteHelper.getWritableDatabase();

        for (User user: users) {
            values = new ContentValues();

            values.put(User.Companion.getID(), user.getId());
            values.put(User.Companion.getNAME(), user.getName());
            values.put(User.Companion.getEMAIL(), user.getEmail());
            values.put(User.Companion.getPHONE(), user.getPhone());


            Long idRow = database.insert(User.Companion.getTABLE_NAME(), User.Companion.getID(), values);

            Log.d("HaroldTest", "Row Id:" + String.valueOf(idRow));
        }
    }
}
