package cn.base.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 文件下载
 * Created by fengwei on 17/3/31.
 */
public class NetManager {

    /**
     * 单线程下载
     *
     * @param url
     * @throws IOException
     */
    public static void download(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        InputStream in = conn.getInputStream();
        System.out.println(conn.getContentType());
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        String filePath = "/Users/fengwei/Documents/data/test/" + fileName;

        // FileOutputStream fos = new FileOutputStream(filePath);
        RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
        byte[] buff = new byte[1024];
        int byteRead;
        int offset = 0;
        while ((byteRead = in.read(buff, 0, buff.length)) != -1) {
            raf.seek(offset);
            raf.write(buff, 0, byteRead);
            offset = offset + byteRead;
        }
        raf.close();
        in.close();

    }

    /**
     * 多线程分段下载，目前只支持文本文件
     *
     * @param url
     * @param filePath
     * @throws IOException
     */
    public static void download_multi(URL url, String filePath) throws IOException {
        // 文件总长度
        long fileSize = getFileSize(url);
        // 段的大小100M
        long segSize = 1024 * 1024 * 10;
        // 线程总数
        int threadNum = 0;
        if (fileSize > segSize) {
            threadNum = (int) (fileSize / segSize);

            // 如果文件总大小不是段大小的整数倍，则线程数加1　
            if (fileSize % segSize != 0) {
                threadNum += 1;
            }
        }

        System.out.println("threadnum=" + threadNum);

        // 分段下载
        for (int i = 0; i < threadNum; i++) {
            long start = i * segSize;
            long end;
            if (i < threadNum - 1) {
                end = start + segSize - 1;
            } else {
                end = fileSize - 1;
            }
            Thread thread = new DownloadThread(start, end, url, filePath);
            thread.setName("thread-" + i);
            thread.start();
        }

    }

    public static long getFileSize(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        long fileSize = conn.getContentLengthLong();
        System.out.println("fileSize=" + fileSize);
        return fileSize;
    }

    static class DownloadThread extends Thread {

        private long start;
        private long end;
        private URL url;
        private byte[] buff = new byte[1024 * 8];
        private String filePath;

        public DownloadThread(long start, long end, URL url, String filePath) {
            this.start = start;
            this.end = end;
            this.url = url;
            this.filePath = filePath;
        }

        @Override
        public void run() {

            InputStream in = null;
            RandomAccessFile raf = null;
            try {
                System.out.println(Thread.currentThread() + " is running...");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);
                String property = "bytes=" + start + "-" + end;
                conn.setRequestProperty("Range", property);
                conn.setRequestMethod("GET");
                conn.connect();
                in = conn.getInputStream();
                int length;

                raf = new RandomAccessFile(filePath, "rw");
                raf.seek(start);
                while ((length = in.read(buff)) != -1 && start < end) {
                    raf.write(buff, 0, length);
                    // start += length;
                }
                System.out.println(Thread.currentThread() + " is over.");
            } catch (Exception e) {
                System.out.println("ERROR message: " + e.getMessage());
            } finally {
                try {
                    if (null != raf) {
                        raf.close();
                    }
                    if (null != in) {
                        in.close();
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
