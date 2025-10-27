package ma.projet.service;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ma.projet.beans.Mariage;

public class MariageService {

    private EntityManager em;

    public MariageService(EntityManager em) {
        this.em = em;
    }

    // Ajouter un mariage
    public void addMariage(Mariage mariage) {
        em.getTransaction().begin();
        em.persist(mariage);
        em.getTransaction().commit();
    }

    // Supprimer un mariage par id
    public void deleteMariage(int id) {
        em.getTransaction().begin();
        Mariage mariage = em.find(Mariage.class, id);
        if (mariage != null) {
            em.remove(mariage);
        }
        em.getTransaction().commit();
    }

    // Modifier un mariage
    public void updateMariage(Mariage mariage) {
        em.getTransaction().begin();
        em.merge(mariage);
        em.getTransaction().commit();
    }

    // Lister tous les mariages
    public List<Mariage> listMariages() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Mariage> cq = cb.createQuery(Mariage.class);
        Root<Mariage> rootEntry = cq.from(Mariage.class);
        CriteriaQuery<Mariage> all = cq.select(rootEntry);
        TypedQuery<Mariage> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    // Trouver un mariage par id
    public Mariage findMariageById(int id) {
        return em.find(Mariage.class, id);
    }
}
