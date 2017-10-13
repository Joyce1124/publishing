package com.best.publishing.dao.common;

import com.best.publishing.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectDaoTest extends BaseTest {

    @Autowired
    ProjectDao projectDao;

    @Test
    public void getPoById() {
        projectDao.getById(0L);
    }
}
