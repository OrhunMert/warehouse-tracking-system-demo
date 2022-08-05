package com.trackingsystem.warehouse.utils;

import org.springframework.stereotype.Component;

@Component
public class CommunicationProperties {

        private final static int USER_LOCAL_HOST_PORT = 8080;
        private final static int NOTIFICATION_LOCAL_HOST_PORT = 8082;

        public static int getUserLocalHostPort() {
                return USER_LOCAL_HOST_PORT;
        }

        public static int getNotificationLocalHostPort() {
                return NOTIFICATION_LOCAL_HOST_PORT;
        }
}
