package com.hzh.app.circulardependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@ComponentScan("com.hzh.app.circulardependency")
//@Configuration
@Component
//Set<AnnotationAttributes> componentScans = AnnotationConfigUtils.attributesForRepeatable(
//				sourceClass.getMetadata(), ComponentScans.class, ComponentScan.class);
//fixme  sourceClass = 任何一个component
public class CircularDependencyConfiguration {
}
