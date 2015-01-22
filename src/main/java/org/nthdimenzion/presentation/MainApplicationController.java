package org.nthdimenzion.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: Samir
 * @since 1.0
 */
@Controller
@RequestMapping("/")
public class MainApplicationController {

    @RequestMapping(method = RequestMethod.GET)
    public String applicationDefaultURI() {
        return "index";
    }
}
