package entites;

/**
 * Classe de l'entité region
 */
public class Region {
    private int id;
    private String codeRegion;
    private String nomRegion;


    /**
     *
     * @param id
     * @param nomRegion
     * @param codeRegion
     */
    public Region(int id,String codeRegion,String nomRegion) {
        this.id = id;
        this.codeRegion = codeRegion;
        this.nomRegion = nomRegion;

    }

    public Region(String codeRegion, String nomRegion) {
        this.codeRegion = codeRegion;
        this.nomRegion = nomRegion;
    }

    public int getId() {
        return id;
    }

    /**
     * Getters et Setters
     * @return
     */


    public String getCodeRegion() {
        return codeRegion;
    }

    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }
}
