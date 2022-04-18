package ElearningBack.controller;
import ElearningBack.exception.ResourceNotFoundException;
import ElearningBack.model.Test;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

import ElearningBack.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import ElearningBack.payload.UploadFileResponse;


import ElearningBack.Service.DocStorageService ;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.stream.Collectors;
import java.util.Arrays;
import ElearningBack.message.ResponseMessage ;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class TestController {
    // define a location
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";
    @Autowired
    private TestRepository testRepository ;

    private DocStorageService docStorageService;

    //get all tests
    @GetMapping("/tests")
    public List<Test> getAlltests(){
        return docStorageService.getFiles();
    }


    /** @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    Test test = docStorageService.saveFile(file);
    message = "Uploaded the file successfully: " + file.getOriginalFilename();
    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
    .path("/downloadFile/")
    .path(test.getIdTest())
    .toUriString();

    return new UploadFileResponse(test.getTestname(), fileDownloadUri,
    file.getContentType(), file.getSize());



    }**/
    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            docStorageService.saveFile(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    //
    // 0< @GetMapping("/tests/{fileId}")
    //public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
    // Test test = docStorageService.getFile(fileId).get();
    // return ResponseEntity.ok()
    //    .contentType(MediaType.parseMediaType(test.getTesttype()))
    //  .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+test.getTestname()+"\"")
    //.body(new ByteArrayResource(test.getDoc()));
    // }


    @PostMapping("/tests")
    public Test createTest(@Valid @RequestBody Test test){
        return testRepository.save(test);
    }

    //get test by id rest api & return error if not found
    @GetMapping("/tests/{id}")
    public ResponseEntity<Test> gettestById(@PathVariable Long id){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("test does not exist with this id: "+ id));
        return ResponseEntity.ok(test);
    }
    @PutMapping("/tests/{id}")
    public ResponseEntity<Test> updatetest( @PathVariable Long id,@Valid @RequestBody Test testDetails) {
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("test not exists wih id :" + id));

        test.setStart_Date(testDetails.getStart_Date());
        test.setDuration(testDetails.getDuration());
        test.setDoc(testDetails.getDoc());
        //test.setTeacher(testDetails.getTeacher());
        test.setTestname(testDetails.getTestname());
        test.setTesttype(testDetails.getTesttype());

        //  test.setTeachers(testDetails.getTeachers());

        Test updatedtest = testRepository.save(test);
        return ResponseEntity.ok(updatedtest);

    }

    //delete test rest api
    @DeleteMapping("/tests/{id}")
    public ResponseEntity <Map<String,Boolean>>  deletetest(@PathVariable Long id){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("test not exists with id:" + id));
        testRepository.delete(test);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return  ResponseEntity.ok(response);
    }

    /** @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files)
    .stream()
    .map(file -> uploadFile(file))
    .collect(Collectors.toList());
    }
     **/
}
