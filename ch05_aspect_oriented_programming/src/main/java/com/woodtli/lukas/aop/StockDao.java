package com.woodtli.lukas.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class StockDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Stock retrieveStock() {
        logger.info("Retrieving a dummy Stock value");
        return new Stock(20);
    }

}
