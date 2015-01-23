/*
 * Copyright (c) $today.year.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Samir
 * @since 1.0 23/01/2015
 */
@Controller
@RequestMapping("/")
public class MainApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public String applicationDefaultURI() {
        return "index";
    }
}
