package cesur.examen;

import cesur.examen.core.common.FileUtils;
import cesur.examen.core.common.JDBCUtils;
import cesur.examen.core.worker.Worker;
import cesur.examen.core.worker.WorkerDAO;
import cesur.examen.core.worker.WorkerService;

import java.io.IOException;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 */
public class App 
{
    /**
     *
     * NO MODIFICAR EL CODIGO DE ESTA CLASE.
     * SI SE MODIFICA NO SE CORREGIRÁ EL RESTO DEL PROYECTO.
     *
     * Descomentar el código conforme se vayan creando los
     * métodos implicados
     *
     */


    // Put your full name in the constant value
    static final String STUDENT_NAME = "Pablo Postigo Morales";

    // filename for csv output.
    static final String FILE_NAME = "output.csv";

    public static void main( String[] args )
    {
        WorkerDAO workerDAO = new WorkerDAO(JDBCUtils.getConn());
        WorkerService workerService = new WorkerService(JDBCUtils.getConn());

        /*

        First Part of exam: JDBC and File Access
        Don't change anything in this part, just run and see the terminal...

         */

        System.out.println("----------------------------------------------------------");
        System.out.println(" >>> "+ STUDENT_NAME.toUpperCase() + " JDBC and File exercises");
        System.out.println("----------------------------------------------------------\n");

        // Checking the connection...
        if( JDBCUtils.getConn()==null){
            throw new RuntimeException("Please, begin implementing JDBCUtils!");
        }

        // Get all workers  ordered asc by From date
        System.out.print("Getting all workers...");
        var allWorkers= workerDAO.getAllOrderByFrom();
        if(allWorkers.size()==0) throw new RuntimeException("getAllOrderByFrom() not implemented yet!");
        System.out.println("ok!");

        // Find the oldest worker
        System.out.print("Looking for the oldest worker... ");

        Worker oldest = allWorkers.get(0);
        System.out.println(oldest.toString());

        // Renovate the oldest worker
        System.out.print("Renovating the oldest worker... ");
        oldest = workerService.renovateWorker( oldest.getDni() );
        System.out.println(oldest.toString() + " renovated successfully!");

        // Save all the workers to file
        System.out.print("Saving all workers to csv file... ");
        try {
            FileUtils.toCSV(App.FILE_NAME, workerDAO.getAllOrderByFrom());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(allWorkers.size()+" stored in "+App.FILE_NAME);


    }

}
