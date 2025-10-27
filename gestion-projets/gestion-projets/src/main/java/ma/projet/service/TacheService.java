package ma.projet.service;

import ma.projet.classes.Tache;

public class TacheService {
    private final ma.projet.dao.impl.TacheDaoImpl tacheDao = new ma.projet.dao.impl.TacheDaoImpl();

    public Tache save(Tache t) { return tacheDao.save(t); }
    public Tache update(Tache t) { return tacheDao.update(t); }
    public void delete(Tache t) { tacheDao.delete(t); }
    public Tache findById(Integer id) { return tacheDao.findById(id); }
}
