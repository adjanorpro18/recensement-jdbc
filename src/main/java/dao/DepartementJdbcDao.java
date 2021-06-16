package dao;

import entites.Departement;
import org.mariadb.jdbc.Driver;
import utils.ConfiguartionsDatabase;
import utils.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité Departement
 */
public class DepartementJdbcDao implements DepartementDao{


    //Connection données d'accès de la BDD

    static final Database  DB_CONFIG = ConfiguartionsDatabase.extractConfigs();

    Departement departement = null;
    Connection connection = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int nb = 0;


    @Override
    public List<Departement> extraire() {
        List<Departement> listeDepartement = new ArrayList<>();

        return listeDepartement;
    }

    @Override
    public Departement extraireParNumero(String codeDepart) {
        try {
            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            pstmt = connection.prepareStatement("SELECT * FROM DEPARTEMENT WHERE codeDepart = ? ");
            pstmt.setString(1,codeDepart);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idDepart");
                int regionId = rs.getInt("idRegion");

                departement = new Departement(id,codeDepart,regionId);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
        return departement;
    }

    @Override
    public void insert(Departement departement) {

        try {

            connection = DriverManager.getConnection(DB_CONFIG.getUrl(), DB_CONFIG.getUser(), DB_CONFIG.getPassword());
            pstmt = connection.prepareStatement("INSERT INTO DEPARTEMENT (idDepart,codeDepart,idRegion) VALUES (?,?,?)");
            pstmt.setInt(1, departement.getId());
            pstmt.setString(2, departement.getCodeDepart());
            pstmt.setInt(3,departement.getIdRegion());
            nb = pstmt.executeUpdate();
            if(nb != 0){
                System.out.println(nb + " L'insertion s'est réalisée avec succès!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally { // bloc de quoi qu'il arrive, de fermer les ressources utilisées:
            // Preparestatement, connection
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
    public int update(String ancienNom, String nouveauNom) {
        return 0;
    }

    @Override
    public boolean delete(Departement departement) {
        return false;
    }
}
