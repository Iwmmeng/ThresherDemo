package com.example.demo.service;

import com.example.demo.dao.AppInfoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangmeng
 * @date 18/8/28
 */
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









}
