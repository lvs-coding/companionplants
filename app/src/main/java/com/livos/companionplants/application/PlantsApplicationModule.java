package com.livos.companionplants.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;

import com.livos.companionplants.R;
import com.livos.companionplants.data.local.database.AppDatabaseHelper;
import com.livos.companionplants.data.local.database.DatabaseHelper;
import com.livos.companionplants.data.local.database.model.DaoMaster;
import com.livos.companionplants.data.local.database.model.DaoSession;
import com.livos.companionplants.data.local.database.utils.DatabaseOpenHelper;
import com.livos.companionplants.util.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlantsApplicationModule {
    private final Context context;

    public PlantsApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return context;
    }

    @Provides
    @ApplicationScope
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @ApplicationScope
    String provideDatabaseFileName(Context context) {
        return  context.getResources().getString(R.string.database_file_name);
    }

    @Provides
    @ApplicationScope
    DatabaseOpenHelper provideDatabaseOpenHelper(Context context, SharedPreferences sharedPreferences, String databaseFileName) {
        return new DatabaseOpenHelper(context,sharedPreferences, databaseFileName, null);
    }

    @Provides
    @ApplicationScope
    SQLiteDatabase provideSqliteDatabase(DatabaseOpenHelper databaseOpenHelper) {
        return  databaseOpenHelper.getReadableDatabase();
    }

    @Provides
    @ApplicationScope
    DaoMaster provideDaoMaster(SQLiteDatabase sqliteDatabase) {
        return new DaoMaster(sqliteDatabase);
    }

    @Provides
    @ApplicationScope
    DaoSession provideDaoSession(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @Provides
    @ApplicationScope
    DatabaseHelper provideAppDatabaseHelper(DaoSession daoSession) {
        return  new AppDatabaseHelper(daoSession);
    }

}
