package com.likang.myutil.util.singleton;

/**
 * 单例模式双重校验锁
 * @author LiKang
 *
 */
public class Singleton {
    private volatile static Singleton singleton;
    
    private Singleton (){}
    
    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}