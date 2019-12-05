package by.fastrentcar.springdata.config;

import org.springframework.beans.factory.annotation.Value;


public class DatasourseSettings {
    @Value("${dburl}")
    private String url;

    @Value("${dbusername}")
    private String user;

    @Value("${dbpassword}")
    private String password;

    @Value("${dbdriver}")
    private String driver;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
