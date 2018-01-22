package com.likang.myutil.service;

import java.util.List;

import com.likang.myutil.bean.Product;

/**
 *
 *@Author likang  2017年12月1日 上午10:41:13
 *
 */
public interface IProductService {

	public Product getProduct(Integer id);
	
	
	public void insertBatch(List<Product> products);
	public void updateBatch(List<Product> products);
	
}
