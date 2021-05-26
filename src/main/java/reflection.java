import jdk.nashorn.internal.runtime.logging.Logger;
import leetcode.Solution;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.ServiceLoader;

@Logger
public class reflection extends SecureClassLoader {

    public void getMyClass() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class sing  = Class.forName("leetcode.Solution");
        Object o = sing.newInstance();
        Method method = sing.getMethod("test");
        method.invoke(o);
        Solution solution = new Solution();
        Class s2 = solution.getClass();
        System.out.println(sing == s2);
    }
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        reflection ref = new reflection();
        ref.getMyClass();
    }
}
