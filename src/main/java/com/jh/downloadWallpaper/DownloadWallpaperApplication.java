package com.jh.downloadWallpaper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@MapperScan(basePackages = "com.jh.downloadWallpaper.mapper")
@EnableScheduling
@SpringBootApplication
public class DownloadWallpaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DownloadWallpaperApplication.class, args);
    }

}
