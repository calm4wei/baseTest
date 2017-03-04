package cn.base.test;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created on 2017/3/3
 *
 * @author feng.wei
 */
public class IOTest {

    @Test
    public void testChannel() throws IOException {
        RandomAccessFile aaf = new RandomAccessFile("D:\\data\\wc.txt", "rw");
        FileChannel inChannel = aaf.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(128);
        int readBytes = inChannel.read(buffer);
        while (readBytes != -1) {
            System.out.println("Read " + readBytes);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
//            buffer.clear();
            buffer.compact();
            readBytes = inChannel.read(buffer);
        }
        inChannel.close();
        aaf.close();
    }

    /**
     * 通道之间的数据传输
     *
     * @throws IOException
     */
    @Test
    public void testTransfer() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("D:\\data\\wc.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("D:\\data\\wc.to.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
        //fromChannel.transferTo(position, count, toChannel);
        fromChannel.close();
        fromFile.close();
        toFile.close();
        toChannel.close();
    }
}
