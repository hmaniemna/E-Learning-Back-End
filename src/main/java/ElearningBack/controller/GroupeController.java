package ElearningBack.controller;

import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Groupe;
import ElearningBack.model.Student;
import ElearningBack.repository.GroupeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:60040")
@RequestMapping("/api/v1/")
public class GroupeController {

    @Autowired
    private GroupeRepository groupeRepository;

    //get all groups
    @GetMapping("/groups")
    public List<Groupe> getAllGroups(){
        return groupeRepository.findAll();
    }

    //create a new group rest api
    @PostMapping("/groups")
    public Groupe createGroup(@Valid @RequestBody Groupe group){
        return groupeRepository.save(group);
    }

    //get group by id rest api & return error if not found
    @GetMapping("/groups/{id}")
    public ResponseEntity<Groupe> getGroupById(@PathVariable Long id){
        Groupe groupe = groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("group does not exist with this id: "+ id));
        return ResponseEntity.ok(groupe);
    }
    //update group rest api
    @PutMapping("/groups/{id}")
    public ResponseEntity<Groupe> updateStudent( @PathVariable Long id,@Valid @RequestBody Groupe groupDetails) {
        Groupe group = groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not exists wih id :" + id));

        group.setNameG(groupDetails.getNameG());
        group.setLevelG(groupDetails.getLevelG());

        Groupe updatedGroup = groupeRepository.save(group);
        return ResponseEntity.ok(updatedGroup);
    }

    //delete group rest api
    @DeleteMapping("/groups/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteGroup(@PathVariable Long id){
        Groupe groupe= groupeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student not exists with id:" + id));
        groupeRepository.delete(groupe);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

}
