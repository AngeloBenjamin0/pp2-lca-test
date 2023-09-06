package org.pp2;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class IntegracionClimatizadorDiscoverer {

    public List<IntegracionClimatizador> discover(String path) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<IntegracionClimatizador> result = new ArrayList<>();
        for (File f : Objects.requireNonNull(new File(path).listFiles())) {
            if (!f.getName().endsWith(".class")) continue;
            Class<?> cls = Class.forName(f.getName().replace(".class", ""));
            if (!IntegracionClimatizador.class.isAssignableFrom(cls))
                throw new RuntimeException(); // TODO: Elegir una mejor excepción
            result.add((IntegracionClimatizador) cls.getDeclaredConstructor().newInstance());
        }
        return result;
    }

}
