package us;

import org.junit.jupiter.api.Test;
import org.pp2.ClimaTotal;
import org.pp2.ComandoDeshabilitadoException;
import org.pp2.Dispositivo;

import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserStory3Test {

    @Test
    public void ca1HorarioHabilitado() throws FileNotFoundException {
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application-us3-ca1.properties").toString();
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
        List<Dispositivo> dispositivos = climaTotal.getDispositivos();
        Dispositivo d1 = dispositivos.get(0);

        d1.ejecutar("ENCENDER");
    }

    @Test
    public void ca2HorarioInhabilitado() throws FileNotFoundException {
        String propertiesPath = FileSystems.getDefault().getPath("src", "test", "resources", "application-us3-ca2.properties").toString();
        String dispositivosPath = FileSystems.getDefault().getPath("src", "test", "resources", "dispositivo").toString();
        ClimaTotal climaTotal = new ClimaTotal(dispositivosPath, propertiesPath);
        List<Dispositivo> dispositivos = climaTotal.getDispositivos();
        Dispositivo d1 = dispositivos.get(0);

        ComandoDeshabilitadoException excepcion = assertThrows(ComandoDeshabilitadoException.class, () ->
                d1.ejecutar("ENCENDER"));

        assertEquals(ComandoDeshabilitadoException.class, excepcion.getClass());
        assertEquals(excepcion.getMessage(), "Error al ejecutar el comando ENCENDER. Ejecución de comandos deshabilitada desde 9 hasta 12.");
    }

}
