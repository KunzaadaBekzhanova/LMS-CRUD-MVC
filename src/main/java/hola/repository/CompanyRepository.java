package hola.repository;

import hola.models.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@Transactional
public class CompanyRepository {

    private final EntityManager entityManager;

    public CompanyRepository(EntityManagerFactory entityManager) {
        this.entityManager = entityManager.createEntityManager();
    }


    public void save(Company company) {
        entityManager.getTransaction().begin();
        entityManager.persist(company);
        entityManager.getTransaction().commit();
    }


    public List<Company> getCompanies() {
        return entityManager.createQuery("SELECT e FROM Company e", Company.class).getResultList();
    }


    public Company getCompanyById(Long id) {
        return entityManager.find(Company.class, id);

    }


    public void deleteCompany(Long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(getCompanyById(id));
        entityManager.getTransaction().commit();
    }


    public void updateCompany(Long id, Company updatedCompany) {
        entityManager.getTransaction().begin();
        Company company = getCompanyById(id);
        company.setCompanyName(updatedCompany.getCompanyName());
        company.setLocatedCountry(updatedCompany.getLocatedCountry());
        entityManager.merge(company);
        entityManager.getTransaction().commit();

    }
}

