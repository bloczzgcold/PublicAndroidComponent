package com.qdtckj.component.logger

import org.junit.Before
import org.junit.Test


public class LoggerTest {

    @Before
    public void before() {
        Logger.setLogger(new SystemLogger());
    }

    @Test
    public void testEnable() {

        Logger.setLevel(Level.INFO);

        Assert.assertTrue("当前日志级别为Info, verbose不可用", !Logger.isVerboseEnabled());
        Assert.assertTrue("当前日志级别为Info, debug不可用", !Logger.isDebugEnabled());
        Assert.assertTrue("当前日志级别为Info, info可用", Logger.isInfoEnabled());
        Assert.assertTrue("当前日志级别为Info, warn可用", Logger.isWarnEnabled());
        Assert.assertTrue("当前日志级别为Info, error可用", Logger.isErrorEnabled());

    }

}
