package com.livos.companionplants.data.local.database.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.livos.companionplants.data.local.database.model.DaoMaster;
import com.livos.companionplants.util.ApplicationScope;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@ApplicationScope
public class DatabaseOpenHelper extends DaoMaster.OpenHelper {
    private String TAG = DatabaseOpenHelper.class.getSimpleName();
    private static final String SP_KEY_DB_VER = "db_ver";
    private static final int DATABASE_VERSION = 2;

    private Context context;
    private SharedPreferences sharedPreferences;

    private SQLiteDatabase sqliteDatabase;

    private static String DB_PATH;

    private static String DB_NAME;

    public DatabaseOpenHelper(Context context, SharedPreferences sharedPreferences, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        DB_NAME = name;

        try {
            createDataBase();
        } catch (Exception ioe) {
            throw new Error("Unable to create database");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    /** Open Database for Use */
    public void openDatabase() {
        String databasePath = context.getDatabasePath(DB_NAME).toString();
        sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null,
                (SQLiteDatabase.OPEN_READWRITE));
    }

    /** Close Database after use */
    @Override
    public synchronized void close() {
        if ((sqliteDatabase != null) && sqliteDatabase.isOpen()) {
            sqliteDatabase.close();
        }
        super.close();
    }

    /** Get database instance for use */
    public SQLiteDatabase getSqliteDatabase() {
        return sqliteDatabase;
    }

    /** Create new database if not present */
    public void createDataBase() {
        SQLiteDatabase sqliteDatabase;

        if (databaseExists()) {
            int dbVersion = sharedPreferences.getInt(SP_KEY_DB_VER, 1);
            /* If different version then delete current database and copy the new one from assets*/
            if (DATABASE_VERSION != dbVersion) {
                File dbFile = context.getDatabasePath(DB_NAME);
                boolean dbFileDeleted = dbFile.delete();
                if (!dbFileDeleted) {
                    Log.w(TAG, "Unable to update database");
                } else {
                    createDataBase();
                }
            }
        } else {
            /* Database does not exists create blank database */
            sqliteDatabase = this.getReadableDatabase();
            sqliteDatabase.close();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(SP_KEY_DB_VER, DATABASE_VERSION);
            editor.apply();

            copyDataBase();
        }
    }

    /** Check Database if it exists */
    private boolean databaseExists() {
        SQLiteDatabase sqliteDatabase = null;
        try {
            String databasePath = context.getDatabasePath(DB_NAME).toString();
            sqliteDatabase = SQLiteDatabase.openDatabase(databasePath, null,
                    SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        if (sqliteDatabase != null) {
            sqliteDatabase.close();
        }
        return sqliteDatabase != null;
    }

    /**
     * Copy existing database file in system
     */
    public void copyDataBase() {

        int length;
        byte[] buffer = new byte[1024];
        String databasePath = context.getDatabasePath(DB_NAME).toString();

        try {
            InputStream databaseInputFile = this.context.getAssets().open(DB_NAME);
            OutputStream databaseOutputFile = new FileOutputStream(databasePath);

            while ((length = databaseInputFile.read(buffer)) > 0) {
                databaseOutputFile.write(buffer, 0, length);
                databaseOutputFile.flush();
            }
            databaseInputFile.close();
            databaseOutputFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isRoboUnitTest() {
        return "robolectric".equals(Build.FINGERPRINT);
    }
}