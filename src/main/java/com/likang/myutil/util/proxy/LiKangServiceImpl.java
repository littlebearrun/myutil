package com.likang.myutil.util.proxy;
/**  
* @author likang  2018年10月27日 下午4:12:11
*/

public class LiKangServiceImpl implements LiKangService {

	@Override
	public String makeMoney(String who) {
		String msg = who + " make much money ~";
		System.err.println("serviceImpl:"+msg);
		return msg; 
	}

}


