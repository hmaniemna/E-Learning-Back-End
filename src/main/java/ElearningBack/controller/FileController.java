package ElearningBack.controller;


import java.util.*;
import java.util.stream.Collectors;

import ElearningBack.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ElearningBack.Service.FileStorageService;
import ElearningBack.message.ResponseMessage ;
import ElearningBack.message.ResponseFile ;
import ElearningBack.model.File ;
import org.springframework.core.io.ByteArrayResource ;
import org.springframework.http.MediaType ;
import ElearningBack.exception.ResourceNotFoundException;
@RestController
@CrossOrigin(origins ="http://localhost:4200")
@RequestMapping("/api/v1/")
public class FileController {

    @Autowired
    private FileStorageService storageService;
    private FileRepository  testRepository ;
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    //delete test rest api
    @DeleteMapping("/deletetest/{id}")
    public ResponseEntity <Map<String,Boolean>>  deletefile(@PathVariable Long id){
        File test = testRepository.findById((id))
                .orElseThrow(() -> new ResourceNotFoundException("test not exists with id:" + id));
        testRepository.delete(test);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/downloadFile/")
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
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
        File test = storageService.getFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(test.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+test.getName()+"\"")
                .body(new ByteArrayResource(test.getData()));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable  Long id) {
        File fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @PostMapping("/uploadMultipleFile")
    public List<ResponseEntity<ResponseMessage>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}