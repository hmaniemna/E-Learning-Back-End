package ElearningBack.controller;

import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Admin;
import ElearningBack.model.Groupe;
import ElearningBack.model.Student;
import ElearningBack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    //logging for admin
    //@PostMapping("/admin")
    //public ResponseEntity<?> connectAdmin(@RequestBody Admin adminDetails){
     //   System.out.println(adminDetails);

      //  Admin admin = adminRepository.findById(adminDetails.getIdA())
       //         .orElseThrow(() -> new ResourceNotFoundException("Admin not exist with id :" + adminDetails.getIdA()));
      //  if (admin.getPassword().equals(adminDetails.getPassword()))
      //      return ResponseEntity.ok(admin);
     //   else
     //       return (ResponseEntity<?>) ResponseEntity.internalServerError();

   // }

    @GetMapping("/admin/{code}/{password}")
    public ResponseEntity<?> getAdminFromCodeAndPassword(@PathVariable Integer code, @PathVariable String password){
        System.out.println(code+password);



        Admin admin = adminRepository.getAdminFromCodeAndPassword(code, password);
        //.orElseThrow(() -> new ResourceNotFoundException("admin not exist with id :" + id));
        System.out.println(admin);
        //if (student.getPassword().equals(password))
        return ResponseEntity.ok(admin);
        //else
        //return (ResponseEntity<?>) ResponseEntity.internalServerError();

    }

    //get admin by id rest api & return error if not found
    @GetMapping("/admin/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id){
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("admin does not exist with this id: "+ id));
        return ResponseEntity.ok(admin);
    }

    //update admin rest api
    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> updateAdmin( @PathVariable Long id,@Valid @RequestBody Admin adminDetails) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("admin not exists wih id :" + id));

        admin.setPassword(adminDetails.getPassword());
        admin.setCode(adminDetails.getCode());

        Admin updatedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(updatedAdmin);
    }

    //get all admins
    @GetMapping("/admin")
    public List<Admin> getAdmin(){
        return adminRepository.findAll();
    }

}
