/*
  @author:jh
  描述：
  User: jeffx
  Date: 2020/3/31
  Time: 20:22
  
*/

package com.jh.downloadWallpaper.asyn.impl;

import cn.hutool.core.io.FileUtil;
import com.jh.downloadWallpaper.asyn.AsynService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
@Slf4j
@Async
@Service
public class AsynServiceImpl implements AsynService {
    private final String localPath = "C:/Users/jeffx/Pictures/Saved Pictures/";

    @Override
    public void downLoad(String src) {
        try {
            // 构造URL
            URL url = new URL(src);
            // 打开连接
            URLConnection urlConnection = url.openConnection();
            urlConnection.setReadTimeout(300000);
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0"); //防止报403错误。
            // 输入流
            InputStream is = urlConnection.getInputStream();
            // 1K的数据缓冲
           // byte[] bs = new byte[1024];
            // 读取到的数据长度
           // int len;
            // 输出的文件流
            //OutputStream os = new FileOutputStream(localPath + FileUtil.getName(src));
            // 开始读取
            //while ((len = is.read(bs)) != -1) {
            //    os.write(bs, 0, len);
            //}
            FileUtil.writeFromStream(is, localPath + FileUtil.getName(src));
            // 完毕，关闭所有链接
            //os.close();
            //is.close();
            log.info("异步下载完成，文件名："+FileUtil.getName(src));
        } catch (Exception e) {

        }

    }

}
