package com.ablog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test(){

        logger.info("测试>>>");

    }

    public static void main(String[] args) {
        logger.info("测试>>>");
    }

}
