package ElearningBack.Service;

import java.util.List;
import java.util.Optional;

import ElearningBack.model.Teacher;
import ElearningBack.model.Test;
import ElearningBack.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date ;
import java.io.IOException;


public class DocStorageService {
    @Autowired
    private TestRepository docRepository;
    public Test saveFile(MultipartFile file) {
        String testname = file.getOriginalFilename();
        Date start_Date = null;
        String duration = null;
        //Teacher teacher = null;
        try {
            Test test = new Test( start_Date , duration, file.getBytes() ,  testname ,file.getContentType());
            return docRepository.save(test);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Optional<Test> getFile(Integer fileId) {
        return docRepository.findById(Long.valueOf(fileId));
    }
    public List<Test> getFiles(){
        return docRepository.findAll();
    }
}

