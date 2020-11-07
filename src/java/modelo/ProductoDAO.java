/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author docencia
 */
public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    
    public Producto buscar(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where idProducto=" + id;
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()){
               p.setId(rs.getInt(1));
               p.setNombres(rs.getString(2));
               p.setFoto(rs.getBinaryStream(3));
               p.setDescripcion(rs.getString(4));
               p.setPrecio(rs.getDouble(5));
               p.setStock(rs.getInt(6));
               
           }
        }catch(Exception e) {
            
        }
        return p;
    }
    
    public int actualizarStock(int id, int stock) {
        String sql = "update producto set stock =? where idProducto = ?";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch(Exception ex) {
            
        }
        return r;
    }
    
    public Producto listarId(int id){
        String sql = "select * from producto where idProducto = " + id;
        Producto p = new Producto();
        
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()){
               p.setId(rs.getInt(1));
               p.setNombres(rs.getString(2));
               p.setFoto(rs.getBinaryStream(3));
               p.setDescripcion(rs.getString(4));
               p.setPrecio(rs.getDouble(5));
               p.setStock(rs.getInt(6));
               
           }
        }catch(Exception e) {
            
        }
        return p;
    }
    
    public List listar() {
        List<Producto>productos = new ArrayList<>();
        String sql = "select * from producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(); 
            while(rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
            
        } catch(Exception e){
            
        }
        return productos;
    }
    
    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from producto where idProducto = " + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            if(rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
                
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        }catch(Exception e) {
            
        }
        
    }
    
    public int agregar(Producto p){ 
        String sql="insert into producto(Nombres, Descripcion, Precio, Stock)values(?,?,?,?)";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
        
    }
    
    public int actualizar(Producto p){
        String sql="update producto set Nombres=?, Descripcion=?, Precio=?, Stock=? where idProducto=?";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getDescripcion());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getId());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return r;
    }
    public void delete(int id){
        String sql="delete from producto where IdProducto="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
}
