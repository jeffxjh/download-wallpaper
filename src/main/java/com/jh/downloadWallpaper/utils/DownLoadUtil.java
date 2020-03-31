package com.jh.downloadWallpaper.utils;

import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * description
 *
 * @Author: jeffx
 * @Date: 2020/3/31:11:20
 */
@Component
public class DownLoadUtil {
    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection urlConnection  = url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0"); //防止报403错误。
        // 输入流
        InputStream is = urlConnection .getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
