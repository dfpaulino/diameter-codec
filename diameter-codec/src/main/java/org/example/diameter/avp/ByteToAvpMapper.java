package org.example.diameter.avp;



import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ByteToAvpMapper {

    private static final Logger logger = LoggerFactory.getLogger(ByteToAvpMapper.class);
    private static  ByteToAvpMapper instance;
    private static final String[] avpPackages = {"org.example.diameter.avp.common","org.example.diameter.avp.gx","org.example.diameter.avp.rx"};

    private Map<Integer,AvpBuilder> avpBuilderMapper= null;

    public ByteToAvpMapper() {
        loadMap();
    }

    public static synchronized ByteToAvpMapper getInstance(){
        if(null == instance) {
            instance = new ByteToAvpMapper();
        }
        return instance;
    }

    public Map<Integer, AvpBuilder> getAvpBuilderMapper() {
        return avpBuilderMapper;
    }

    /*
        Register all classes with @AvpRegister into mapper
         */
    private void loadMap(){
        Map<Integer,AvpBuilder> map = new HashMap<>();
        Arrays.stream(avpPackages).forEach(pkg -> {
                logger.debug("Scanning [{}]",pkg);
                Reflections reflections = new Reflections(pkg);
                Set<Class<?>> clazzes =  reflections.getTypesAnnotatedWith(AvpRegister.class);
                clazzes.forEach(clz ->{
                    int code = clz.getAnnotation(AvpRegister.class).avpCode();
                    String builderMethod = clz.getAnnotation(AvpRegister.class).avpBuilderMethod();
                    try {
                        logger.debug("Loading AVP [{}],class [{}]...",code,clz.getSimpleName());
                        Method method = clz.getDeclaredMethod(builderMethod);
                        AvpBuilder avpBuilder = (AvpBuilder)method.invoke(null);
                        map.put(code,avpBuilder);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        logger.error("Exception",e);
                    } finally {
                        logger.debug("Loaded AVP [{}],class [{}]",code,clz.getSimpleName());
                    }
                });
            }
        );
        avpBuilderMapper = Collections.unmodifiableMap(map);
    }
}
