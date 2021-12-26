package com.example.demojpa;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static final String FIRST_NAME_HEADER = "firstName";
    private static final String LAST_NAME_HEADER = "lastName";
    private static final String AGE_HEADER = "age";
    private static final String EMAIL_HEADER = "email";


    // >= 2.x && < 2.17 - Vulnerable
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() throws SQLException {
        return userService.getUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") int userId) throws SQLException {
        return userService.getUserById(userId);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {

        try{
            userService.createUser(userCreateRequest);
            return new ResponseEntity<String>("User is created", HttpStatus.CREATED);
        }catch (SQLException e){
            return new ResponseEntity<>(e.getCause().getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause().getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user")
    public User deleteUser(@RequestParam("id") int userId) throws SQLException {
        return userService.deleteUser(userId);
    }

    @PostMapping("/parse-file")
    public ResponseEntity<String> parseFile(HttpServletRequest httpServletRequest) throws ServletException, IOException, SQLException {

        Part filePart = httpServletRequest.getPart("my_file");
        Part textPart = httpServletRequest.getPart("my_text");

//        InputStream inputStream = textPart.getInputStream();
//        String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
//
//        String partValue = textPart.getSubmittedFileName();
//
//        String csvResult = IOUtils.toString(filePart.getInputStream(), StandardCharsets.UTF_8);
//
//        String filePartValue = filePart.getSubmittedFileName();
//
//        logger.info("textPart is {}, {}", textPart.toString(), textPart.getName());


        InputStream inputStream = filePart.getInputStream();
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(FIRST_NAME_HEADER, LAST_NAME_HEADER, AGE_HEADER, EMAIL_HEADER);

        CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), csvFormat);

        List<CSVRecord> records = csvParser.getRecords();

        List<UserCreateRequest> userCreateRequests = new ArrayList<>();

        for(int i = 1; i < records.size(); i++){
            logger.info("record no {} is {}", i, records.get(i));

            UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                    .firstName(records.get(i).get(FIRST_NAME_HEADER))
                    .lastName(records.get(i).get(LAST_NAME_HEADER))
                    .age(Integer.parseInt(records.get(i).get(AGE_HEADER)))
                    .email(records.get(i).get(EMAIL_HEADER))
                    .build();

            logger.info("user create request is {}", userCreateRequest);

            userCreateRequests.add(userCreateRequest);
        }

        List<User> usersCreated = userService.createBulkUsers(userCreateRequests);

        return new ResponseEntity<>(usersCreated.size() + " users are created in ths system", HttpStatus.OK);
    }

    // You will be receiving a csv/xls file which contains some users info,
    // you need to parse that file and save the user info in the db
}
