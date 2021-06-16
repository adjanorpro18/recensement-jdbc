package dao;

import entites.Ville;
import utils.ConfiguartionsDatabase;
import utils.Database;

import java.sql.*;
import java.util.List;

/**
 * Classe qui implémente les methodes de l'interface VilleDao
 */

public class VilleJdbcDao implements VilleDao{

    //Connection données d'accès de la BDD

    static final Database DB_CONFIG = ConfiguartionsDatabase.extractConfigs();

    Ville ville = null;
    PreparedStatement pstmt = null;
    Connection connection = null;
    ResultSet rs = null;
    int nb = 0;





    @Override
    public List<Ville> extraire() {
        return null;
    }

    /**
     * Methode permettant d'extraire une ville par son nomCommune et codeCommune
     * @param numeroCommune
     * @param codeCommune
     * @return
     */

    @Override
    public Ville extraireCodeCommune(String codeCommune, int idDepart) {

        try {
            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            pstmt = connection.prepareStatement("SELECT * FROM VILLE WHERE codeCommune = ? AND idDepart = ? ");
            pstmt.setString(1,codeCommune);
            pstmt.setInt(2, idDepart);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("idVille");
                //String code = rs.getString("codeCommune");
                String nom = rs.getString("nomCommune");
                int population = rs.getInt("population");
                //int idDepart = rs.getInt("idDepart");
                int idRegion = rs.getInt("idRegion");

                ville = new Ville(id,codeCommune, nom, population, idDepart, idRegion);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
            // ResultSet, Preparestatement, connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return ville;
    }

    /**
     * Methode qui permet d'inserer une ville dans la table Ville
     * @param ville
     */

    @Override
    public void insert(Ville ville) {

        try {
            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            pstmt = connection.prepareStatement("INSERT INTO VILLE (codeCommune, nomCommune, population, idDepart, idRegion) VALUES (?,?,?,?,?) ");
            //pstmt.setInt(1, ville.getId());
            pstmt.setString(1, ville.getCodeCommune());
            pstmt.setString(2, ville.getNomCommune());
            pstmt.setInt(3, ville.getPopulation());
            pstmt.setInt(4, ville.getIdDepart());
            pstmt.setInt(5, ville.getIdRegion());
            nb = pstmt.executeUpdate();

            if( nb != 0){
                System.out.println(ville.getCodeCommune() + " : " +  ville.getNomCommune());
            }

            System.out.println(ville.getCodeCommune() + " : " +  ville.getNomCommune());


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
            //Preparestatement, connection
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
            }
        }


    }

    @Override
    public int update(String ancienVille, String nouvelleVille) {
        return 0;
    }

    @Override
    public boolean delete(Ville ville) {
        return false;
    }
}
