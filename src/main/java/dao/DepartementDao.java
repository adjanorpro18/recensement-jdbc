package dao;

import entites.Departement;

import java.util.List;

public interface DepartementDao {
    List<Departement> extraire();

    public Departement extraireParNumero(String nomRegion);

    void insert(Departement departement);

    int update(String ancienNom, String nouveauNom);

    boolean delete(Departement departement);

}
