package dao;

import entites.Region;

import java.util.List;

/**
 * Classe RegionDao disposera des methodes de traitement de la classe region
 */
public interface RegionDao {

    List<Region> extraire();

    public Region extraireParNom(String nomRegion);

    void insert(Region region);

    int update(String ancienNom, String nouveauNom);

    boolean delete(Region region);



}
