package co.com.ceiba.mobile.pruebadeingreso.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import co.com.ceiba.mobile.pruebadeingreso.users.dto.User;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String sDrop = "DROP TABLE IF EXISTS ";
        db.execSQL(sDrop + User.Companion.getTABLE_NAME());
        onCreate(db);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
