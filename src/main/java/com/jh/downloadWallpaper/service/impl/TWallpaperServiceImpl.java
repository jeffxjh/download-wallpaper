package com.jh.downloadWallpaper.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jh.downloadWallpaper.pojo.TWallpaper;
import com.jh.downloadWallpaper.mapper.TWallpaperMapper;
import com.jh.downloadWallpaper.service.TWallpaperService;
@Service
public class TWallpaperServiceImpl implements TWallpaperService{

    @Resource
    private TWallpaperMapper tWallpaperMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tWallpaperMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TWallpaper record) {
        return tWallpaperMapper.insert(record);
    }

    @Override
    public int insertSelective(TWallpaper record) {
        return tWallpaperMapper.insertSelective(record);
    }

    @Override
    public TWallpaper selectByPrimaryKey(Integer id) {
        return tWallpaperMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TWallpaper record) {
        return tWallpaperMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TWallpaper record) {
        return tWallpaperMapper.updateByPrimaryKey(record);
    }

}
