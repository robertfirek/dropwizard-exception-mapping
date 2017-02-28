package org.firek;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.firek.hibernate.HibernateBundle;
import org.firek.mappers.GenericJDBCExceptionMapper;
import org.firek.mappers.MyExceptionMapper;
import org.firek.resources.Resource;
import org.firek.services.Service;

public class Application extends io.dropwizard.Application<Configuration> {

    private HibernateBundle hibernateBundle;

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public String getName() {
        return "errorTest";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        hibernateBundle = new HibernateBundle();
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        final Service service = new Service(hibernateBundle.getSessionFactory());
        final Resource resource = new Resource(service);
        environment.jersey().register(resource);
        environment.jersey().register(new GenericJDBCExceptionMapper());
        environment.jersey().register(new MyExceptionMapper());
    }
}
