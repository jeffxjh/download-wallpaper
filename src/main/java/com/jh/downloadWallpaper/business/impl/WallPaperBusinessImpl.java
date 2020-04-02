package com.jh.downloadWallpaper.business.impl;

import cn.hutool.core.io.FileUtil;
import com.jh.downloadWallpaper.asyn.AsynService;
import com.jh.downloadWallpaper.business.WallPaperBusiness;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description
 *
 * @Author: jeffx
 * @Date: 2020/3/31:15:22
 */
@Slf4j
@Service
public class WallPaperBusinessImpl implements WallPaperBusiness {
    private final String localPath = "C:/Users/jeffx/Pictures/Saved Pictures/";
    @Autowired
    private AsynService service;
    @Override
    public void getWallPaper() {
        try {
            //ExecutorService threadPool = Executors.newFixedThreadPool(20);
            //threadPool.execute(new Runnable() {
            //    @Override
            //    public void run() {
            //
            //    }
            //});
            for (int i = 1; i <= 20; i++) {
                String webUrl = "https://wallhaven.cc/toplist?page="+i;
                Document doc = Jsoup.connect(webUrl)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                        .timeout(60000)//超时时间
                        .get();
                Elements preview = doc.body().getElementsByClass("preview");
                for (Element element : preview) {
                    String href = element.attr("href");
                    Document childDoc = Jsoup.connect(href)
                            .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                            .timeout(60000)//超时时间
                            .get();
                    Element wallpaper = childDoc.getElementById("wallpaper");
                    String src = wallpaper.attr("src");
                    log.info("图片地址为："+src);
                    File file=new File(localPath + FileUtil.getName(src));
                    if (file.exists()) {
                        log.info("已存在文件"+FileUtil.getName(src)+"跳过该文件");
                        continue;
                    }
                    service.downLoad(src);
                }
                log.error("第"+i+"页下载完成");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
