package com.spring;

import java.io.File;
import java.net.URL;

public class ApplicationContext {
    private Class configClass;

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;
        if(configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();
            path = path.replace(".", "/");
            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
                    System.out.println(f);
                    String fileName = f.getAbsolutePath();
                    if(fileName.endsWith(".class"))  {
                        String classPath = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                        System.out.println(classPath);
                        classPath = classPath.replace("\\", ".");;
                        System.out.println(classPath);
                        try {
                            Class<?> clazz = classLoader.loadClass(classPath);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                // create a bean
                            }
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        }
    }

    public Object getBean(String beanName) {
        return null;
    }
}
