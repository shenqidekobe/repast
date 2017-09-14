package com.yiyou.repast.merchant.service;

import repast.yiyou.common.util.DataGrid;

import java.util.List;

public interface IBaseService<T> {

    T findById(Long merchantId, Long id);

    List<T> findAll(Long merchantId);

    T save(Long merchantId, T obj);

    void remove(Long merchantId, Long id);

    T update(Long merchantId, T obj);

    DataGrid<T> findList(Long merchantId, int page, int pageSize);
}
