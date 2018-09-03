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
    // FIXME: log4j是一套日志规范接口，有各种实现，slf4j就是一种，这个用的就是slf4j
    private static final Logger LOGGER = LoggerFactory.getLogger(AppInfoService.class);



    //TODO 加入spring相关依赖，就能识别的了AppInfoDao类型
    //FIXME 嗯，spring boot初始化会扫包，需要指定dao包的位置。有个实现可能不用指定，约定优于配置
    /*** <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-context</artifactId>
     </dependency>
     *
     */

    @Autowired
    private AppInfoDao appInfoDao;


    // TODO: 18/8/29 此处传入的是appInfo对象，打印出来的是什么格式的呢？？？
    // FIXME: 按照toString()方法打印
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
    // FIXME: 初始状态是0啊，下载完成、解析完成都有啊，是通过update()方法更新的
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
