package 面试真题.T1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static Set<String> showMap(Map<String, Object> map) {
        //TODO your code goes here...
        for(String key:map.keySet()){
            System.out.println(key);
        }
        return null;
    }
}
class Main{
    public static void main(String[] args) {
        Map<String, Object> mapa=new HashMap<String, Object>();
        Map<String, Object> mapb=new HashMap<String, Object>();
        Map<String, Object> mapc=new HashMap<String, Object>();
        mapc.put("c",0);
        Object[] objects=new Object[3];
        objects[0]="v";
        objects[1]=2;
        objects[2]=mapc;
        mapb.put("b",objects);
        mapa.put("a",mapb);
        Solution.showMap(mapa);
    }
}
    /**
     * 对任意一个Map<String, Object>, 其 key 为 String,
     * 其 value 为 Map<String, Object> Object[] Number String 中的任意一种,
     * 显然叶子节点是 value 类型为 Number 或 String的节点,
     * 将 Map 转为多条字符串, 每条字符串表达其中一个叶子节点,
     * 比如:
     * {"a":{"b":["v",2,{"c":0}]},"d":[1,null,3]}
     * 将转化为以下这些字符串
     * a.b[0] = v
     * a.b[1] = 2
     * a.b[2].c = 0
     * d[0] = 1
     * d[1] = null
     * d[2] = 3
     *
     * @param map 上述的 map
     * @return 所有的字符串
     */
