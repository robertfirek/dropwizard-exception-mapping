package org.firek.hibernate;

import com.google.common.collect.ImmutableList;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.SessionFactoryFactory;
import org.firek.Configuration;

public class HibernateBundle extends io.dropwizard.hibernate.HibernateBundle<Configuration> {
    public HibernateBundle() {
        super(ImmutableList.<Class<?>>builder().build(), new SessionFactoryFactory());
    }

    @Override
    public PooledDataSourceFactory getDataSourceFactory(Configuration configuration) {
        return configuration.getDataSourceFactory();
    }
}
