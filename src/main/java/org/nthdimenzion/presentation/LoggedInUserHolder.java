/*
 * Copyright (c) 1/23/15 5:06 PM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.presentation;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
public class LoggedInUserHolder {

    private LoggedInUserHolder() {
    }

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setUserName(String username) {
        contextHolder.set(username);
    }

    public static String getUserName() {
        return contextHolder.get();
    }

    public static void clearLoggedInUser() {
        contextHolder.remove();
    }
}
