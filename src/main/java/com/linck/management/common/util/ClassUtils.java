package com.linck.management.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-13 21:49
 **/
public final class ClassUtils {

    private static final Logger log = LoggerFactory.getLogger(ClassUtils.class);

    private ClassUtils() {}


    /**
     * 获取某个包下的所有类
     * */
    public static Set<Class<?>> getClasses(String packageName) throws IOException {
        Set<Class<?>> classSet = new HashSet<>();
        Enumeration<?> urls = Thread.currentThread().getContextClassLoader().getResources(packageName.replace(".", "/"));
        while (urls.hasMoreElements()) {
            URL url = (URL) urls.nextElement();
            if (url != null) {
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String packagePath = url.getPath().replaceAll("%20", " ");
                    addClass(classSet, packagePath, packageName);
                } else if ("jar".equals(protocol)) {
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    if (jarURLConnection != null) {
                        JarFile jarFile = jarURLConnection.getJarFile();
                        if (jarFile != null) {
                            Enumeration<?> jarEntries = jarFile.entries();
                            while (jarEntries.hasMoreElements()) {
                                JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
                                String jarEntryName = jarEntry.getName();
                                if (jarEntryName.endsWith(".class")) {
                                    String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                    doAddClass(classSet, className);
                                }
                            }
                        }
                    }
                }
            }
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (!Strings.isNullOrEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(classSet, className);
            } else {
                String subPackagePath = fileName;
                if (!Strings.isNullOrEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (!Strings.isNullOrEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    /**
     * 加载类
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, ClassUtils.class.getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
