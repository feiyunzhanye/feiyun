package design;

/**
 * 饱汉
 */
public class Danli {
    private static volatile Danli danli = null;

    public Danli getDanli(){
        if(danli == null){
            synchronized (Danli.class){
                if(danli == null){
                    danli = new Danli();
                }
            }
        }
        return danli;
    }
}
