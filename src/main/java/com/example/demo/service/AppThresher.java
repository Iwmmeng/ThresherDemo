package com.example.demo.service;

import com.example.demo.utils.ApkUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wangmeng
 * @date 18/8/30
 */
@Service
public class AppThresher {
     private static final Logger LOGGER = LoggerFactory.getLogger(AppThresher.class);
    private Map<String,String> pushKeyWordMap = new HashMap<>();
    public List<String> thresh(String apkFilePath) {
        List<String> sdkList = new ArrayList<>();
        String content = ApkUtils.getManifestXMLFromAPK(apkFilePath);

        if (StringUtils.isEmpty(content)) {
            LOGGER.error("get manifest XML from apk error,apkFilePath : {}", apkFilePath);
            return null;
        }
        for (Map.Entry<String, String> entry : pushKeyWordMap.entrySet()
                ) {
            if (content.contains(entry.getKey())) {
                sdkList.add(entry.getValue());
            }
        }
        return sdkList;
    }

       /**
        * 初始化push sdk 与关键词的映射
        */

       private void initPushKeyWordMap(){
           Resource resource = new ClassPathResource("/push.key");
           try {
               List<String> keyWordStrList = IOUtils.readLines(new FileReader(resource.getFile()));
               for (String keyWordStr:keyWordStrList
                    ) {
                   String[] item = keyWordStr.split("\\$");
                   if(item.length!=2) {continue;}
                   pushKeyWordMap.put(item[1],item[0]);
               }

           } catch (IOException e) {
               LOGGER.error("read packagelist failed",e);
           }
       }







    }


