package com.matthewjensen.project;

import com.matthewjensen.project.modules.AnagramMain;
import java.io.FileNotFoundException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
@RestController
class TwentyQuestions {

    @RequestMapping("/20questions")
    String index() {
        //AnagramMain anagram = new AnagramMain();
        
        //String[] args = new String[4];
        //try {
        //    anagram.main(args);
        //} catch (FileNotFoundException e){
        //}
        return "Hello There";
    }
}
