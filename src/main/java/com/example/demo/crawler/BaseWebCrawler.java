package com.example.demo.crawler;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author wangmeng
 * @date 18/8/31
 */


// TODO: 18/8/31  component 这个注解是干啥用的？？？？
@Component
public abstract class BaseWebCrawler {
    private static final String STORE_APP_LIST_URL_PROPERTIES="store.app.list.url";
    protected abstract String getStoreName();
    public abstract void process();


    @Autowired
    private Environment env;

    protected List<String> getAllListUrl(){
        String listurl = env.getProperty(getStoreName()+"."+STORE_APP_LIST_URL_PROPERTIES);
        int flagIndex = listurl.indexOf("{");
        if(flagIndex>0){
            String urlPrefix = listurl.substring(0,flagIndex);
            String pageStr = listurl.substring(flagIndex+1,listurl.length()-1);
            String[] items = pageStr.split("-");

            int start = NumberUtils.toInt(items[0]);
            int end = NumberUtils.toInt(items[1]);
            List<String> urlList = new ArrayList<>();
            for(int i = start;i<=end;i++){
                urlList.add(urlPrefix+i);
            }
            return urlList;
        }else{
            return Collections.singletonList(listurl);
        }
    }





}
