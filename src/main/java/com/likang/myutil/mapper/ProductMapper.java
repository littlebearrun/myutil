package com.likang.myutil.mapper;

import java.util.List;

import com.likang.myutil.bean.Product;

//@Mapper  //启动项里面可以直接配置扫描mapper这个包下的所有Mapper
public interface ProductMapper {
    Product selectByPrimaryKey(Integer id);

    int insert(Product record);
    
    int insertBatch(List<Product> list);
    int updateBatch(List<Product> list);

    int updateByPrimaryKeySelective(Product record);

    int deleteByPrimaryKey(Integer id);
}