import java.lang.reflect.Method;

class innerClass {

    @MyAnnoationType(doSomething = "hello", date = "111-1113")
    public void myMethod(){
        System.out.println("myMethod");
    }
}

class innerClass2 {
    public void myExecute() throws Exception {
        System.out.println("myExecute");
        innerClass a = new innerClass();
        a.myMethod();

        Method method = a.getClass().getMethod("myMethod");
        MyAnnoationType annotation = method.getAnnotation(MyAnnoationType.class);
        if (annotation != null) {
            System.out.println("dosomething: " + annotation.doSomething());
            System.out.println("date: " + annotation.date());
            System.out.println("count: " + annotation.count());
        }
    }
}

public class annotation {

    public static void main(String[] args) throws Exception {
        System.out.println("Example of annotation use");
        innerClass2 a = new innerClass2();
        a.myExecute();
    }
}

