package leetcode;

public class Single {
    private static volatile Single sin;
    private static  Single getSin(){
        if(sin == null){
            synchronized (Single.class){
                if(sin == null){
                    sin = new Single();
                }
            }
        }
        return sin;
    }
}
