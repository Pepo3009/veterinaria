package veterinaria;

import java.sql.*;
import javax.swing.JOptionPane;

public class DB {
     //Método
    public Connection conectarDB() {
        //Variables
        String database = "jdbc:postgresql://localhost:5432/EXAMEN";
        String user = "postgres";
        String password = "Pepo3009";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(database, user, password);
            
            //JOptionPane.showMessageDialog(null, "Conexión satisfactoria a la base de datos!");
            
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return conexion;
    }
}
