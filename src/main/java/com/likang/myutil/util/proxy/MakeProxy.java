package com.likang.myutil.util.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**  
* @author likang  2018年10月27日 下午4:13:00
*/

public class MakeProxy {//implements InvocationHandler{
	Object object ;
	
	public MakeProxy(Object object) {
		this.object =  object;
	}

//	@Override
//	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//		
//		System.err.println("我是代理");
//		System.err.println(method.getName());
//		System.err.println(args[0]);
//		
//		return null;
//	}
	
	public Object getInstance() {
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//some modify
				
				
				System.err.println("我是代理");
				System.err.println(method.getName());
				System.err.println(args[0]);
				return method.invoke(object, args);
			}
		});
	}

}


