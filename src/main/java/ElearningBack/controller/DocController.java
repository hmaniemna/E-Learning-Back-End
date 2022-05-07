package ElearningBack.controller;


import java.util.*;
import java.util.stream.Collectors;

import ElearningBack.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ElearningBack.Service.DocStorageService;
import ElearningBack.message.ResponseMessage ;
import ElearningBack.message.ResponseFile ;
import ElearningBack.model.File ;
import org.springframework.core.io.ByteArrayResource ;
import org.springframework.http.MediaType ;
import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Doc ;
@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api/v1/")
public class DocController {

    @Autowired
    private DocStorageService storageService;
    private DocRepository  docRepository ;
    @PostMapping("/uploadDoc")
    public ResponseEntity<ResponseMessage> uploadDoc(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the doc successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    //delete test rest api
    @DeleteMapping("/deleteDoc/{id}")
    public ResponseEntity <Map<String,Boolean>>  deleteDoc(@PathVariable Long id){
        Doc doc = docRepository.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("test not exists with id:" + id));
        docRepository.delete(doc);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }


    @GetMapping("/doc")
    public ResponseEntity<List<ResponseFile>> getListDoc() {
        List<ResponseFile> files = storageService.getAllDoc().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/downloadDoc/")
                    .path(String.valueOf(dbFile.getId()))
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @GetMapping("/downloadDoc/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadDoc(@PathVariable Long fileId){
        Doc doc = storageService.getDoc(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }

    @GetMapping("/doc/{id}")
    public ResponseEntity<byte[]> getDoc(@PathVariable  Long id) {
        Doc doc = storageService.getDoc(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getName() + "\"")
                .body(doc.getData());
    }
    @PostMapping("/uploadMultipleDoc")
    public List<ResponseEntity<ResponseMessage>> uploadMultipleDoc(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadDoc(file))
                .collect(Collectors.toList());
    }
}