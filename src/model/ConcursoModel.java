package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

    public int insertarConcurso(Concurso obj) {

        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {

            // 1. Conexión a la BD
            conn = MySqlDBConexion.getConexion();

            // 2. SQL
            String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";

            pstm = conn.prepareStatement(sql);

            // 3. Pasar valores
            pstm.setString(1, obj.getNombre());
            pstm.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
            pstm.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
            pstm.setString(4, obj.getEstado());

            // 4. Ejecutar
            salida = pstm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return salida;
    }
}