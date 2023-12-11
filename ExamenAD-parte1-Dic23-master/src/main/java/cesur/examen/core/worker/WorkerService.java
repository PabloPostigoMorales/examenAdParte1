package cesur.examen.core.worker;

import cesur.examen.core.common.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import lombok.extern.java.Log;
import java.util.Date;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno: Pablo Postigo Morales
 * Fecha: 11/12/2003
 *
 *  No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 *  En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 *  o para seguir la traza de ejecución.
 */
/**
 *  Services classes offers methods to our main application, and can
 *  use multiple methods from DAOs and Entities.
 *  It's a layer between Data Access Layer and Aplication Layer (where
 *  Main app and controllers lives)
 *  It helps to encapsulate multiple opperations with DAOs that can be
 *  reused in application layer.
 */
@Log public class WorkerService {
    private static Connection connection;
    public  WorkerService(Connection c){connection = c;}
    private static final String SQL_RENOVATE="UPDATE trabajador SET desde = ? WHERE dni = ?";
    /*
    RenovateWorker() set "from" date of the worker with this dni to today's date.
    Remember Date().
    Returns the new updated worker, null if fails or dni doesn't exist.
    */
    public static Worker renovateWorker(String dni){
        Worker worker = new Worker();
       try(PreparedStatement ps = connection.prepareStatement(SQL_RENOVATE)){
           Date d = new Date();
           ps.setDate(1, JDBCUtils.dateUtilToSQL(d));
           ps.setString(2,dni);
           int i = ps.executeUpdate();
           if (i>0){
               log.info("Se ha realizado el cambio con exito");
           }else {
               log.info("No se ha podido realizar el cambio");
           }

        } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        /* Make implementation here ...  */

        return worker;
    }
}
