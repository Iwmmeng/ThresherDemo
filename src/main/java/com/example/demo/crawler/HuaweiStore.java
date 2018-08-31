package com.example.demo.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author wangmeng
 * @date 18/8/31
 */

@Component
public class HuaweiStore extends BaseWebCrawler{
    private static final Logger LOGGER = LoggerFactory.getLogger(HuaweiStore.class);

    @Override
    protected String getStoreName() {
        return "huawei";
    }

    /**
     * web 界面解析*/
    @Override
    public void process() {
        // TODO: 18/8/31 方法的调用，都不用对象吗？直接用？？？？这个怎么理解会比较好？？
        List<String> urlList = getAllListUrl();
        for(String url:urlList){
            LOGGER.info("GET app list url :{}",url);

//// TODO: 18/8/31 解析一个html页面，获取其中的字段，不会写，没有太看明白



        }





    }
}
