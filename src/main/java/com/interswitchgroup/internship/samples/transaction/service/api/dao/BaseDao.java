package com.interswitchgroup.internship.samples.transaction.service.api.dao;

import java.util.List;

import com.interswitchgroup.internship.samples.transaction.service.api.models.Page;

public interface BaseDao<T> {
	public T create(T Model);

    public boolean update(T model);

    public T find(long id);

    public List<T> findAll();

    public Page<T> findAll(int pageNumber, int pageSize);

    public boolean delete(T model);
}
