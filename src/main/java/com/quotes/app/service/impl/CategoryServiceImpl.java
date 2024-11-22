package com.quotes.app.service.impl;

import org.springframework.stereotype.Service;

import com.quotes.app.entity.Category;
import com.quotes.app.repoditory.CategoryMapper;
import com.quotes.app.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryMapper categoryMapper;

	@Override
	public Category findCategoryById(int id) {
		return categoryMapper.selectCategoryById(id);
	}

}
