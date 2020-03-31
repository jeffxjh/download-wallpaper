package com.jh.downloadWallpaper.business.impl;

import cn.hutool.core.io.FileUtil;
import com.jh.downloadWallpaper.business.WallPaperBusiness;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * description
 *
 * @Author: jeffx
 * @Date: 2020/3/31:15:22
 */
@Service
public class WallPaperBusinessImpl implements WallPaperBusiness {
    private String url = "https://wallhaven.cc/toplist?page=4";
    @Override
    public void getWallPaper() {
        try {
            Document doc = Jsoup.connect(url)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                   // .header("Connection", "alive")//如果是这种方式，这里务必带上
                    .timeout(60000)//超时时间
                    .get();
            Elements preview = doc.body().getElementsByClass("preview");
            for (Element element : preview) {
                String href = element.attr("href");
                Document childDoc = Jsoup.connect(href)
                        .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:49.0) Gecko/20100101 Firefox/49.0")
                     //   .header("Connection", "close")//如果是这种方式，这里务必带上
                        .timeout(60000)//超时时间
                        .get();
                Element wallpaper = childDoc.getElementById("wallpaper");
                String src = wallpaper.attr("src");
                System.out.println(src);
                // 构造URL
                URL url = new URL(src);
                // 打开连接
                URLConnection urlConnection = url.openConnection();
                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0"); //防止报403错误。
                // 输入流
                InputStream is = urlConnection.getInputStream();
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len;
                // 输出的文件流
                OutputStream os = new FileOutputStream("F:/pic/Saved Pictures" + FileUtil.getName(src));
                // 开始读取
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
                // 完毕，关闭所有链接
                os.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
