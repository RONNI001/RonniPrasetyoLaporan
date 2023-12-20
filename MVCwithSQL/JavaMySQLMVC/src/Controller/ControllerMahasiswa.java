/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOMahasiswa;
import DAOInterface.IDAOMahasiswa;
import Model.Mahasiswa;
import Model.TabelModelMahasiswa;
import View.FormMhs;
import java.util.List;

/**
 *
 * @author User
 */
public class ControllerMahasiswa {
    FormMhs frmMahasiswa;
    IDAOMahasiswa iMahasiswa;
    List <Mahasiswa> lstMahasiswa;
    
    public ControllerMahasiswa(FormMhs frmMahasiswa){
        this.frmMahasiswa = frmMahasiswa;
        iMahasiswa = new DAOMahasiswa();
    }
    
    public void isiTable(){
        lstMahasiswa = iMahasiswa.getAll();
        TabelModelMahasiswa tabelMhs = new TabelModelMahasiswa(lstMahasiswa);
        frmMahasiswa.getTabelData().setModel(tabelMhs);
    }
    
    public void insert(){
        Mahasiswa b = new Mahasiswa();
        b.setNim(frmMahasiswa.gettxtNim().getText());
        b.setNama(frmMahasiswa.gettxtNama().getText());
        b.setKelas(frmMahasiswa.gettxtKelas().getText());
        b.setProdi(frmMahasiswa.gettxtProdi().getText());
        iMahasiswa.insert(b);
    }

    public void reset (){
     frmMahasiswa.gettxtNim().setText("");
     frmMahasiswa.gettxtNama().setText("");
     frmMahasiswa.gettxtKelas().setText("");
     frmMahasiswa.gettxtProdi().setText("");
    }
    
    public void isiField (int row){
        frmMahasiswa.gettxtNim().setText(lstMahasiswa.get(row).getNim().toString());
        frmMahasiswa.gettxtNama().setText(lstMahasiswa.get(row).getNama().toString());
        frmMahasiswa.gettxtKelas().setText(lstMahasiswa.get(row).getKelas().toString());
        frmMahasiswa.gettxtProdi().setText(lstMahasiswa.get(row).getProdi().toString());
    }
    
    public void update(){
        Mahasiswa b =new Mahasiswa();
        b.setNama(frmMahasiswa.gettxtNama().getText());
        b.setKelas(frmMahasiswa.gettxtKelas().getText());
        b.setProdi(frmMahasiswa.gettxtProdi().getText());
        b.setNim(frmMahasiswa.gettxtNim().getText());
        iMahasiswa.update(b);
    }
    
    public void delete(){
        Mahasiswa b = new Mahasiswa();
        b.setNim(frmMahasiswa.gettxtNim().getText());
        iMahasiswa.delete(frmMahasiswa.gettxtNim().getText());
    }
}