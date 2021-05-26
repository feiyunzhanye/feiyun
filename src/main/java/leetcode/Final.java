package leetcode;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class Final {
    private final int[] myArray;
    public void print(){
        System.out.println(myArray[0]);
    }
    public Final(int[] myArray ){
        this.myArray = myArray;
    }
    public static void main(String args[]){
        int[] array = {1,2};
        Final t = new Final(array);
        ConcurrentHashMap map = new ConcurrentHashMap();
        t.print();
        array[0] = 3;
        t.print();

    }
}
