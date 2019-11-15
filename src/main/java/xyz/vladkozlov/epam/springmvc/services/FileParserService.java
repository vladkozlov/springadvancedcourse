package xyz.vladkozlov.epam.springmvc.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.vladkozlov.epam.springmvc.models.User;

import java.io.IOException;
import java.util.List;

@Service
public interface FileParserService {
    List<User> parseFile(MultipartFile file) throws IOException;
}
