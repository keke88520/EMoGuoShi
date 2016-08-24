package com.whl.emoguoshi.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by wanghl on 16/2/25.
 */
public class GuoShiDevOpenHelper extends DaoMaster.OpenHelper {
    private static final String TAG = GuoShiDevOpenHelper.class.getSimpleName();
    private Context mContext;
    private SQLiteDatabase mDefaultWritableDatabase = null;

    public GuoShiDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
        mContext = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.mDefaultWritableDatabase = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        this.mDefaultWritableDatabase = db;

        try {
            InputStream in = mContext.getAssets().open("guoshi.sql");
            SQLiteDatabase database = getWritableDatabase();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String sqlUpdate = null;
            while ((sqlUpdate = bufferedReader.readLine()) != null) {
                if (!TextUtils.isEmpty(sqlUpdate)) {
                    database.execSQL(sqlUpdate);
                }
            }
            bufferedReader.close();
            in.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        final SQLiteDatabase db;
        if (mDefaultWritableDatabase != null) {
            db = mDefaultWritableDatabase;
        } else {
            db = super.getWritableDatabase();
        }
        return db;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.mDefaultWritableDatabase = db;
    }
}
