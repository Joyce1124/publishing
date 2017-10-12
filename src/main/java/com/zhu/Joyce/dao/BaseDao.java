package com.zhu.Joyce.dao;

import com.zhu.Joyce.po.AbstractPo;
import com.zhu.Joyce.so.AbstractSo;

import java.util.List;

public interface BaseDao <P extends AbstractPo, S extends AbstractSo> {

    P getById(Long id);

    List<P> getByIdList(List<Long> idList);

    Long create(P p);

    void createBatch(List<P> list);

    void delete(Long id);

    void update(P p);

    void updateBatch(List<P> list);

    List<P> search(S s);

    Long searchCount(S s);
}
