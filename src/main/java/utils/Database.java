package utils;

public class Database {
    private String url;
    private String user;
    private String password;

    /**
     * Constructeur  de la classe Database
     * @param url
     * @param user
     * @param password
     */

    public Database (String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Getters pour recuperer les attributs de la Database
     * @return
     */
    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
