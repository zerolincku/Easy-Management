package com.linck.management;

import com.linck.management.common.util.ClassUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

public class ClassUtilTest {

    @Test
    public void loadByPackageName() throws IOException {
        Set<Class<?>> classSet = ClassUtils.getClasses("com.linck.management.quartz.job");
        classSet.forEach(clazz -> {
            System.out.println(clazz.getName());
        });
        Assert.assertTrue(classSet.size() == 1);
    }

}
