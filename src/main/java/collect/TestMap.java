package collect;

import java.util.HashMap;

/**
 * @author xxxyyy
 * @className TestMap
 * @description 测试高并发下map的链表循环
 * @date 2021/5/15 21:29
 */
public class TestMap {
    public static int hash(Object key) {
        if(key != null){
            int hash1 = key.hashCode();
        }
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public static void main(String[] args){
        String s = "test";
        HashMap<String,String> map1 = new HashMap(2,0.75f);
        int hashKey = hash("5");
        map1.put("5","C");
        new Thread("T1") {
            @Override
            public void run() {
                map1.put("7", "B");
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run(){
                map1.put("3","A");
            }
        }.start();
        System.out.println(map1);
    }
}
