package org.firek.services;

import org.firek.resources.ResourceResult;
import org.hibernate.transform.ResultTransformer;

import java.util.List;

class ResourceResultTransformer implements ResultTransformer {
    @Override
    public ResourceResult transformTuple(Object[] objects, String[] strings) {
        return new ResourceResult((String) objects[0]);
    }

    @Override
    public List transformList(List list) {
        return list;
    }
}
