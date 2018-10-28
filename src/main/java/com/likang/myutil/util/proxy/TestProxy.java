package com.likang.myutil.util.proxy;
/**  
* @author likang  2018年10月27日 下午4:16:08
*/

public class TestProxy {

	public static void main(String[] args) throws InterruptedException {
		LiKangService service = new LiKangServiceImpl();
		MakeProxy proxy = new MakeProxy(service);
		service = (LiKangService) proxy.getInstance();
		System.err.println(service.makeMoney("likang"));
		Thread.sleep(10000);
	}
}


