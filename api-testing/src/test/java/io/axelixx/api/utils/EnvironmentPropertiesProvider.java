package io.axelixx.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

public class EnvironmentPropertiesProvider
{
    public static final EnvironmentPropertiesProvider INSTANCE = new EnvironmentPropertiesProvider();

    private final Properties properties;

    private EnvironmentPropertiesProvider() {
        properties = new Properties();
        try (InputStream propertiesStream = EnvironmentPropertiesProvider.class
                .getClassLoader()
                .getResourceAsStream(String.format("environment/environment.%s.properties", environment()))) {
            properties.load(propertiesStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize EnvironmentPropertiesProvider", e);
        }
    }

    private String environment() {
        if(System.getProperty("environment") == null) {
            return "default";
        }
        return Optional.ofNullable(System.getProperty("environment"))
                .orElseThrow(() -> new NoSuchElementException("Missing 'environment' system property, use -Denvironment"));
    }

    private String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public String getStravaRefreshToken() {
        return getProperty("strava.refresh.token");
    }

    public String getStravaClientSecret() {
        return getProperty("strava.client.secret");
    }

    public String getStravaClientId() {
        return getProperty("strava.client.id");
    }
}
