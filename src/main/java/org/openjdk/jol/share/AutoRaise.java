package org.openjdk.jol.share;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoRaise {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(8);
        Map<String, Object> map = new HashMap<>(8);
        System.out.println(ClassLayout.parseInstance(list).toPrintable());
        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        System.out.println(GraphLayout.parseInstance(list).toFootprint());
        System.out.println(GraphLayout.parseInstance(map).toFootprint());
        for (int i = 0; i < 100; i++) {
            list.add("list" + i);
            map.put("map" + i, i);
        }
        System.out.println(ClassLayout.parseInstance(list).toPrintable());
        System.out.println(GraphLayout.parseInstance(list).toFootprint());
        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        System.out.println(GraphLayout.parseInstance(map).toFootprint());


        for (int i = 0; i < 100000; i++) {
            list.add("list" + i);
            map.put("map" + i, i);
        }
//        System.out.println(ClassLayout.parseInstance(list).toPrintable());
        System.out.println(GraphLayout.parseInstance(list).toFootprint());
//        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        System.out.println(GraphLayout.parseInstance(map).toFootprint());
    }
}
