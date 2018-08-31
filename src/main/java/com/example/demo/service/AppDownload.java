package com.example.demo.service;

import com.example.demo.entities.AppInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class AppDownload {

    private static final String DATA_DIR_PROPERTY_NAME="data.home";
    private static final Logger LOGGER = LoggerFactory.getLogger(AppDownload.class);


    @Autowired
    private Environment env;

    /***
     *启动schedule线程，定时从数据库中拉取appURL进行下载
     */

    // TODO: 18/8/31 这个每次都是下载一个app，怎么不用挑选app来下载呢？有的可能是已经下载了的，有的是正在下载的，
    // 没有下载的列表里面，选取排名第一的嘛？










    /**
    * 根据AppInfo信息，下载文件
     * */
    private String downloadFromStore(AppInfo appInfo){
        String filePath = generateFilePath(appInfo);
        try {
            directDownload(appInfo.getDownloadUrl(),filePath);
            return filePath;
        }catch (Exception e){
            LOGGER.error("APP DOWNLOAD EXCEPTION,{}",appInfo);
            e.printStackTrace();
            //// TODO: 18/8/30 对于这种返回null的，当别的方法在调用的时候，是不是要处理一下为null的情况？？？ 
            return null;
        }

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
    private void directDownload(String url,String filePath) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //// TODO: 18/8/30 为啥不是用的时候，直接new了，而是先定义，到用的时候再new？？
        InputStream in = null;
        OutputStream out = null;
        File file = new File(filePath);


        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            in = httpEntity.getContent();
            long length = httpEntity.getContentLength();
            if(length<=0){
                LOGGER.error("下载文件不存在");
            }
            out = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int readlength = 0;
            while ((readlength = in.read(buffer))!= -1){
                // TODO: 18/8/30 读的时候， 为啥要读一个byte数组，read完了的时候，又是返回一个int类型的值呢？
                // TODO: 18/8/30 相当于是把byte类型的给转换成为int类型的嘛？？？
                out.write(buffer,0,readlength);
            }
            out.flush();
            LOGGER.info("DOWNLOAD succ,url={},filepath={}",url,filePath);
            
        } catch (IOException e) {
            FileUtils.forceDelete(file);
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
    
    
    



}
