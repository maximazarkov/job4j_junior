package ru.job4j.io;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UsageLog4j {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace massage");
        LOG.debug("debug massage");
        LOG.info("info massage");
        LOG.warn("warn massege");
        LOG.error("error massage");
    }
}
