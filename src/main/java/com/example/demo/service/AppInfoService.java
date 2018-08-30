package com.example.demo.service;

import com.example.demo.dao.AppInfoDao;
import com.example.demo.entities.AppInfo;
import com.example.demo.entities.AppStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangmeng
 * @date 18/8/28
 */
@Service
public class AppInfoService {

    // TODO: 18/8/28 这个日志跟log4j的关系？？怎么选择会比较好？？
    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoService.class);



    //TODO 加入spring相关依赖，就能识别的了AppInfoDao类型
    /*** <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-context</artifactId>
     </dependency>
     *
     */

    @Autowired
    private AppInfoDao appInfoDao;


    // TODO: 18/8/29 此处传入的是appInfo对象，打印出来的是什么格式的呢？？？ 
    public void  addAppInfo(AppInfo appInfo){
        if(appInfoDao.findByPackageNameAndVersion(appInfo.getPackageName(),appInfo.getVersion())!= null){
            LOGGER.info("APP exists {}",appInfo);
        }else {
            appInfoDao.insert(appInfo);
            LOGGER.info("INSER APP {}",appInfo);
        }
    }

    // TODO: 18/8/29 看到了app有创建-》下载中状态的转变，下载完成-》解析中的状态。那appinfo的初始状态为默认值？这个应该怎么看？ 
    // TODO: 18/8/29 下载中-》下载完成这个状态也没有看到过度啊？？？ 
    public synchronized AppInfo getOneAppAndDownloading(){
        AppInfo appInfo = appInfoDao.getOneAppByStatus(AppStatus.CREATE.ordinal());
        if(appInfo ==null){
            return null;
        }
        appInfo.setStatus(AppStatus.APP_DOWNLOADING.ordinal());
        appInfoDao.update(appInfo);
        return appInfo;
    }

    public synchronized AppInfo getOneAppAndThreshing(){
        AppInfo appInfo = appInfoDao.getOneAppByStatus(AppStatus.APP_DOWNLOADED.ordinal());
        if(appInfo ==null){return null;}
        appInfo.setStatus(AppStatus.APP_THRESHING.ordinal());
        appInfoDao.update(appInfo);
        return appInfo;

    }

    public void update(AppInfo appInfo){
        appInfoDao.update(appInfo);
    }
    















}
