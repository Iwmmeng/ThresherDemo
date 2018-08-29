package com.example.demo.service;

import com.example.demo.entities.AppInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;

public class AppDownload {

    private static final String DATA_DIR_PROPERTY_NAME="data.home";


    @Autowired
    private Environment env;


    /**
    * 根据AppInfo信息，下载文件
     * */
    private String downloadFromStore(AppInfo appInfo){

    }


    /**
     * 根据APPInfo生成文件下载路径*/

    // TODO: 2018/8/30 这个路径到底是哪里，是怎么创建出来这个路径的？？？？
    private String generateFilePath(AppInfo appInfo){
        return StringUtils.joinWith("/",env.getProperty(DATA_DIR_PROPERTY_NAME),"apps",appInfo.getSource(),
                appInfo.getPackageName(),appInfo.getPackageName()+"-"+appInfo.getVersion()+".apk");

    }

    /**
     * 根据url直接下载文件*/
    private void directDownload(String url,String filePath){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        InputStream in = null;


        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            in = httpEntity.getContent();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
