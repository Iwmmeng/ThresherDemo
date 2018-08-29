package com.example.demo.entities;

import java.util.Date;

public class AppInfo {

    private Integer id;
    private String appName;
    private String packageName;
    private String downloadUrl;
    private String version;
    private String source;
    private String pushSdk;
    private String fileLocation;
    /**
     * 0->创建初始状态
     * 1->apk 下载中
     * 2->apk 下载完成
     * 3->apk 解析中
     * 4->apk 解析完成
     * 5->apk 失败
     * */

    // TODO: 18/8/28 这个状态是所有失败状态的一个统称吗？不需要具体区分是哪一个步骤的失败吗？？？？
    // TODO: 18/8/28 这些状态都有对应的数字，状态数字的映射是在哪里呢？

    private Integer status;
    private Date createTime;


    // TODO: 18/8/28   IDE默认生成的set都是void形式的，但是ET的set都是AppInfo类型的，区别是啥？
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPushSdk() {
        return pushSdk;
    }

    public void setPushSdk(String pushSdk) {
        this.pushSdk = pushSdk;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    // TODO: 18/8/28 需要加上this吗？？

    @Override
    public String toString(){
        return "id: "+ id +"appName:" +appName +"packageName"+packageName+"downloadUrl:"+downloadUrl+"source:"+source;
    }




}

