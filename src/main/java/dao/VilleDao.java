package dao;

import entites.Departement;
import entites.Ville;

import java.util.List;

/**
 * Interface Dao de la classe Ville
 */

public interface VilleDao {

    List<Ville> extraire();

    public Ville extraireCodeCommune( String codeCommune, int idDepart);

    void insert(Ville ville);

    int update(String ancienVille, String nouvelleVille);

    boolean delete(Ville ville);

}
