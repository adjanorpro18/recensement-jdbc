import dao.DepartementJdbcDao;
import dao.RegionJdbcDao;
import dao.VilleJdbcDao;
import entites.Departement;
import entites.Region;
import entites.Ville;
import org.mariadb.jdbc.Driver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class IntegrationRecensement {

    public static void main(String[] args) {

        //Rechargement du Driver pour la connection à la BDD: ceci une seule fois
        try {
            DriverManager.registerDriver(new Driver());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //Creation des DaoJdbc pour interagir avec des données en BDD

        RegionJdbcDao regionJdbcDao = new RegionJdbcDao(); // Region
        DepartementJdbcDao departementJdbcDao = new DepartementJdbcDao(); // Departement
        VilleJdbcDao villeJdbcDao = new VilleJdbcDao(); // Ville


        //Lecture du fichier de recensement
        Path paths = Paths.get("./recensement.csv");
        try {
            List<String> lignes = Files.readAllLines(paths, StandardCharsets.UTF_8);
            lignes.remove(0); // supprimer la premiere ligne du fichier
            // qui dipsose des informations dont on n'a pas besoin
            Iterator<String> iterator = lignes.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                String [] morceaux = line.split(";");
                //System.out.println(morceaux[9]);
                //Découper le fichier et prendre les elements qui nous interesse pour la region
                String codeRegion = morceaux[0];
                String nomRegion = morceaux[1];

                //System.out.println(codeRegion + "  : " + nomRegion);


                //Pour le département
                String codeDepart = morceaux[2];
               // System.out.println(codeDepart);

                //Pour la commune
                String codeCommune = morceaux[5];
                String nomCommune = morceaux[6];
                    //Population
                Integer population = Integer.parseInt(morceaux[9].replaceAll(" ", ""));

                //je crée la Région et l'insérer en BDD

                Region region = new Region(0,codeRegion, nomRegion);
                Region rgdb =  regionJdbcDao.extraireParNom(nomRegion);
                if(rgdb  == null){
                    regionJdbcDao.insert(region);
                    rgdb  = regionJdbcDao.extraireParNom(nomRegion);

                }

                //Je crée le Département et l'insérer en BDD

                Departement departement = new Departement(0, codeDepart, rgdb .getId());
                Departement dpdb = departementJdbcDao.extraireParNumero(codeDepart);
                if(dpdb == null){
                    departementJdbcDao.insert(departement);
                    dpdb = departementJdbcDao.extraireParNumero(codeDepart);

                }

                //Je crée la Ville et l'insérer en BDD

                Ville ville = new Ville(0, codeCommune, nomCommune, population, rgdb.getId(), dpdb.getId());
                Ville vldb = villeJdbcDao.extraireCodeCommune(codeCommune, dpdb.getId());
                if(vldb == null){
                    villeJdbcDao.insert(ville);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
