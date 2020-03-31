package com.jh.downloadWallpaper.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TWallpaper {
    private Integer id;

    private String url;

    private String src;

    private Date createdate;
}