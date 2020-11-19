package io.kimmking.spring01;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuavaDemo {
    
    static EventBus bus = new EventBus();
    static {
        bus.register(new GuavaDemo());
    }
    
    
    @SneakyThrows
    public static void main(String[] args) throws IOException {
        List<String> lists = testStrings();
        List<Integer> list = testIntegers();
        testMultimap(list);
        testBiMap(lists);
        testEventBus();
    }

    private static void testEventBus() {
        // EventBus
        // SPI+service loader
        // Callback/Listener
        //
        Student student2 = new Student(2, "KK02");
        System.out.println("I want " + student2 + " run now.");
        bus.post(new AEvent(student2));
    }

    private static void testBiMap(List<String> lists) {
        //BiMap强制其value的唯一性，如果发现违规则会抛出 IllegalArgumentException
        BiMap<String, Integer> words = HashBiMap.create();
        words.put("First", 1);
        words.put("Second", 2);
        words.put("Third", 3);
        //java.lang.IllegalArgumentException: value already present: 3
//        words.put("Third1",3);

        System.out.println(words.get("Second").intValue());
        //inverse方法返回一个反转后的BiMap，即key/value互相切换的映射
        System.out.println(words.inverse().get(3));

        Map<String,String> map1 = Maps.toMap(lists.listIterator(), a -> a+"-value");
        print(map1);
    }

    private static void testMultimap(List<Integer> list) {
        //Map map = list.stream().collect(Collectors.toMap(a->a,a->(a+1)));
        //https://www.cnblogs.com/qdhxhz/p/9411511.html
        //Multimap的特点其实就是可以包含有几个重复Key的value，你可以put进入多个不同value但是相同的key，但是又不是让后面覆盖前面的内容。
        // 当你需要构造像Map<K, List<V>>或者Map<K, Set<V>>这样比较复杂的集合类型的数据结构，来做相应的业务逻辑处理。那Multimap在合适不过
        Multimap<Integer,Integer> bMultimap = ArrayListMultimap.create();
        list.forEach(
                a -> bMultimap.put(a,a+1)
        );
        print(bMultimap);
        print(bMultimap.get(3));
    }

    private static List<Integer> testIntegers() {
        // 更强的集合操作
        // 简化 创建

        List<Integer> list = Lists.newArrayList(4,2,3,5,1,2,2,7,6);

        List<List<Integer>> list1 = Lists.partition(list,3);
        print(list1);
        return list;
    }

    private static List<String> testStrings() {
        // 字符串处理
        //
        List<String> lists = Lists.newArrayList("a","b","g","8","9");
        String result = Joiner.on(",").join(lists);
        System.out.println(result);

        String test = "34344,34,34,哈哈";
        lists = Splitter.on(",").splitToList(test);
        System.out.println(lists);
        return lists;
    }

    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }
    
    
    @Data
    @AllArgsConstructor
    public static class AEvent{
        private Student student;
    }
    
    @Subscribe
    public void handle(AEvent ae){
        System.out.println(ae.student + " is running.");
    }
    
    
    
}
