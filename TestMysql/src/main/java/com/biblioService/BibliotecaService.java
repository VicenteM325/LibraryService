package com.biblioService;

import com.mysqlConnection.ConexionMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BibliotecaService {
    public void prestarLibros(int usuarioId,int libroId) throws SQLException {
        String verificarDisponibilidad = "SELECT disponible FROM libros WHERE id = ?";
        String actualizarDisponibilidad = "UPDATE libros SET disponible = FALSE WHERE id = ?";
        String registrarPrestamo = "INSERT INTO prestamos VALUES(usuario_id,libro_id) VALUES(?,?)";

        try(Connection conn = ConexionMysql.conectar()){
            conn.setAutoCommit(false);

        try (PreparedStatement stmtVerificar = conn.prepareStatement(verificarDisponibilidad)) {
                stmtVerificar.setInt(1, libroId);
                ResultSet rs = stmtVerificar.executeQuery();

                if(rs.next() && rs.getBoolean("disponible")){
                    try(PreparedStatement stmtActualizar = conn.prepareStatement(actualizarDisponibilidad);
                    PreparedStatement stmtRegistrar = conn.prepareStatement(registrarPrestamo)){
                        stmtActualizar.setInt(1, libroId);
                        stmtActualizar.executeUpdate();

                        stmtRegistrar.setInt(1,usuarioId);
                        stmtRegistrar.setInt(2, libroId);

                        conn.commit();
                        System.out.println("Prestamo realizado con exito");
                    }
                } else {
                    System.out.println("El libro no esta disponible");
                }
                } catch (SQLException e){
                    conn.rollback();
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }
}
