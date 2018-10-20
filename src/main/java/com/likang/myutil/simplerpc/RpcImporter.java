package com.likang.myutil.simplerpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**  
* @author likang  2018年10月20日 上午8:30:53
*/

public class RpcImporter<S> {

	@SuppressWarnings("unchecked")
	public S importer(final Class<?> serviceClass, final InetSocketAddress address) {
		return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class<?>[] {serviceClass.getInterfaces()[0]}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket = null;
				ObjectOutputStream out = null;
				ObjectInputStream input = null;
				
				try {
					
					socket = new Socket();
					socket.connect(address);
					
					out = new ObjectOutputStream(socket.getOutputStream());
					out.writeUTF(serviceClass.getName());
					out.writeUTF(method.getName());
					out.writeObject(method.getParameterTypes());
					out.writeObject(args);
					input = new ObjectInputStream(socket.getInputStream());
					return input.readObject();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(socket != null ) {
						socket.close();
						
					}
					if(out != null) {
						out.close();
						
					}
					if(input != null) {
						input.close();
					}
				}
				
				return null;
			}
		});
	}
}


