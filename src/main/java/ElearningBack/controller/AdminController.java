package ElearningBack.controller;

import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Admin;
import ElearningBack.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:60040")
@RequestMapping("/api/v1/")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    //logging for admin
    @PostMapping("/admin")
    public ResponseEntity<?> connectAdmin(@RequestBody Admin adminDetails){
        System.out.println(adminDetails);

        Admin admin = adminRepository.findById(adminDetails.getIdA())
                .orElseThrow(() -> new ResourceNotFoundException("Admin not exist with id :" + adminDetails.getIdA()));
        if (admin.getPassword().equals(adminDetails.getPassword()))
            return ResponseEntity.ok(admin);
        else
            return (ResponseEntity<?>) ResponseEntity.internalServerError();

    }
}
