package com.best.publishing.dao.common;

import com.best.publishing.dao.BaseDaoImpl;
import com.best.publishing.po.common.ProjectPo;
import com.best.publishing.so.common.ProjectSo;

public class ProjectDaoImpl extends BaseDaoImpl<ProjectPo, ProjectSo> implements ProjectDao {
    protected String getNamespace() {
        return null;
    }
}
