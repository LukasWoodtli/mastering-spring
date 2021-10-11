package com.woodtli.lukas.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ShoppingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StockDao stockDao;

    @Autowired
    private OrderDao orderDao;

    @PostConstruct
    public void checkAndPlaceOrder() {
        int availableQuantity = stockDao.retrieveStock().getQuantity();
        logger.info("Retrieved stock: {}", availableQuantity);
        if (availableQuantity > 0) {
            orderDao.placeOrder(availableQuantity);
        }
    }
}
