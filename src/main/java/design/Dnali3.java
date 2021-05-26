package design;


/**
 * 饿汉枚举
 */
public enum Dnali3 {
    INSTANCE;
    public void fun(){

    }
    public static void main(String[] args){
        Dnali3.INSTANCE.fun();
    }
}
