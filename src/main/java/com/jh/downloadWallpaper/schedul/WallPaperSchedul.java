package com.jh.downloadWallpaper.schedul;

import com.jh.downloadWallpaper.business.WallPaperBusiness;
import com.jh.downloadWallpaper.utils.DownLoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @Author: jeffx
 * @Date: 2020/3/31:11:21
 */
@Slf4j
@Component
public class WallPaperSchedul {

    @Autowired
    private WallPaperBusiness business;
    @Autowired
    private DownLoadUtil downLoadUtil;

    @Scheduled(cron = "0/1 * * * * *")
    private void getWallpaper() {
     //   business.getWallPaper();

    }
}
