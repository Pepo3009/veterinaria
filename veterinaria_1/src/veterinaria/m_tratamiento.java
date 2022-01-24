package veterinaria;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDialog; 

public class m_tratamiento {
    
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;

String id_m,id_t;
java.sql.Date fecha;

Boolean a;
String sql;

DefaultTableModel m = new DefaultTableModel();

//Conexion base datos  
DB DB = new DB();
Connection conexion =DB.conectarDB();
     
public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/EXAMEN","postgres","Pepo3009");    
     //Acualize ruta .../basedatos,usuario,contraseña
     } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());}
}
    
    //Ingreso tabla mantenimiento
public void inserta_tratamiento() throws SQLException {
    //conexion a la base de dato 
 conecciondb();
 // funcion de llamar a la transaccion
 try{
     // ingreso de datos 
     id_t=tratamiento.tratamiento.getText();
     id_m=tratamiento.mascota.getText();
     fecha = new java.sql.Date(tratamiento.fecha.getDate().getTime());
     //consulta sql en postgres
     sql ="select transaccion('"+id_t+"','"+id_m+"','"+fecha+"');" ;
      PreparedStatement pstmt = conexion.prepareStatement(sql);
      rs = pstmt.executeQuery();
      //mensaje de confirmacion
      JOptionPane.showMessageDialog(null,"Se Guardo...");
     System.out.println(rs);
    }catch(SQLException e){
        // mensaje de error que se mostrara como alert
         JOptionPane alerta = new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
         JDialog dialog = alerta.createDialog("Warning!");
         dialog.setAlwaysOnTop(true); // to show top of all other application
         dialog.setVisible(true);          
    }
}
}