package xyz.vladkozlov.epam.springmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.repositories.UsersRepository;
import xyz.vladkozlov.epam.springmvc.services.FileParserService;

import java.io.IOException;
import java.util.List;

@RestController
public class DataUploadController {
    private final FileParserService fileParserService;

    private final UsersRepository usersRepository;

    @Autowired
    public DataUploadController(FileParserService fileParserService, UsersRepository usersRepository) {
        this.fileParserService = fileParserService;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity uploadFiles(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            List<User> parsedListOfUsers = fileParserService.parseFile(file);
            System.out.println(parsedListOfUsers.toString());
            usersRepository.saveAll(parsedListOfUsers);
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

}
