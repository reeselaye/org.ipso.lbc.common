package org.ipso.lbc.common.domain;

import org.ipso.lbc.common.db.dao.SuperDAO;

import java.io.Serializable;

public class AbstractDomain implements Serializable {
    private SuperDAO superDAO;

    public AbstractDomain() {
    }

    public AbstractDomain attachSuperDAO(SuperDAO superDAO) {
        this.superDAO = superDAO;
        return this;
    }

    private SuperDAO getSuperDAO() {
        if (superDAO == null) {
            throw new RuntimeException("Cannot complete operation before a SuperDAO being attached to object <" + toString() + ">");
        }
        return superDAO;
    }

    public AbstractDomain saveOrUpdate() {
        getSuperDAO().insertOrUpdate(this);
        return this;
    }

    public AbstractDomain delete() {
        getSuperDAO().delete(this);
        return this;
    }
}
