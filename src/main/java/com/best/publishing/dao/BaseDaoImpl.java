package com.best.publishing.dao;

import com.best.publishing.exception.OptLockException;
import com.best.publishing.po.AbstractPo;
import com.best.publishing.so.AbstractSo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Date;
import java.util.List;

public abstract class BaseDaoImpl<P extends AbstractPo, S extends AbstractSo> implements BaseDao<P, S> {

    @Autowired
    @Qualifier("sqlSession")
    private SqlSession sqlSession;

    protected SqlSession getSqlSession() {
        return sqlSession;
    }

    protected abstract String getNamespace();

    public Long create(P p) {

        p.setCreatedTime(new Date());

        String path = getNamespace() + "create";

        sqlSession.insert(path, p);
        return p.getId();
    }


    public void createBatch(List<P> list) {
        for (P p : list) {
            create(p);
        }
    }

    public void delete(Long id) {
        String path = getNamespace() + "delete";
        sqlSession.delete(path, id);
    }

    public void update(P p) {

        p.setUpdatedTime(new Date());

        String path = getNamespace() + "update";

        int size = sqlSession.update(path, p);
        if (size != 1) {
            throw new OptLockException("乐观锁异常!");
        }

        p.setLockVersion(p.getLockVersion() + 1);
    }

    public void updateBatch(List<P> list) {
        for (P p : list) {
            update(p);
        }
    }

    public List<P> getByIdList(List<Long> idList) {
        String path = getNamespace() + "getByIdList";
        List<P> rlt = sqlSession.selectList(path, idList);
        return rlt;
    }

    public P getById(Long id) {
        String path = getNamespace() + "getById";
        P rlt = sqlSession.selectOne(path, id);
        return rlt;
    }

    public List<P> search(S s) {
        String path = getNamespace() + "search";
        RowBounds rowBounds = new RowBounds((s.getPage() - 1) * s.getRows(), s.getRows());
        return sqlSession.selectList(path, s, rowBounds);
    }

    public Long searchCount(S s) {
        String path = getNamespace() + "searchCount";
        return sqlSession.selectOne(path, s);
    }
}
