

package veterinaria;

import java.awt.print.PrinterException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class me_veterrinaria {
    Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;
String nom,dire ;
Integer id,tele ;
String sql;
Boolean a;
DefaultTableModel m = new DefaultTableModel();

//Conexion base datos  

public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/EXAMEN","postgres","Pepo3009"); 
          //Acualize ruta .../basedatos,usuario,contraseña
      } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} }

//Ingreso (unico) tabla cliente
public void inserta_veterrinaria() throws SQLException {
if (a==false) { conecciondb();
//ingreso de variables
id=Integer.valueOf(veterinaria.id.getText());
nom=veterinaria.nom.getText();
tele=Integer.valueOf(veterinaria.tel.getText());
dire=veterinaria.dir.getText(); 
//consulta sql en postgres
 sql = "insert into veterinaria (id_veterinaria,nombre_veterinaria,telefono_veterinaria,direccion_veterinaria) values (?,?,?,?)";//corregir
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setInt(1,id);
 pst.setString(2,nom);
 pst.setInt(3,tele);
 pst.setString(4,dire);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Guardo..."); } }

//Actualiza tabla cliente

public void actuali_veterrinaria() throws SQLException {
if (a==true) { conecciondb();
nom=veterinaria.nom.getText();
tele=Integer.valueOf(veterinaria.tel.getText());
dire=veterinaria.dir.getText(); 
 sql = "update veterinaria set nombre_veterinaria=?, telefono_veterinaria=?, direccion_veterinaria=? where id_veterinaria='"+id+"'";//corregir
 pst = db.prepareStatement(sql);
 pst.setString(1,nom);
 pst.setInt(2,tele);
 pst.setString(3,dire);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}



//Consulta tabla cliente si existe cliente

public void consulta_veterrinaria() throws SQLException {
 conecciondb();
 st = db.createStatement();
 id=Integer.valueOf(veterinaria.id.getText());
 rs = st.executeQuery("select * from veterinaria where id_veterinaria='"+id+"'");//corregir
 if (rs.next()) {a=true;
 
  veterinaria.nom.setText(rs.getString(2));
  veterinaria.tel.setText(rs.getString(3));
  veterinaria.dir.setText(rs.getString(4));

 }
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }



//Elimina tabla cliente si existe cliente y no está en otra tabla relacionada

public void elimina_veterrinaria() throws SQLException { //corregir
 try {
 if (a==true) { conecciondb();
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
 if (resp!=1) {st.execute("delete from veterinaria where id_veterinaria='"+id+"'");
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos entrada de cliente

public void limpia() { //corregir
veterinaria.id.setText("");
veterinaria.nom.setText("");
veterinaria.tel.setText("");
veterinaria.dir.setText("");

}    
}
