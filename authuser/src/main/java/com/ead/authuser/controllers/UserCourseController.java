package com.ead.authuser.controllers;

import com.ead.authuser.clients.CourseClient;
import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.UserCourseDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserCourseService;
import com.ead.authuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserCourseController {

    private final CourseClient userClient;
    private final UserService userService;
    private final UserCourseService userCourseService;


    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Page<CourseDto>> getAllCoursesByUser(@PathVariable UUID userId,
                                                               @PageableDefault(page = 0, size = 10, sort = "courseId",
                                                                       direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(userClient.getAllCoursesByUser(userId, pageable));
    }

    @PostMapping("/users/{userId}/courses/subscription")
    public ResponseEntity<?> saveSubscriptionUserInCourse(@PathVariable UUID userId, @RequestBody @Valid UserCourseDto userCourseDto){
        Optional<UserModel> userOptional = userService.findById(userId);
        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.get());
        }
        if(userCourseService.existsByUserAndCourseId(userOptional.get(), userCourseDto.getCourseId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Inscrição já existe.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userCourseService
                .save(userOptional.get().convertToUserCourseModel(userCourseDto.getCourseId())));
    }
}
