package io.kimmking.spring01;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionDemo {
    public static void main(String[] args) throws IOException {
    
        List<Integer> list = Arrays.asList(4,2,3,5,1,2,2,7,6);   // Arrays还可以包装stream
        print(list);
        Collections.sort(list);
        print(list);
        Collections.reverse(list);
        print(list);
        Collections.shuffle(list);
        print(list);
        //统计频率
        System.out.println(Collections.frequency(list, 2));
        System.out.println(Collections.max(list));
        //使用指定元素替换指定列表中的所有元素
        Collections.fill(list,2);
        //使用另一个值替换列表中出现的所有某一指定值。更确切地讲，使用 newVal替换 list 中满足 (oldVal==null ? e==null : oldVal.equals(e)) 的每个 e 元素。（此方法对列表的大小没有任何影响。）
        System.out.println(Collections.replaceAll(list,2,3));;
        print(list);
        
        list = Collections.singletonList(6);
        print(list);
        
    }
    
    private static void print(List<Integer> list) {
        System.out.println(String.join(",",list.stream().map(i -> i.toString()).collect(Collectors.toList()).toArray(new String[]{})));  
    }
    
}
