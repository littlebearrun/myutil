package com.likang.myutil.util.node;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**  
* @author likang  2018年10月29日 下午10:55:44
*/

public class QueueAndStack {

	public static void main(String[] args) {
		//先进先出
		Queue<Integer> queue = new ArrayDeque<>();//new LinkedList<>();
		
		queue.offer(1);
		queue.offer(2);
		
		System.err.println("队列中首个元素：" + queue.peek());//首个元素
		System.err.println("队列取出元素：" + queue.poll());//取出元素
		System.err.println("队列是否为空：" + queue.isEmpty());//取出元素
		System.err.println("队列取出元素：" + queue.poll());//取出元素
		System.err.println("队列是否为空：" + queue.isEmpty());//取出元素
		System.err.println();
		//后进先出
		Stack<Integer> s = new Stack<>();
		s.push(3);//压入顶部
		s.push(4);
		
		System.err.println("获取栈定元素：" + s.peek());//取出顶部元素
		System.err.println("取出栈定元素" + s.pop());//取出顶部元素
		System.err.println("栈是否为空：" + s.empty());//取出顶部元素
		System.err.println("取出栈定元素：" + s.pop());//取出顶部元素
		System.err.println("栈是否为空：" + s.empty());//取出顶部元素
		
	}
}


