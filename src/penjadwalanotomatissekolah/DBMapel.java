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
public class DBMapel {
    private MapelModel dt=new MapelModel();
    public MapelModel getMapelModel(){ return(dt);}
    public void setMapeldModel(MapelModel s){ dt=s;}
    
    public ObservableList<MapelModel>  Load() {
        try {
            ObservableList<MapelModel> 
                    tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery
            ("Select idmapel, namamapel, pengajar, waktumulai, waktuselesai from mapel");
            
            int i = 1;
            while (rs.next()) {
                MapelModel d=new MapelModel();
                d.setIdmapel(rs.getString("idmapel"));                
                d.setNamamapel(rs.getString("namamapel"));
                d.setPengajar(rs.getString("pengajar"));                
                d.setWaktumulai(rs.getString("waktumulai"));
                d.setWaktuselesai(rs.getString("waktuselesai"));
                
                String idmapel = rs.getString("idmapel");
                char kode = idmapel.charAt(0);
                String hari;
                
                if (kode == 'F') hari = "Sabtu";
                else if (kode == 'E') hari = "Jumat";
                else if (kode == 'D') hari = "Kamis";
                else if(kode == 'C') hari = "Rabu";
                else if(kode == 'B') hari = "Selasa";
                else if(kode == 'A') hari = "Senin";
                else hari = "-";
                d.setHari(hari);
                
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from mapel where idmapel = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into mapel (idmapel, namamapel, pengajar, waktumulai, waktuselesai) values (?,?,?,?,?)");
            con.preparedStatement.setString(1, getMapelModel().getIdmapel());           
            con.preparedStatement.setString(2, getMapelModel().getNamamapel());
            con.preparedStatement.setString(3, getMapelModel().getPengajar());           
            con.preparedStatement.setString(4, getMapelModel().getWaktumulai());
            con.preparedStatement.setString(5, getMapelModel().getWaktuselesai());
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from mapel where idmapel  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update mapel set namamapel = ?, pengajar = ?, waktumulai = ?, waktuselesai = ?  where  idmapel = ? ");
            con.preparedStatement.setString(1, getMapelModel().getNamamapel());
            con.preparedStatement.setString(2, getMapelModel().getPengajar());           
            con.preparedStatement.setString(3, getMapelModel().getWaktumulai());
            con.preparedStatement.setString(4, getMapelModel().getWaktuselesai());
            con.preparedStatement.setString(5, getMapelModel().getIdmapel());
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
