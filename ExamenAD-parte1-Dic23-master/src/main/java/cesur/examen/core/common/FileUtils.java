package cesur.examen.core.common;

import cesur.examen.core.worker.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Pablo Postigo Morales
 * Fecha: 11/12/2023
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */


public class FileUtils {

    public static void toCSV(String fileName, List<Worker> workers) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Worker worker :
                    workers) {
                String nombre = worker.getName();
                String dni = worker.getDni();
                String desde = String.valueOf(worker.getFrom());
                bw.write(nombre+", "+dni+", "+desde+"\n");

            }
        }

        /*
        Uncomment and implement body method!...

        try (...) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        */
    }
}
