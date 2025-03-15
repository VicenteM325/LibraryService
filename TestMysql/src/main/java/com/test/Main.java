package com.test;

import com.biblioService.BibliotecaService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BibliotecaService service = new BibliotecaService();

        //Prueba; Usuario 1 intenta prestar el libro 2
        service.prestarLibros(1,1);
    }
}