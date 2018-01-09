package itg8.com.serviceapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import itg8.com.serviceapp.showcase.model.BannerModel;

/**
 * Created by Android itg 8 on 10/25/2017.
 */



public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "ormlite.db";
    private static final int    DATABASE_VERSION = 1;

    private Dao<BannerModel, Integer> mDAOBanner = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, BannerModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, BannerModel.class, true);
          onCreate(database,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<BannerModel, Integer> getmDAOBanner() throws SQLException {
        if (mDAOBanner == null) {
            mDAOBanner = getDao(BannerModel.class);
        }

        return mDAOBanner;
    }

    @Override
    public void close() {
        mDAOBanner = null;
        super.close();
    }

}
