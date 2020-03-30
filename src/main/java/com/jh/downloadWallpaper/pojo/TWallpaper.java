package com.jh.downloadWallpaper.pojo;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TWallpaper {
    private Integer id;

    private String url;

    private Date createdate;
}