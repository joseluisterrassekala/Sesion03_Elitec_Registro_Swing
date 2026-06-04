package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Concurso;
import util.MySqlDBConexion;

public class ConcursoModel {

    public int insertarConcurso(Concurso obj) {

        int salida = -1;

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "INSERT INTO concurso (nombre, fechaInicio, fechaFin, estado) VALUES (?,?,?,?)";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, obj.getNombre());
            pstm.setDate(2, java.sql.Date.valueOf(obj.getFechaInicio()));
            pstm.setDate(3, java.sql.Date.valueOf(obj.getFechaFin()));
            pstm.setString(4, obj.getEstado());

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

    // 🔥 MÉTODO CLAVE (CONSULTA)
    public List<Concurso> listaConcurso(String nombre, String estado, LocalDate desde, LocalDate hasta) {

        ArrayList<Concurso> lista = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySqlDBConexion.getConexion();

            String sql = "SELECT * FROM concurso WHERE "
                    + " nombre LIKE ? AND "
                    + " ( ? = '' OR estado = ? ) AND "
                    + " ( ? = '9999-01-01' OR fechaInicio >= ? ) AND "
                    + " ( ? = '9999-01-01' OR fechaFin <= ? ) ";

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, "%" + nombre + "%");

            pstm.setString(2, estado);
            pstm.setString(3, estado);

            pstm.setDate(4, java.sql.Date.valueOf(desde));
            pstm.setDate(5, java.sql.Date.valueOf(desde));

            pstm.setDate(6, java.sql.Date.valueOf(hasta));
            pstm.setDate(7, java.sql.Date.valueOf(hasta));

            // 🔍 DEBUG
            System.out.println("SQL: " + pstm.toString());

            rs = pstm.executeQuery();

            while (rs.next()) {
                Concurso c = new Concurso();

                c.setIdConcurso(rs.getInt("idConcurso"));
                c.setNombre(rs.getString("nombre"));
                c.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                c.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                c.setEstado(rs.getString("estado"));

                lista.add(c);
            }

            // 🔥 IMPORTANTE PARA VER SI FUNCIONA
            System.out.println("Cantidad de resultados: " + lista.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return lista;
    }
}