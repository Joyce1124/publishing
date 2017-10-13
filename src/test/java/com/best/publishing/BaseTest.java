package com.best.publishing;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = {
        "classpath*:testContext.xml",
        "classpath*:WEB-INF/spring/daoContext.xml",
        "classpath*:WEB-INF/spring/managerContext.xml",
        "classpath*:WEB-INF/spring/serviceContext.xml",
})
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

}
