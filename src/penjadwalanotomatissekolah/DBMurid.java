/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penjadwalanotomatissekolah;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Acer
 */
public class DBMurid {
    private MuridModel dt=new MuridModel();    
    public MuridModel getMuridModel(){ return(dt);}
    public void setMuridModel(MuridModel s){ dt=s;}
    
    public ObservableList<MuridModel>  Load() {
        try {
            ObservableList<MuridModel> 
                    tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery
            ("Select idmurid, namamurid, kelas, umur, gambar from murid");
            
            int i = 1;
            while (rs.next()) {
                MuridModel d=new MuridModel();
                d.setIdmurid(rs.getString("idmurid"));                
                d.setNamamurid(rs.getString("namamurid"));
                d.setKelas(rs.getString("kelas"));                
                d.setUmur(rs.getInt("umur"));
                d.setGambar(rs.getString("gambar"));
                
                String idmurid = rs.getString("idmurid");
                char kode = idmurid.charAt(0);
                String jeniskelamin;
                
                if (kode == 'P') jeniskelamin = "Perempuan";
                else if (kode == 'L') jeniskelamin = "Laki-Laki";
                else jeniskelamin = "-";
                d.setJeniskelamin(jeniskelamin);
                
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from murid where idmurid = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
    public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into murid (idmurid, namamurid, kelas, umur, gambar) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getMuridModel().getIdmurid());           
            con.preparedStatement.setString(2, getMuridModel().getNamamurid());
            con.preparedStatement.setString(3, getMuridModel().getKelas());           
            con.preparedStatement.setInt(4, getMuridModel().getUmur());
            con.preparedStatement.setString(5, getMuridModel().getGambar());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {           
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from murid where idmurid  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update murid set namamurid = ?, kelas = ?, umur = ?, gambar = ?  where  idmurid = ? ");
            con.preparedStatement.setString(1, getMuridModel().getNamamurid());
            con.preparedStatement.setString(2, getMuridModel().getKelas());
            con.preparedStatement.setInt(3, getMuridModel().getUmur());
            con.preparedStatement.setString(4, getMuridModel().getGambar());
            con.preparedStatement.setString(5, getMuridModel().getIdmurid());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {            
            con.tutupKoneksi();
            return berhasil;
        }    
    }
}
