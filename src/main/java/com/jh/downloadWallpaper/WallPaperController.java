package com.jh.downloadWallpaper;

import com.jh.downloadWallpaper.business.WallPaperBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @Author: jeffx
 * @Date: 2020/3/31:15:20
 */
@RestController
public class WallPaperController {
    @Autowired
    private WallPaperBusiness business;
    @GetMapping("/get")
    public String getWallPaper() {
        business.getWallPaper();
        return "success";
    }
}
