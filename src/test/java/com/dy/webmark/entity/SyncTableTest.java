package com.dy.webmark.entity;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.duanbn.support.spring.mydao.TableSyncBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/applicationContext.xml" })
public class SyncTableTest {

    @Resource
    private TableSyncBean tsb;

    @Test
    public void test() {

    }

}
