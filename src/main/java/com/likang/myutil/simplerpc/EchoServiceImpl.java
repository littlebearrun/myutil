package com.likang.myutil.simplerpc;
/**  
* @author likang  2018年10月20日 上午8:13:14
*/

public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String str) {
		
		return str != null ? str + "-->I'am ok" : "i'am ok";
	}

}


