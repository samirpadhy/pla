package org.nthdimenzion;

import com.pla.mvcdemo.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nthdimenzion.application.Application;
import org.nthdimenzion.security.service.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private TestService testService;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(authenticationEntryPoint);
    }

}
