package com.rminfo.jinmaocs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by carl on 2017/1/3.
 */
@Table(name = "R5RMAPPUSERS")
public class R5rmAppUser implements Serializable{
    @Id
    @GeneratedValue(generator = "UUID")
    private String usrid;
    private String usrcode;
    private String usrdesc;
    private String usrremark;
    @Column(name = "USRENTITY")
    private String entity ="AUSR";
    private String usrpassword;
    private Date usrvalidate;
    private String usrisadmin ="-";
    private String usrisapp="+";
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date usrappvalidate;
    private String usrstatus;
    private String usrnotused;
    @Column(name = "USRCREATEBY")
    private String createdby;
    @Column(name = "USRCREATED")
    private Date created;
    @Column(name = "USRUPDATEBY")
    private String updatedby;
    @Column(name = "USRUPDATED")
    private Date updated;
    private String usrorg;
    private String usrversion="1.00.00";
    private String usrtenant="*";
    private String usrudfchar01;
    private String usrudfchar02;
    private String usrudfchar03;
    private String usrudfchar04;
    private String usrudfchar05;
    private String usrudfchar06;
    private String usrudfchar07;
    private String usrudfchar08;
    private String usrudfchar09;
    private String usrudfchar10;
    private String usrudfchar11;
    private String usrudfchar12;
    private String usrudfchar13;
    private String usrudfchar14;
    private String usrudfchar15;
    private String usrudfchar16;
    private String usrudfchar17;
    private String usrudfchar18;
    private String usrudfchar19;
    private String usrudfchar20;

    //外表数据
    private String usr_default_org;
    private String uog_desc;
    private String mrc_desc;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMrc_desc() {
        return mrc_desc;
    }

    public void setMrc_desc(String mrc_desc) {
        this.mrc_desc = mrc_desc;
    }

    @Transient
    private String appScreens;

    @Transient
    private String[] usrScreens;

    public String getUog_desc() {
        return uog_desc;
    }

    public void setUog_desc(String uog_desc) {
        this.uog_desc = uog_desc;
    }
    public String getUsr_default_org() {
        return usr_default_org;
    }

    public void setUsr_default_org(String usr_default_org) {
        this.usr_default_org = usr_default_org;
    }

    public String[] getUsrScreens() {
        return usrScreens;
    }

    public void setUsrScreens(String[] usrScreens) {
        this.usrScreens = usrScreens;
    }

    public String getAppScreens() {
        return appScreens;
    }

