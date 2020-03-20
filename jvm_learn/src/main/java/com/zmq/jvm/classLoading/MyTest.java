package com.zmq.jvm.classLoading;
/**
 * *********类加载********
 */

/**
 * 情况1：只有MyParent1中有 public static String str="hello word";
 * main方法中是：System.out.println(MyChild1.str);
 * 运行结果是：MyParent static block
 *          hello word
 * 情况2：main方法中是：System.out.println(MyChild1.str);
 *                   System.out.println(MyChild1.str);
 * 运行结果是：MyParent static block
 *           hello word
 *           hello word
 * 情况:3：在子类中也添加 public static String str2="welcome";
 * main方法中是：System.out.println(MyChild1.str2);
 * 运行结果是：MyParent static block
 *          MyChild1 static block
 *          welcome

 */

/**
 * 结论：
 * 1.对于静态字段来说，只有直接定义了该字段的类才会被初始化（str字段是父类定义的，通过子类调用时，子类不会初始化）
 * 2.static代码块只有首次使用时会被调用，也就是说只有该类被初始化时会被调用。
 * 3.初始化一个子类之前所有父类一定初始化完毕。（所以调用子类str2时，父类static块被调用）
 * 4.-XX:+TraceClassLoading用于追踪类的加载信息
 * jvm参数：-XX:是固定格式
 * -XX:+<option>表示开启option选项
 * -XX:-<option>关闭
 * 例如设置堆空间大小：-XX:<option>=<value>,表示讲option选项的值设置为value
 */

class MyParent1{
    public static String str="hello word";
    static {
        System.out.println("MyParent static block");
    }
}
class MyChild1 extends MyParent1{
    public static String str2="welcome";
    static {
        System.out.println("MyChild1 static block");
    }
}
public class MyTest {
    public static void main(String[] args) {
//        System.out.println(MyChild1.str);
//        System.out.println(MyChild1.str);
        System.out.println(MyChild1.str2);


    }
}
