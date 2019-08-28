package com.rminfo.jinmaocs.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;

@Service
public class SpringBeanUtils implements BeanFactoryAware {
    private static BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringBeanUtils.beanFactory = beanFactory;
    }

    /**
     * 根据Bean的名称获取对应的UI想
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }
}
