package com.core;

import com.commons.Exchanges;
import com.commons.utils.reflection.FindAnotations;
import com.commons.utils.reflection.ModuleName;
import com.commons.utils.reflection.ProjectReader;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Manel on 02/02/2018.
 */
@Service
public class FindAnnotation {

    private Map<Exchanges, Class> allClassesAnnotatedBy;

//    private static Logger log = LoggerFactory.getLogger(FindAnnotation.class);

//    private Map<ModuleName, File> findMavenSubModules(String pom) throws IOException {
//        ProjectReader reader = new ProjectReader(new File(pom));
//        return reader.read();
//    }
//
//    private Map<Exchanges, Class> findAnnotatedClassesInModule(String path, Class<? extends Annotation> annotatedClass) {
//        Map<Exchanges, Class> classMap = new HashMap<>();
//        Reflections ref = new Reflections(path);
//        for (Class<?> cl : ref.getTypesAnnotatedWith(annotatedClass)) {
//            Annotation annotation = cl.getAnnotation(annotatedClass);
//            if (annotation != null) {
//                try {
//                    Exchanges name = (Exchanges) annotation.annotationType().getMethod("name").invoke(annotation);
//                    classMap.put(name, cl);
//                } catch (Exception ex) {
//                    log.error("Error al recuperar el connector: " , ex);
//                }
//
//            }
//        }
//
//        return classMap;
//    }
//
//    public Map<Exchanges, Class> findAllClassesAnnotatedBy(Class<? extends Annotation> annotatedClass) {
//
//        if (allClassesAnnotatedBy != null && allClassesAnnotatedBy.size() > 0)
//            return allClassesAnnotatedBy;
//
//        Map<Exchanges, Class> classMap = new HashMap<>();
//        try {
//            Map<ModuleName, File> mavenSubModules = findMavenSubModules("connectors/pom.xml");
//            for (ModuleName moduleName : mavenSubModules.keySet()) {
//                allClassesAnnotatedBy.putAll(findAnnotatedClassesInModule(moduleName.getGroupId(), annotatedClass));
//            }
//
//        } catch (IOException e) {
//            return classMap;
//        }
//
//        return allClassesAnnotatedBy;
//    }


    public Map<Exchanges, Class> findAllClassesAnnotatedBy(Class<? extends Annotation> annotatedClass) {

        if (allClassesAnnotatedBy != null && allClassesAnnotatedBy.size() > 0) {
            return allClassesAnnotatedBy;
        }

        FindAnotations findAnotations = new FindAnotations();
        allClassesAnnotatedBy = findAnotations.findAllClassesAnnotatedBy(annotatedClass);
        return allClassesAnnotatedBy;
    }
}
