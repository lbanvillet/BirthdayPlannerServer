/*
 *-----------------------------------------------------------------------------
 * Copyright 2017 TBD
 *-----------------------------------------------------------------------------
 */
package com.tbd.birthdayplanner;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for dozer mappings in the application context.
 *
 * @author lb185112
 */
@Configuration
public class DozerConfiguration {

    /**
     * Creates the Dozer mapper bean.
     *
     * @return the Dozer mapper bean
     */
    @Bean
    public Mapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new MappingBuilder());
        return mapper;
    }
}