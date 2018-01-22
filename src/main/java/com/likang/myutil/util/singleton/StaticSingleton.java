package com.likang.myutil.util.singleton;

/**
 * 利用静态代码块的特性实现单例
 * @author LiKang
 *
 */
public class StaticSingleton {
    private static StaticSingleton instance = null;

    static {
	    instance = new StaticSingleton();
    }
    
    private StaticSingleton (){

    }
    public static StaticSingleton getInstance() {
	    return instance;
    }
}