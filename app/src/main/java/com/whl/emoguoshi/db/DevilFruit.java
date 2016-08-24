package com.whl.emoguoshi.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Created by wanghl on 16/8/19.
 */
@Entity(
        nameInDb = "DevilFruit"
)
public class DevilFruit implements Serializable {
    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "fruitName")
    private String fruitName;
    @Property(nameInDb = "roleName")
    private String roleName;
    @Property(nameInDb = "roleNick")
    private String roleNick;
    @Property(nameInDb = "apearChapter")
    private String apearChapter;
    @Property(nameInDb = "descript")
    private String descript;
    @Property(nameInDb = "imgUrl")
    private String imgUrl;
    @Property(nameInDb = "type")
    private String type;
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getDescript() {
        return this.descript;
    }
    public void setDescript(String descript) {
        this.descript = descript;
    }
    public String getApearChapter() {
        return this.apearChapter;
    }
    public void setApearChapter(String apearChapter) {
        this.apearChapter = apearChapter;
    }
    public String getRoleNick() {
        return this.roleNick;
    }
    public void setRoleNick(String roleNick) {
        this.roleNick = roleNick;
    }
    public String getRoleName() {
        return this.roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getFruitName() {
        return this.fruitName;
    }
    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 753263903)
    public DevilFruit(Long id, String fruitName, String roleName, String roleNick,
            String apearChapter, String descript, String imgUrl, String type) {
        this.id = id;
        this.fruitName = fruitName;
        this.roleName = roleName;
        this.roleNick = roleNick;
        this.apearChapter = apearChapter;
        this.descript = descript;
        this.imgUrl = imgUrl;
        this.type = type;
    }
    @Generated(hash = 2116171308)
    public DevilFruit() {
    }
}
