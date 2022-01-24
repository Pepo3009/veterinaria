package veterinaria;

import java.awt.print.PrinterException;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class me_cliente {
Connection db = null;
Statement st = null;
ResultSet rs = null;
PreparedStatement pst=null;
String id, nom,ape,cor,dire ;
Integer can,cedu,tele ;
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
public void inserta_cliente() throws SQLException {
if (a==false) { conecciondb();
//ingreso de variables
id=cliente.Id.getText();
cedu=Integer.valueOf(cliente.cedulausuario.getText());
nom=cliente.nombreusuario.getText();
ape=cliente.apellidousuario.getText();      
tele=Integer.valueOf(cliente.telefonousuario.getText());
dire=cliente.direccionusuario.getText(); 
cor=cliente.correousuario.getText();
can=Integer.valueOf(cliente.cant.getText());
//consulta sql en postgres
 sql = "insert into cliente (id_cliente,cedula_cliente,nombre_cliente,apellido_cliente,telefono_cliente,direccion_cliente,correo_cliente,cantidad_mascotas) values (?,?,?,?,?,?,?,?)";//corregir
 pst = db.prepareStatement(sql);
 //donde se muetra la insercion 
 pst.setString(1,id);
 pst.setInt(2,cedu);
 pst.setString(3,nom);
 pst.setString(4,ape);
 pst.setInt(5,tele);
 pst.setString(6,dire);
 pst.setString(7,cor);
 pst.setInt(8,can);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Guardo..."); } }

//Actualiza tabla cliente

public void actuali_cliente() throws SQLException {
if (a==true) { conecciondb();
id=cliente.Id.getText();
nom=cliente.nombreusuario.getText();
ape=cliente.apellidousuario.getText();      
tele=Integer.valueOf(cliente.telefonousuario.getText());
dire=cliente.direccionusuario.getText(); 
cor=cliente.correousuario.getText();
can=Integer.valueOf(cliente.cant.getText());
 sql = "update cliente set nombre_cliente=?, apellido_cliente=?, telefono_cliente=?,direccion_cliente=?,correo_cliente=?,cantidad_mascotas=?  where id_cliente='"+id+"'";//corregir
 pst = db.prepareStatement(sql);
 
 pst.setString(1,nom);
 pst.setString(2,ape);
 pst.setInt(3,tele);
 pst.setString(4,dire);
 pst.setString(5,cor);
 pst.setInt(6,can);
 pst.executeUpdate();
 JOptionPane.showMessageDialog(null,"Se Actualizo...");
}
}

//Consulta tabla cliente si existe cliente

public void consulta_cliente() throws SQLException {
 conecciondb();
 st = db.createStatement();
 id=cliente.Id.getText();
 rs = st.executeQuery("select * from cliente where id_cliente='"+id+"'");
 if (rs.next()) {a=true;

  cliente.cedulausuario.setText(rs.getString(2));
  cliente.nombreusuario.setText(rs.getString(3));
  cliente.apellidousuario.setText(rs.getString(4));
  cliente.telefonousuario.setText(rs.getString(5));
  cliente.direccionusuario.setText(rs.getString(6));
  cliente.correousuario.setText(rs.getString(7));
  cliente.cant.setText(rs.getString(8));
 }
 else {JOptionPane.showMessageDialog(null,"No Existe...");a=false;} }

//Elimina tabla cliente si existe cliente y no está en otra tabla relacionada

public void elimina_cliente() throws SQLException { //corregir
 try {
 if (a==true) { conecciondb();
 int resp = JOptionPane.showConfirmDialog(null, "Lo elimina","ALERTA",JOptionPane.YES_NO_OPTION);
 if (resp!=1) {st.execute("delete from cliente where id_cliente='"+id+"'");
   JOptionPane.showMessageDialog(null,"SE ELIMINO, ya que no tiene relación tabla"); } } 
    }
 catch (SQLException e) {JOptionPane.showMessageDialog(null,"No se puede eliminar, tiene relación tabla");} }

//Limpiar datos entrada de cliente

public void limpia() { //corregirç
cliente.Id.setText("");
cliente.cedulausuario.setText("");
cliente.nombreusuario.setText("");
cliente.apellidousuario.setText("");
cliente.telefonousuario.setText("");
cliente.direccionusuario.setText("");
cliente.correousuario.setText("");
cliente.cant.setText("");
}    
}