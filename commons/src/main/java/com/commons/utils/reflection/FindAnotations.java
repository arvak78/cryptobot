package com.commons.utils.reflection;

import com.commons.Exchanges;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class FindAnotations {
    private static FindAnotations ourInstance = new FindAnotations();

    public static FindAnotations getInstance() {
        return ourInstance;
    }

    private static Logger log = LoggerFactory.getLogger(FindAnotations.class);

    private Map<Exchanges, Class> allClassesAnnotatedBy = new HashMap<>();

    public Map<ModuleName, File> findMavenSubModules(String pom) throws IOException {
        ProjectReader reader = new ProjectReader(new File(pom));
        return reader.read();
    }

    public Map<Exchanges, Class> findAnnotatedClassesInModule(String path, Class<? extends Annotation> annotatedClass) {
        Map<Exchanges, Class> classMap = new HashMap<>();
        Reflections ref = new Reflections(path);
        for (Class<?> cl : ref.getTypesAnnotatedWith(annotatedClass)) {
            Annotation annotation = cl.getAnnotation(annotatedClass);
            if (annotation != null) {
                try {
                    Exchanges name = (Exchanges) annotation.annotationType().getMethod("name").invoke(annotation);
                    classMap.put(name, cl);
                } catch (Exception ex) {
                    log.error("Error al recuperar el connector: " , ex);
                }

            }
        }

        return classMap;
    }

    public Map<Exchanges, Class> findAllClassesAnnotatedBy(Class<? extends Annotation> annotatedClass) {

        if (allClassesAnnotatedBy != null && allClassesAnnotatedBy.size() > 0)
            return allClassesAnnotatedBy;

        Map<Exchanges, Class> classMap = new HashMap<>();
        try {
            Map<ModuleName, File> mavenSubModules = findMavenSubModules("connectors/pom.xml");
            for (ModuleName moduleName : mavenSubModules.keySet()) {
                allClassesAnnotatedBy.putAll(findAnnotatedClassesInModule(moduleName.getGroupId(), annotatedClass));
            }

        } catch (IOException e) {
            return classMap;
        }

        return allClassesAnnotatedBy;
    }
}
