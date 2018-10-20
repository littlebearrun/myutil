package com.likang.myutil.simplerpc;
/**  
* @author likang  2018年10月20日 上午8:14:47
*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RpcExporter {
	static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	public static void exporter(String hostName,int port) throws Exception{
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress(hostName, port));
		try {
			while(true) {
				executor.execute(new ExporterTask(server.accept()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			server.close();
		}
		
		
		
	}
	
	
	private static class ExporterTask implements Runnable{
		Socket client = null;
		
		public ExporterTask(Socket client) {
			this.client = client;
		}
		@Override
		public void run() {

			ObjectInputStream input = null;
			ObjectOutputStream out = null;
			try {
				input = new ObjectInputStream(client.getInputStream());
				String interfaceName = input.readUTF();
				Class service = Class.forName(interfaceName);
				
				String methodName = input.readUTF();
				
				Class<?>[] parameterTypes = (Class<?>[])input.readObject(); 
				Object[] args = (Object[]) input.readObject();
				
				Method method = service.getMethod(methodName, parameterTypes);
				
				Object result = method.invoke(service.newInstance(), args);
				
				out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(result);
				
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				if(out != null) {
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(client != null) {
					try {
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}


