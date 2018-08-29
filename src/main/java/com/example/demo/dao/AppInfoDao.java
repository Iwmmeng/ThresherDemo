package com.example.demo.dao;

import com.example.demo.entities.AppInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author wangmeng
 * @date 18/8/28
 */
public interface AppInfoDao {
    
    //// TODO: 18/8/28 insert 方法已经在mybatis.xml文件中配置，但是并没有搜索到，why？还缺少什么配置？？？ 
    int insert(AppInfo appInfo);
    int update(AppInfo appInfo);
    int deleteById(@Param("id") int id);
    AppInfo findByPackageNameAndVersion(@Param("packageName") String packageName, @Param("version") String version);
    AppInfo getOneAppByStatus(@Param("status")int status);





    /**
     *  @Insert("insert into store_app_info (app_name,package_name,download_url,version,source,push_sdk,file_location,status) " +
    "values (#{appName},#{packageName},#{downloadUrl},#{version},#{source},#{pushSdk},#{fileLocation},#{status})")
    public int insert(AppInfo appInfo);

     @Select("select * from store_app_info where package_name=#{packageName} and version=#{version}")
     public AppInfo findByPackageNameAndVersion(@Param("packageName") String packageName, @Param("version") String version);

     @Insert("update store_app_info set app_name=#{appName},package_name=#{packageName},download_url=#{downloadUrl}," +
     "version=#{version},source=#{source},push_sdk=#{pushSdk},file_location=#{fileLocation},status=#{status}" +
     " where id=${id}")
     public int update(AppInfo appInfo);

     @Select("select * from store_app_info where status=#{status} limit 1")
     public AppInfo getOneByStatus(@Param("status") int status);

     @Select("delete from store_app_info where id=#{id}")
     public void deleteById(@Param("id") int id);
     *
     *
     *
     *
     * */


}
