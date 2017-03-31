package cn.base.test;

import cn.base.net.NetManager;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Created by fengwei on 17/3/31.
 */
public class NetTest {

    @Test
    public void test_download() throws IOException {
//        NetManager.download("http://localhost:8089/resource/hadoop-2.7.1.tar.gz");
        NetManager.download("http://localhost:8089/resource/features_20170103.txt");
    }

    @Test
    public void test_net() throws IOException {
        final URL url = new URL("http://localhost:8089/resource/hadoop-2.7.1.tar.gz");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NetManager.getFileSize(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Test
    public void test_download_multi() throws IOException {
        URL url = new URL("http://localhost:8089/resource/hadoop-2.7.1.tar.gz");
        String filePath = "/Users/fengwei/Documents/data/test/bak.tar.gz";
        NetManager.download_multi(url, filePath);
    }

}
