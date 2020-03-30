/*
  @author:jh
  描述：
  User: jeffx
  Date: 2020/3/30
  Time: 23:59
  
*/

package com.jh.downloadWallpaper;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetHtml {
    private final static String url = "https://wallhaven.cc/toplist?page=23";


    public static void main(String[] args) {

        try {
            Connection con = Jsoup
                    .connect(url);// 获取连接
            con.header("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
            Connection.Response rs = con.execute();// 获取响应
            Document doc = Jsoup.parse(rs.body());// 转换为Dom树
            Elements preview = doc.body().getElementsByClass("preview");
            for (Element element : preview) {
                String href = element.attr("href");
                Connection connect = Jsoup.connect(href);
                Document childDoc = Jsoup.parse(connect.execute().body());
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
                OutputStream os = new FileOutputStream("f:/"+FileUtil.getName(src));
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
    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
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
