package com.whl.emoguoshi.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.whl.emoguoshi.adapter.WallPaperListAdapter;
import com.whl.emoguoshi.domain.WallPaperBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by wanghl on 16/8/25.
 */
public class PaperModel {
    private static final String TAG = "PaperModel";

    public List<WallPaperBean> loadPapers(Context mContext) {
        List<WallPaperBean> wallPaperBeanList = new ArrayList<>();

        try {
            InputStream in = mContext.getAssets().open("screenPic.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String sqlUpdate = null;
            while ((sqlUpdate = bufferedReader.readLine()) != null) {
                if (!TextUtils.isEmpty(sqlUpdate)) {
                    WallPaperBean bean = new WallPaperBean();
                    bean.setType(WallPaperListAdapter.TYPE_ITEM);
                    bean.setUrl(sqlUpdate);
                    wallPaperBeanList.add(bean);
                }
            }
            bufferedReader.close();
            in.close();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        List<WallPaperBean> wallPaperBeanListNew = new ArrayList<>();
        for (int i = 0; i < wallPaperBeanList.size(); i++) {
            if (i%6 == 0) {
                WallPaperBean ad = new WallPaperBean();
                ad.setType(WallPaperListAdapter.TYPE_AD);
                wallPaperBeanListNew.add(ad);
            }
            wallPaperBeanListNew.add(wallPaperBeanList.get(i));
        }

        return wallPaperBeanListNew;
    }
}
