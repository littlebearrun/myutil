package com.likang.myutil.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.likang.myutil.bean.Product;
import com.likang.myutil.config.MySetting;
import com.likang.myutil.mapper.ProductMapper;

/**
 *
 *@Author likang  2017年11月28日 下午3:46:12
 *
 */
@RestController
public class UtilController {

	@Autowired
	MySetting setting;
	
	@Autowired
	ProductMapper mapper;
	
	@RequestMapping("/product")
    public Product index() {
		
		Product p = new Product();
		p.setName(setting.getName());
		p.setDescription("java coder");
		mapper.insert(p);
        return p;
    }
}
