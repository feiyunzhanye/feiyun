package design;

/**
 * 饿汉
 */
public class Danli2 {
    private Danli2(){}
    public static class SingletonHolder{
        private static final Danli2 danli2 = new Danli2();
    }
    public static Danli2 getDanli(){
        return SingletonHolder.danli2;
    }

}
