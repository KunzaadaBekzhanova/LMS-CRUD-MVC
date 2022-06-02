package hola.service;

import hola.models.Group;
import hola.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupService{
    private GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }


    public void saveGroup(Group group) {
        groupRepository.saveGroup(group);
    }


    public List<Group> getGroups(Long id) {
        return groupRepository.getGroups(id);
    }


    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }


    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }


    public void updateGroup(Long id, Group updatedGroup) {
        groupRepository.updateGroup(id, updatedGroup);
    }


    public List<Group> getGroupByCompanyId(Long id) {
        return groupRepository.getGroupByCompanyId(id);
    }
}
