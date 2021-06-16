package dao;

import entites.Region;
import org.mariadb.jdbc.Driver;
import utils.ConfiguartionsDatabase;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class RegionJdbcDao implements RegionDao {

    //Connection données d'accès de la BDD

    static final Database DB_CONFIG = ConfiguartionsDatabase.extractConfigs();

    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Region region = null;
    int nb = 0;

    /**
     * methode qui retourne la liste de toutes les regions
     * @return Region
     */
    @Override
    public List<Region> extraire() {
        List<Region> listeRegion = new ArrayList<>();

        try {
            //On crée la connection avec le Classe Driver

            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            //On prepare la requete SQL à exécuter
            pstmt = connection.prepareStatement("SELECT * FROM REGION");
            rs = pstmt.executeQuery();
            //extraire la liste des elements
            while (rs.next()) {
                int id = rs.getInt("id_region");
                String codeRegion = rs.getString("codeRegion");
                String nomRegion = rs.getString("nomRegion");
                //int population = rs.getInt("population");


                //Rajouter les nouvelles villes selectionnées sur la liste des villes
                Region region = new Region(codeRegion, nomRegion);
                listeRegion.add(region);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            ;
        } finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
            // ResultSet, Preparestatement, connection
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }
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
        for (Region region:listeRegion
        ) {
            System.out.println(region);
        }
        return listeRegion;
    }

    /**
     * methode qui permet d'extraire les regions par nom
     * @return nomRegion
     */
    @Override
    public Region extraireParNom(String nomRegion) {

        try {
            //On crée la connection avec le Classe Driver


            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            //On prepare la requete SQL à exécuter
            pstmt = connection.prepareStatement("SELECT * FROM REGION WHERE nomRegion = ?");
            pstmt.setString(1,nomRegion);
            rs = pstmt.executeQuery();
            //extraire la liste des elements
            while (rs.next()) {
                int id = rs.getInt("idRegion");
                String codeRegion = rs.getString("codeRegion");

                region = new Region(id,codeRegion,nomRegion);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            ;
        } finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
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

        return region;

    }

    /**
     * Methode qui permet d'inserer une nouvelle region dans la table region
     * @param region
     */
    @Override
    public void insert(Region region) {
        try {
            //On crée la connection avec le Classe Driver

            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            //On prepare la requete SQL à exécuter
            pstmt= connection.prepareStatement("INSERT INTO REGION (id_region, codeRegion, nomRegion) VALUES(?,?,?)");
            pstmt.setInt(1,region.getId());
            pstmt.setString(2, region.getCodeRegion());
            pstmt.setString(3, region.getNomRegion());
            nb = pstmt.executeUpdate();

            if(nb != 0){
                System.out.println(nb + " L'insertion s'est réalisée avec succès!");
            }
            //extraire la liste des elements
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally{ // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
            // Preparestatement, connection

            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }

    }

    /**
     * Methode qui permet de modifier une ville dans la liste des villes
     * @param ancienNom
     * @param nouveauNom
     * @return
     */
    @Override
    public int update(String ancienNom, String nouveauNom) {
        try {
            connection = DriverManager.getConnection(DB_CONFIG.getUrl(),DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            //On prepare la requete SQL à exécuter
            pstmt = connection.prepareStatement("UPDATE REGION SET nom = ? WHERE nom = ?");
            pstmt.setString(1,nouveauNom);
            pstmt.setString(2, ancienNom);
            pstmt.executeUpdate();

            if(nb != 0){
                System.out.println(nb + " nombre de ligne a été mis à jour !");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:'

            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return nb;
    }

    /**
     * methode peremettant la suppression d'une région donnée
     * @param region
     * @return
     */
    @Override
    public boolean delete(Region region) {

        try {
            connection = DriverManager.getConnection(DB_CONFIG.getUrl(),DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            //On prepare la requete SQL à exécuter
            pstmt = connection.prepareStatement("DELETE FROM REGION WHERE codeRegion = ? ");
            //on va identifier la region que'on veut supprimer
            //String nom = region.getNom();
            String codeRegion = region.getCodeRegion();
            //pstmt.setString(1,nom);
            pstmt.setString(1, codeRegion);
            nb = pstmt.executeUpdate();
            if(nb != 0){
                System.out.println( nb + " nombre de ligne(s) supprimée(s) ");
                return true;
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:'

            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return false;
    }
}
