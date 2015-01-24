/*
 * Copyright (c) 1/24/15 11:53 AM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion;

import org.joda.time.Duration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.Arrays;

/**
 * @author: Samir
 * @since 1.0 24/01/2015
 */
public class PasswordHelper {
    static ShaPasswordEncoder encoder = new ShaPasswordEncoder();

    public static void main(String a[]) {
        System.out.println("PLA password " + encoder.encodePassword("admin", "pla"));
    }
}
