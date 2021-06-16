package entites;

/**
 * Entite Ville
 */
public class Ville {
    private int id;
    private String codeCommune;
    private String nomCommune;
    private int population;
    private int idRegion;
    private int idDepart;

    /**
     * Constructeur de la classe Ville
     * @param id
     * @param codeCommune
     * @param nomCommune
     * @param population
     * @param idRegion
     * @param idDepart
     */

    public Ville(int id, String codeCommune, String nomCommune, int population, int idRegion, int idDepart) {
        this.id = id;
        this.codeCommune = codeCommune;
        this.nomCommune = nomCommune;
        this.population = population;
        this.idRegion = idRegion;
        this.idDepart = idDepart;
    }

    /**
     * Getters et Setters
     * @return id, codeCommune, nomCommune, population, idRegion, idDepart
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeCommune() {
        return codeCommune;
    }

    public void setCodeCommune(String codeCommune) {
        this.codeCommune = codeCommune;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIdDepart() {
        return idDepart;
    }

    public void setIdDepart(int idDepart) {
        this.idDepart = idDepart;
    }
}
