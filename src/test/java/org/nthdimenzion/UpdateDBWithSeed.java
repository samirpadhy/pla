/*
 * Copyright (c) 1/24/15 11:36 AM.Nth Dimenzion, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package org.nthdimenzion;

import com.google.common.collect.ImmutableList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author: Samir
 * @since 1.0 24/01/2015
 */
public class UpdateDBWithSeed {

    List<File> seeds = ImmutableList.of(new File("src\\main\\resources\\scripts\\seed.sql"));

    public void execute(ApplicationContext context) throws IOException {
        DataSource dataSource = context.getBean("dataSourceRef", DataSource.class);
        JdbcTemplate template = new JdbcTemplate(dataSource);
        for (File seed : seeds) {
            System.out.println(seed.getName());
            JdbcTestUtils.executeSqlScript(template, new FileSystemResource(seed), true);
        }
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("dbSeedConfiguration.xml");
        UpdateDBWithSeed p = context.getBean(UpdateDBWithSeed.class);
        p.execute(context);
    }
}
