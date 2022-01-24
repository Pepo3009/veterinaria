package veterinaria;

import java.awt.print.PrinterException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class me_medicamento {
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;
String nom,deta ;
Integer id,precio ;
String sql;
Boolean a;
DefaultTableModel m = new DefaultTableModel();

//Conexion base datos  

public void conecciondb() {
  try {
      db=DriverManager.getConnection("jdbc:postgresql://localhost:5432/EXAMEN","postgres","Pepo3009"); 
          //Acualize ruta .../basedatos,usuario,contraseña
      } catch (SQLException e) {System.out.println("Ocurrio un error : "+e.getMessage());} }

//Ingreso (unico) tabla medicamento
public void inserta_medicamento() throws SQLException {
if (a==false) { conecciondb();
//ingreso de variables
id=Integer.valueOf(medicamento.id.getText());
nom=medicamento.nom.getText();
deta=medicamento.deta.getText();      
precio=Integer.valueOf(medicamento.pre.getText());
//consulta sql en postgres
 sql = "insert into medicamento (id_medicamento,nombre_medicamento,detalle_medicamento,precio_medicamento) values (?,?,?,?)";
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setInt(1,id);
 pst.setString(2,nom);
 pst.setString(3,deta);
 pst.setInt(4,precio);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Guardo..."); } }

//Actualiza tabla medicamento

public void actuali_medicamento() throws SQLException {
if (a==true) { conecciondb();
nom=medicamento.nom.getText();
deta=medicamento.deta.getText();      
precio=Integer.valueOf(medicamento.pre.getText());
 sql = "update medicamento set nombre_medicamento=?, detalle_medicamento=?, precio_medicamento=? where id_medicamento='"+id+"'";
 pst = db.prepareStatement(sql);
 pst.setString(1,nom);
 pst.setString(2,deta);
 pst.setInt(3,precio);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}

//Consulta tabla medicamento si existe medicamento

public void consulta_medicamento() throws SQLException {
 conecciondb();
 st = db.createStatement();
 id=Integer.valueOf(medicamento.id.getText());
 rs = st.executeQuery("select * from medicamento where id_medicamento='"+id+"'");
 if (rs.next()) {a=true;
  medicamento.nom.setText(rs.getString(2));
  medicamento.deta.setText(rs.getString(3));
  medicamento.pre.setText(rs.getString(4));
 }
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }

//Elimina tabla medicamento si existe medicamento y no está en otra tabla relacionada

public void elimina_medicamento() throws SQLException {
 try {
 if (a==true) { conecciondb();
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
 if (resp!=1) {
     st.execute("delete from medicamento where id_medicamento='"+id+"'");
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos entrada de medicamento

public void limpia() {
medicamento.id.setText("");
medicamento.nom.setText("");
medicamento.deta.setText("");
medicamento.pre.setText("");

}
}