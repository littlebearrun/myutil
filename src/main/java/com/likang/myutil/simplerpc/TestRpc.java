package com.likang.myutil.simplerpc;

import java.net.InetSocketAddress;

/**  
* @author likang  2018年10月20日 上午8:48:58
*/

public class TestRpc {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {

					try {
						RpcExporter.exporter("127.0.0.1", 8088);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}).start();
		
		RpcImporter<EchoService> importer = new RpcImporter<>();
		EchoService echo = importer.importer(EchoServiceImpl.class, new InetSocketAddress("127.0.0.1", 8088));
		System.err.println(echo.echo("测试消息"));
		
	}
}


