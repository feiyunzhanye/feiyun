package leetcode;

public class Singleton2 {
    private  static final class singletonHolder{
        private static final Singleton2 instance = new Singleton2();
    }
    public Singleton2 getInstance(){
        return  singletonHolder.instance;
    }


}
