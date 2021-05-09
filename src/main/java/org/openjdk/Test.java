package org.openjdk;

import org.openjdk.jol.SimpleClassLayout;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) {
        byte[] encrypt = encrypt("sp0 1".getBytes(StandardCharsets.UTF_8), 1000, 1000);
        System.out.println(Arrays.toString(encrypt));
        System.out.println(new String(decrypt(encrypt)));

        System.out.println(VM.current().sizeOf(encrypt));
        System.out.println(VM.current().sizeOf("sp0 1"));
//        System.out.println( ClassLayout.parseInstance("encrypt").toPrintable() );
//        System.out.println( ClassLayout.parseInstance(encrypt).toPrintable() );
//        System.out.println( ClassLayout.parseInstance(Arrays.toString(encrypt)).toPrintable() );
        Stats stats = new Stats();

        System.out.println(ClassLayout.parseInstance(stats).toPrintable());
//        System.out.println( SimpleClassLayout.parseInstance(stats).toPrintSimple() );


        for (int i = 0; i < 8; i++) {
            stats.failedCount2.put("" + i, new AtomicInteger(i));
            System.out.println(GraphLayout.parseInstance(stats.failedCount2).totalSize());
        }
        System.out.println(SimpleClassLayout.parseInstance(stats.failedCount2).toPrintSimple());
//        System.out.println(GraphLayout.parseInstance(stats).totalSize());
//        System.out.println( ClassLayout.parseClass(Stats.class).toPrintable() );
//
//        System.out.println( GraphLayout.parseInstance(stats).totalSize() );
    }

    public static byte[] encrypt(byte[] bytes, int cid, int sn) {
        byte[] result = new byte[bytes.length + 6];
        int x = bytes.length ^ (cid + sn);
        result[4] = (byte) (x & 255); //异或因子低8位
        result[5] = (byte) ((x >> 8) & 255); //异或因子高8位,目前没用
//        int r = x & 255;
        int r = result[4]; //初始异或因子
        //与 r异或, 位移6
        for (int i = 0; i < bytes.length; i++) {
            r = bytes[i] ^ r;
            result[i + 6] = (byte) r;
        }
        return result;
    }

    public static byte[] decrypt(byte[] bytes) {

        byte[] result = new byte[bytes.length];

        byte r = bytes[4];
        for (int i = 6; i < bytes.length; i++) {
            result[i - 4] = (byte) (bytes[i] ^ r);
            r = bytes[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

}