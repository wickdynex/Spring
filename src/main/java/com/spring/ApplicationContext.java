package com.spring;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private Class configClass;

    private ConcurrentHashMap<String, Object> singletonsObjects = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public ApplicationContext(Class configClass) {
        this.configClass = configClass;
        scan(configClass);
        for(String beanName : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getBeanScope().equals(BeanScope.singleton.getScope())) {
                Object bean = createBean(beanDefinition);
                singletonsObjects.put(beanName, bean);
            }
            else {

            }
        }
    }

    public Object createBean(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        try {

            Object instance = beanClass.getDeclaredConstructor().newInstance();
            for (Field declaredField : beanClass.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }

            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void scan(Class configClass) {
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();
            path = path.replace(".", "/");
            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            File file = new File(resource.getFile());
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File f : files) {
//                    System.out.println(f);
                    String fileName = f.getAbsolutePath();
                    if (fileName.endsWith(".class")) {
                        String classPath = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
//                        System.out.println(classPath);
                        classPath = classPath.replace("\\", ".");
//                        System.out.println(classPath);
                        try {
                            Class<?> clazz = classLoader.loadClass(classPath);
                            if (clazz.isAnnotationPresent(Component.class)) {
                                // set bean name to beanDefinationMap
                                Component componentAnnotation = clazz.getAnnotation(Component.class);
                                String beanName = componentAnnotation.value();
                                if (beanName.isEmpty()) {
                                    beanName = clazz.getSimpleName();
                                    beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
                                }
//                                System.out.println(beanName);

                                BeanDefinition beanDefinition = new BeanDefinition();
                                beanDefinition.setBeanClass(clazz);
                                beanDefinition.setBeanScope(BeanScope.singleton.getScope());
                                if (clazz.isAnnotationPresent(Scope.class)) {
                                    String beanScope = clazz.getAnnotation(Scope.class).value();
                                    beanDefinition.setBeanScope(beanScope);
                                }
                                beanDefinitionMap.put(beanName, beanDefinition);
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
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            String beanScope = beanDefinition.getBeanScope();
            Object object;
            if(beanScope.equals(BeanScope.singleton.getScope())){
                 object = singletonsObjects.get(beanName);
            }
            else {
                // create a new bean
                object = createBean(beanDefinition);
            }
            return object;
        }
        else {
            throw new NullPointerException("Bean not found: " + beanName);
        }
    }
}
