package xyz.vladkozlov.epam.springmvc.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xyz.vladkozlov.epam.springmvc.models.User;
import xyz.vladkozlov.epam.springmvc.services.FileParserService;

import java.io.IOException;
import java.util.List;

@Service
public class JSONFileParserServiceImpl implements FileParserService {

    private final ObjectMapper objectMapper;

    public JSONFileParserServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public List<User> parseFile(MultipartFile file) throws IOException {
        return objectMapper.readValue(
                file.getInputStream(),
                objectMapper
                        .getTypeFactory()
                        .constructCollectionType(List.class, User.class));
    }
}
