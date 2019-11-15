package by.fastrentcar.service;

import by.fastrentcar.dao.ConfigSpringDAO;

//@Configuration
//@Import(ConfigSpringDAO.class)
public class ConfigSpringService {

    private ConfigSpringDAO configSpringDAO;

    public ConfigSpringService(ConfigSpringDAO authUserDAO) {
        this.configSpringDAO = authUserDAO;
    }

//    @Bean
//    public UserService getDefaultUserService() {
//        return new DefaultUserService(configSpringDAO.getDefaultAuthUserDAO());
//    }


}