    public void setAppScreens(String appScreens) {
        this.appScreens = appScreens;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getUsrcode() {
        return usrcode;
    }

    public void setUsrcode(String usrcode) {
        this.usrcode = usrcode;
    }

    public String getUsrdesc() {
        return usrdesc;
    }

    public void setUsrdesc(String usrdesc) {
        this.usrdesc = usrdesc;
    }

    public String getUsrremark() {
        return usrremark;
    }

    public void setUsrremark(String usrremark) {
        this.usrremark = usrremark;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getUsrpassword() {
        return usrpassword;
    }

    public void setUsrpassword(String usrpassword) {
        this.usrpassword = usrpassword;
    }

    public Date getUsrvalidate() {
        return usrvalidate;
    }

    public void setUsrvalidate(Date usrvalidate) {
        this.usrvalidate = usrvalidate;
    }

    public String getUsrisadmin() {
        return usrisadmin;
    }

    public void setUsrisadmin(String usrisadmin) {
        this.usrisadmin = usrisadmin;
    }

    public String getUsrisapp() {
        return usrisapp;
    }

    public void setUsrisapp(String usrisapp) {
        this.usrisapp = usrisapp;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getUsrappvalidate() {
        return usrappvalidate;
    }

    public void setUsrappvalidate(Date usrappvalidate) {
        this.usrappvalidate = usrappvalidate;
    }

    public String getUsrstatus() {
        return usrstatus;
    }

    public void setUsrstatus(String usrstatus) {
        this.usrstatus = usrstatus;
    }

    public String getUsrnotused() {
        return usrnotused;
    }

    public void setUsrnotused(String usrnotused) {
        this.usrnotused = usrnotused;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getUsrorg() {
        return usrorg;
    }

    public void setUsrorg(String usrorg) {
        this.usrorg = usrorg;
    }

    public String getUsrversion() {
        return usrversion;
    }

    public void setUsrversion(String usrversion) {
        this.usrversion = usrversion;
    }

    public String getUsrtenant() {
        return usrtenant;
    }

    public void setUsrtenant(String usrtenant) {
        this.usrtenant = usrtenant;
    }

    public String getUsrudfchar01() {
        return usrudfchar01;
    }

    public void setUsrudfchar01(String usrudfchar01) {
        this.usrudfchar01 = usrudfchar01;
    }

    public String getUsrudfchar02() {
        return usrudfchar02;
    }

    public void setUsrudfchar02(String usrudfchar02) {
        this.usrudfchar02 = usrudfchar02;
    }

    public String getUsrudfchar03() {
        return usrudfchar03;
    }

    public void setUsrudfchar03(String usrudfchar03) {
        this.usrudfchar03 = usrudfchar03;
    }

    public String getUsrudfchar04() {
        return usrudfchar04;
    }

    public void setUsrudfchar04(String usrudfchar04) {
        this.usrudfchar04 = usrudfchar04;
    }

    public String getUsrudfchar05() {
        return usrudfchar05;
    }

    public void setUsrudfchar05(String usrudfchar05) {
        this.usrudfchar05 = usrudfchar05;
    }

    public String getUsrudfchar06() {
        return usrudfchar06;
    }

    public void setUsrudfchar06(String usrudfchar06) {
        this.usrudfchar06 = usrudfchar06;
    }

    public String getUsrudfchar07() {
        return usrudfchar07;
    }

    public void setUsrudfchar07(String usrudfchar07) {
        this.usrudfchar07 = usrudfchar07;
    }

    public String getUsrudfchar08() {
        return usrudfchar08;
    }

    public void setUsrudfchar08(String usrudfchar08) {
        this.usrudfchar08 = usrudfchar08;
    }

    public String getUsrudfchar09() {
        return usrudfchar09;
    }

    public void setUsrudfchar09(String usrudfchar09) {
        this.usrudfchar09 = usrudfchar09;
    }

    public String getUsrudfchar10() {
        return usrudfchar10;
    }

    public void setUsrudfchar10(String usrudfchar10) {
        this.usrudfchar10 = usrudfchar10;
    }

    public String getUsrudfchar11() {
        return usrudfchar11;
    }

    public void setUsrudfchar11(String usrudfchar11) {
        this.usrudfchar11 = usrudfchar11;
    }

    public String getUsrudfchar12() {
        return usrudfchar12;
    }

    public void setUsrudfchar12(String usrudfchar12) {
        this.usrudfchar12 = usrudfchar12;
    }

    public String getUsrudfchar13() {
        return usrudfchar13;
    }

    public void setUsrudfchar13(String usrudfchar13) {
        this.usrudfchar13 = usrudfchar13;
    }

    public String getUsrudfchar14() {
        return usrudfchar14;
    }

    public void setUsrudfchar14(String usrudfchar14) {
        this.usrudfchar14 = usrudfchar14;
    }

    public String getUsrudfchar15() {
        return usrudfchar15;
    }

    public void setUsrudfchar15(String usrudfchar15) {
        this.usrudfchar15 = usrudfchar15;
    }

    public String getUsrudfchar16() {
        return usrudfchar16;
    }

    public void setUsrudfchar16(String usrudfchar16) {
        this.usrudfchar16 = usrudfchar16;
    }

    public String getUsrudfchar17() {
        return usrudfchar17;
    }

    public void setUsrudfchar17(String usrudfchar17) {
        this.usrudfchar17 = usrudfchar17;
    }

    public String getUsrudfchar18() {
        return usrudfchar18;
    }

    public void setUsrudfchar18(String usrudfchar18) {
        this.usrudfchar18 = usrudfchar18;
    }

    public String getUsrudfchar19() {
        return usrudfchar19;
    }

    public void setUsrudfchar19(String usrudfchar19) {
        this.usrudfchar19 = usrudfchar19;
    }

    public String getUsrudfchar20() {
        return usrudfchar20;
    }

    public void setUsrudfchar20(String usrudfchar20) {
        this.usrudfchar20 = usrudfchar20;
    }


}
