package com.jh.downloadWallpaper.service;

import com.jh.downloadWallpaper.pojo.TWallpaper;
public interface TWallpaperService{


    int deleteByPrimaryKey(Integer id);

    int insert(TWallpaper record);

    int insertSelective(TWallpaper record);

    TWallpaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TWallpaper record);

    int updateByPrimaryKey(TWallpaper record);

}
