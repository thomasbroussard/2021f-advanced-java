package fr.epita.springdemo;


import fr.epita.springdemo.datamodel.Person;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baseurl/")
public class SampleRestController {

    @GetMapping("/get-sample")
    public ResponseEntity<String> onGet(){
        return ResponseEntity.ok("hello");
    }




    @PostMapping(value = "/post-sample", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void test(@RequestBody Person contact){
        //{'name':'test'}

        System.out.println(contact.getName());
    }


}











