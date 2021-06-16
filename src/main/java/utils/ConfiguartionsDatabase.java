package utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Iterator;

public class ConfiguartionsDatabase {

    public static Database extractConfigs(){

        Configurations configs = new Configurations();

        try {
            Configuration config = configs.properties("database.properties");
            String databaseUrl = config.getString("database.url");
            String databaseUser = config.getString("database.user");
            String databasePassword = config.getString("database.password");

            return new Database(databaseUrl, databaseUser, databasePassword);

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }





}
