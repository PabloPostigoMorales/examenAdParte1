package cesur.examen.core.worker;

import cesur.examen.core.common.DAO;
import cesur.examen.core.common.JDBCUtils;
import lombok.extern.java.Log;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */
@Log public class WorkerDAO implements DAO<Worker> {
    private Connection connection;

    public WorkerDAO(Connection c){
        connection = c;
    }



    /* Please, use this constants for the queries */
    private final String QUERY_ORDER_BY = "SELECT * from TRABAJADOR ORDER BY desde";
    private final String QUERY_BY_DNI = "Select * from trabajador where dni=?";
    private final String UPDATE_BY_ID = "UPDATE trabajadores SET nombre = ?, dni = ?, desde = ? WHERE dni = ?";
    private final String QUERY_ADD_WORKER = "INSERT INTO trabajador(nombre,dni,desde) VALUES(?,?,?)";
    private final String QUERY_GET_ALL ="Select * from trabajador";

    @Override
    public Worker save(Worker worker) {

        Worker trabajador = new Worker();

        /**
         * Update Worker in database.
         * Remember that date objects in jdbc should be converted to sql.Date type.
         * @param worker
         * @return the updated worker or null if the worker does not exist in database.
         */
        try (PreparedStatement ps = connection.prepareStatement(QUERY_ADD_WORKER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1,worker.getName());
            ps.setString(2, worker.getDni());
            ps.setDate(3,JDBCUtils.dateUtilToSQL(worker.getFrom()));

            int i = ps.executeUpdate();
            if (i>0){
                log.info("Se han guardado los cambios");
            }else{
                log.info("No se han podido guardar los cambios");
            }
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                Long id = rs.getLong(1);
                worker.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return worker;
    }
    @Override
    public Worker update(Worker worker) {
        Worker out = null;

        try(PreparedStatement ps = connection.prepareStatement(QUERY_BY_DNI)){
            ps.setString(1, worker.getName());
            ps.setString(2, worker.getDni());
            ps.setDate(3, JDBCUtils.dateUtilToSQL( worker.getFrom()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return out;
    }

    @Override
    public boolean remove(Worker worker) {
        return false;
    }

    @Override
    public Worker get(Long id) {
        return null;
    }

    /**
     * Retrieve the worker that has this dni. Null if there is no wrker.
     * @param dni
     * @return
     */
    public Worker getWorkerByDNI(String dni) {

        /* Implemented for your pleasure */

        if( JDBCUtils.getConn()==null){
            throw new RuntimeException("Connection is not created!");
        }

        Worker out = null;

        try( PreparedStatement st = JDBCUtils.getConn().prepareStatement(QUERY_BY_DNI) ){
            st.setString(1,dni);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Worker w = new Worker();
                w.setId( rs.getLong("id") );
                w.setName( rs.getString("nombre"));
                w.setDni( rs.getString("dni"));
                w.setFrom( rs.getDate("desde"));
                out=w;
            }
        } catch (SQLException e) {
            log.severe("Error in getWorkerById()");
            throw new RuntimeException(e);
        }
        return out;
    }

    @Override
    public List<Worker> getAll() {
        ArrayList<Worker>workers = new ArrayList<>(0);
        try(PreparedStatement ps = connection.prepareStatement(QUERY_GET_ALL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Worker worker = new Worker();
                worker.setId(rs.getLong("id"));
                worker.setName(rs.getString("nombre"));
                worker.setDni(rs.getString("dni"));
                worker.setFrom(rs.getDate("desde"));
                workers.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return workers;


    }

    /**
     * Get a list with all workers, ordered by from column.
     * The first is the oldest worker and the last are the newest.
     * If there is no worker, the list is empty.
     * @return
     */
    public List<Worker> getAllOrderByFrom(){
        ArrayList<Worker> out = new ArrayList<>(0);
        try(PreparedStatement ps = connection.prepareStatement(QUERY_ORDER_BY)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Worker worker = new Worker();
                worker.setId(rs.getLong("id"));
                worker.setName(rs.getString("nombre"));
                worker.setDni(rs.getString("dni"));
                worker.setFrom(rs.getDate("desde"));
                out.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }
}
