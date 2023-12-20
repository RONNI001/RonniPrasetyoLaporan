/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Mahasiswa;
import java.util.List;

/**
 *
 * @author User
 */
public interface IDAOMahasiswa {

    public List <Mahasiswa> getAll();
    
    public void insert(Mahasiswa b);
    
    public void update(Mahasiswa b);
    
    public void delete(String nim);
    
}
