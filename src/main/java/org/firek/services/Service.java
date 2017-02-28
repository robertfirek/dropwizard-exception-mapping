package org.firek.services;

import io.dropwizard.hibernate.AbstractDAO;
import org.firek.resources.ResourceResult;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

public class Service extends AbstractDAO<Object> {

    public Service(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<ResourceResult> query() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ResultTransformer transformer = new ResourceResultTransformer();
        Query hibernateQuery = currentSession()
                .createSQLQuery("SELECT description FROM errorTestTable")
                .setResultTransformer(transformer);
        return hibernateQuery.list();
    }
}
