package com.likang.myutil.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likang.myutil.bean.Product;
import com.likang.myutil.mapper.ProductMapper;
import com.likang.myutil.service.IProductService;

/**
 *
 *@Author likang  2017年12月1日 上午10:41:58
 *
 */
@Service("imple1")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductMapper mapper;
	
	@Override
	public Product getProduct(Integer id) {

		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void insertBatch(List<Product> list) {
		List<Product> ps = new ArrayList<Product>();
		for(int i = 0; i < 10 ; i ++){
			Product p = new Product("lili", "lolo", new Date());
			ps.add(p);
		}
		
		int i = mapper.insertBatch(ps);
		System.err.println("插入结果:" + i);
	}
	
	@Override
	public void updateBatch(List<Product> products) {
		List<Product>  ps = new ArrayList<>();
		for(int i = 10; i < 20; i ++){
			ps.add(getProduct(i));
		}
		
		int i = mapper.updateBatch(ps);
		System.err.println("更新结果:"+i);
	}

}
