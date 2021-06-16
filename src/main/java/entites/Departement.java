package entites;

import dao.RegionJdbcDao;

/**
 * Classe de l'entité Département
 */
public class Departement {
    private int id;
    private String codeDepart;
    private int idRegion;

    /**
     * Constructeur de la classe departement
     * @param id
     * @param codeDepart
     * @param region
     */
    public Departement(int id, String codeDepart, int idRegion) {
        this.id = id;
        this.codeDepart = codeDepart;
        this.idRegion = idRegion;
    }

    /**
     * Getters et Setters
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeDepart() {
        return codeDepart;
    }

    public void setCodeDepart(String codeDepart) {
        this.codeDepart = codeDepart;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}
