package ma.projet.service;

import ma.projet.classes.EmployeTache;
import ma.projet.dao.impl.EmployeTacheDaoImpl;

public class EmployeTacheService {
    private final EmployeTacheDaoImpl dao = new EmployeTacheDaoImpl();

    public EmployeTache save(EmployeTache et) { return dao.save(et); }
    public EmployeTache update(EmployeTache et) { return dao.update(et); }
    public void delete(EmployeTache et) { dao.delete(et); }
}
