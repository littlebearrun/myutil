package com.likang.myutil;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.likang.myutil.bean.Product;
import com.likang.myutil.mapper.ProductMapper;
import com.likang.myutil.service.IProductService;
import com.likang.myutil.service.impl.ProductServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyutilApplicationTests {

	@Resource(name="")
	private IProductService service;
	
	@Test
	public void contextLoads() {
		
		Product p = service.getProduct(1);
		System.err.println(p.getName());
//		service.insertBatch(null);
//		service.updateBatch(null);
		
	}

}
