package hola.repository;

import hola.models.Group;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;


    public void saveGroup(Group group) {
        //entityManager.getTransaction().begin();
        entityManager.persist(group);
        //entityManager.getTransaction().commit();
    }


    public List<Group> getGroups(Long id) {
        return entityManager.createNativeQuery("Select gr from Group gr", Group.class).getResultList();
    }


    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }


    public void deleteGroup(Long id) {
        entityManager.remove(getGroupById(id));
    }


    public void updateGroup(Long id, Group updatedGroup) {
        Group group = getGroupById(id);
        group.setGroupName(updatedGroup.getGroupName());
        group.setDateOfStart(updatedGroup.getDateOfStart());
        group.setDateOfFinish(updatedGroup.getDateOfFinish());
        entityManager.merge(group);
    }


    public List<Group> getGroupByCompanyId(Long id) {
        return entityManager.createQuery("Select gr from Group gr where gr.company.id = :id", Group.class)
                .setParameter("id" ,id)
                .getResultList();
    }

}
