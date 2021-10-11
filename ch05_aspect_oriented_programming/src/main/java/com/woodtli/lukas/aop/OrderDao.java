package com.woodtli.lukas.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void placeOrder(int value) {
        logger.info("Placed order: value {}", value);
    }
}
