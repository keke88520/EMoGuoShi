package com.whl.emoguoshi.model;

import com.whl.emoguoshi.app.App;
import com.whl.emoguoshi.db.DevilFruit;
import com.whl.emoguoshi.db.DevilFruitDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

/**
 * Created by wanghl on 16/8/24.
 */
public class FruitModel {
    public List<DevilFruit> getFruitsByType(String type) {

//        String string = String.format("%%%s%%", type);

        QueryBuilder<DevilFruit> qb =  App.daoSession.getDevilFruitDao().queryBuilder();
//        WhereCondition whereCondition = qb.or(DevilFruitDao.Properties.FruitName.like(type),
//                DevilFruitDao.Properties.RoleName.like(string),
//                DevilFruitDao.Properties.RoleNick.like(string),
//                DevilFruitDao.Properties.Descript.like(string));
        qb.where(DevilFruitDao.Properties.Type.eq(type));
        return qb.list();
    }

    public List<DevilFruit> getFruitsByKeyWord(String keyword) {

        String string = String.format("%%%s%%", keyword);

        QueryBuilder<DevilFruit> qb =  App.daoSession.getDevilFruitDao().queryBuilder();
        WhereCondition whereCondition = qb.or(DevilFruitDao.Properties.FruitName.like(string),
                DevilFruitDao.Properties.RoleName.like(string),
                DevilFruitDao.Properties.RoleNick.like(string),
                DevilFruitDao.Properties.Descript.like(string));
        qb.where(whereCondition);
        return qb.list();
    }
}
