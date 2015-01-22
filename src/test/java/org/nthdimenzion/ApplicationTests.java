package org.nthdimenzion;

import com.pla.mvcdemo.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        testService.testMethod();
    }

}
