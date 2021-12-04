package com.gmail.elbaglikov.palmetto.config;

import com.gmail.elbaglikov.palmetto.model.Order;
import com.gmail.elbaglikov.palmetto.model.OrderStatus;

import java.time.LocalDateTime;

public class TestData {
    public static final Order ORDER_READY =
            new Order(1l,
                    "cl@m.lv",
                    "Ivanov",
                    "pizza tempo",
                    "Dublin",
                    OrderStatus.READY,
                    LocalDateTime.of(2012, 11, 30, 21, 22));

    public static final Order ORDER_COOK =
            new Order(1l,
                    "cl@m.lv",
                    "Ivanov",
                    "pizza tempo",
                    "Dublin",
                    OrderStatus.COOK,
                    LocalDateTime.of(2012, 11, 30, 21, 22));

    public static final Order ORDER_DELIVER =
            new Order(1l,
                    "cl@m.lv",
                    "Ivanov",
                    "pizza tempo",
                    "Dublin",
                    OrderStatus.DELIVER,
                    LocalDateTime.of(2012, 11, 30, 21, 22));
}
