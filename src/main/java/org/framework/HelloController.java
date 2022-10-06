package org.framework;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping
    public String postCheck(@RequestBody MyPojoClass myPojoClass) {

        return "The Post call has been executed successfully!!! " + myPojoClass.toString();
    }

    @PutMapping
    public String putCheck(@RequestParam String name) {
        return "The Put call has been executed successfully!!!";
    }

    @DeleteMapping
    public String deleteCheck() {
        return "The delete call has been executed successfully!!!";
    }


}
