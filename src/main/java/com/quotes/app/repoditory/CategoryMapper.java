package com.quotes.app.repoditory;

import org.apache.ibatis.annotations.Mapper;

import com.quotes.app.entity.Category;

@Mapper
public interface CategoryMapper {
	
	Category selectCategoryById(int id);

}
