//package com.yunbao.autosendmq.Sancer;
//
//import com.yunbao.autosendmq.Annotation.AutoSendMq;
//import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
//import org.springframework.beans.factory.config.BeanDefinitionHolder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
//import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
//import org.springframework.core.type.filter.AnnotationTypeFilter;
//import org.springframework.core.type.filter.TypeFilter;
//
//import java.util.Set;
//
//public final  class AutoSendSancer extends ClassPathBeanDefinitionScanner {
//
//
//    public AutoSendSancer(BeanDefinitionRegistry registry) {
//        super(registry);
//    }
//
//    public AutoSendSancer(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
//        super(registry, useDefaultFilters);
//    }
//
//
//    public void registerDefaultFilters(TypeFilter includeFilter) {
//        this.addIncludeFilter(new AnnotationTypeFilter(AutoSendMq.class));
//    }
//
//    @Override
//    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
//        this.addIncludeFilter(new AnnotationTypeFilter(AutoSendMq.class));
//        Set<BeanDefinitionHolder> beanDefinitions =   super.doScan(basePackages);
//        for (BeanDefinitionHolder holder : beanDefinitions) {
//            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
//            definition.getPropertyValues().addPropertyValue("innerClassName",definition.getBeanClassName());
//            definition.setBeanClass(AutoSendFactoryBean.class);
//        }
//        return beanDefinitions;
//
//    }
//
//    @Override
//    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition)  {
//        System.out.println("判断----------------");
//        System.out.println("工厂方法名字"+beanDefinition.getFactoryMethodName());
//
//
//
//        return super.isCandidateComponent(beanDefinition) && beanDefinition.getMetadata()
//                .hasAnnotation(AutoSendMq.class.getName());
//    }
//}
