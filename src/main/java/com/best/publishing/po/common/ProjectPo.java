package com.best.publishing.po.common;

import com.best.publishing.po.AbstractPo;

public class ProjectPo extends AbstractPo {

    /**
     * 添加项目的用户id
     */
    private Long userId;

    /**
     * 项目名字
     */
    private String name;

    /**
     * 项目环境1：测试，2：预发，3：线上
     */
    private Integer level;

    /**
     * 线上当前版本，用于快速回滚
     */
    private String version;

    /**
     * svn/git地址
     */
    private String repoUtl;

    /**
     * 版本管理系统的用户名，一般为svn的用户名
     */
    private String repoUsername;

    /**
     * 版本管理系统的密码，一般为svn的密码
     */
    private String repoPassword;

    /**
     * 上线方式：branch/tag
     */
    private String repoMode;

    /**
     * 上线方式：git/svn
     */
    private String repoType;

    /**
     * 宿主机存放clone出来的文件
     */
    private String deployFrom;

    /**
     * 需要拷贝的文件
     */
    private String includes;

    /**
     * 目标机器用户
     */
    private String releaseUser;

    /**
     * 目标机器的目录
     */
    private String releaseTo;

    /**
     * 目标机器列表
     */
    private String hosts;

    /**
     * 部署前置任务
     */
    private String preDeploy;

    /**
     * 同步之前任务
     */
    private String postDeploy;

    /**
     * 同步之前目标机器执行的任务
     */
    private String preRelease;

    /**
     * 每台目标机执行post_release任务间隔/延迟时间 单位:秒
     */
    private Integer postReleaseDelay;

    /**
     * 是否需要审核任务0不需要，1需要
     */
    private String audit;


    /**
     * 线上版本保留数
     */
    private String keepVersionNum;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRepoUtl() {
        return repoUtl;
    }

    public void setRepoUtl(String repoUtl) {
        this.repoUtl = repoUtl;
    }

    public String getRepoUsername() {
        return repoUsername;
    }

    public void setRepoUsername(String repoUsername) {
        this.repoUsername = repoUsername;
    }

    public String getRepoPassword() {
        return repoPassword;
    }

    public void setRepoPassword(String repoPassword) {
        this.repoPassword = repoPassword;
    }

    public String getRepoMode() {
        return repoMode;
    }

    public void setRepoMode(String repoMode) {
        this.repoMode = repoMode;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getDeployFrom() {
        return deployFrom;
    }

    public void setDeployFrom(String deployFrom) {
        this.deployFrom = deployFrom;
    }

    public String getIncludes() {
        return includes;
    }

    public void setIncludes(String includes) {
        this.includes = includes;
    }

    public String getReleaseUser() {
        return releaseUser;
    }

    public void setReleaseUser(String releaseUser) {
        this.releaseUser = releaseUser;
    }

    public String getReleaseTo() {
        return releaseTo;
    }

    public void setReleaseTo(String releaseTo) {
        this.releaseTo = releaseTo;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getPreDeploy() {
        return preDeploy;
    }

    public void setPreDeploy(String preDeploy) {
        this.preDeploy = preDeploy;
    }

    public String getPostDeploy() {
        return postDeploy;
    }

    public void setPostDeploy(String postDeploy) {
        this.postDeploy = postDeploy;
    }

    public String getPreRelease() {
        return preRelease;
    }

    public void setPreRelease(String preRelease) {
        this.preRelease = preRelease;
    }

    public Integer getPostReleaseDelay() {
        return postReleaseDelay;
    }

    public void setPostReleaseDelay(Integer postReleaseDelay) {
        this.postReleaseDelay = postReleaseDelay;
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getKeepVersionNum() {
        return keepVersionNum;
    }

    public void setKeepVersionNum(String keepVersionNum) {
        this.keepVersionNum = keepVersionNum;
    }
}
