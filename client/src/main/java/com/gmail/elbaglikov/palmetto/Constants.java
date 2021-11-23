package com.gmail.elbaglikov.palmetto;

public final class Constants {
    private Constants() {}

    //kafka settings
    public static final String TOPIC_ORDER_NAME = "ORDER";
    public static final String TOPIC_NOTIFICATION_NAME = "NOTIFICATION";
    public static final String BOOTSTRAP_SERVER = "localhost:29092";
    public static final String CLIENT_GROUP = "client";
}
