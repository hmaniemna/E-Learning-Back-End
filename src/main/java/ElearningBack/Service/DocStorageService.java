package ElearningBack.Service;

import java.util.List;
import java.util.Optional;

import ElearningBack.model.Doc;
import ElearningBack.model.Teacher;
import ElearningBack.model.Test;
import ElearningBack.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date ;
import java.io.IOException;
import ElearningBack.repository.DocRepository;
import org.springframework.util.StringUtils ;
import java.util.stream.Stream;
@Service
public class DocStorageService {
    @Autowired
    private DocRepository docRepository;

    public Doc store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Doc File = new Doc(fileName, file.getContentType(), file.getBytes());

        return docRepository.save(File);
    }


    public Stream<Doc> getAllDoc() {
        return docRepository.findAll().stream();
    }


    public Doc getDoc(Long id) {
        return docRepository.findById(id).get();
    }
}

