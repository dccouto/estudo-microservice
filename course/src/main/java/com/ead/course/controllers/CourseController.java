package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> saveCourse(@RequestBody @Valid CourseDto courseDto){
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.courseService.save(courseModel));
    }

    @DeleteMapping("/{courseId}")
    public  ResponseEntity<?> deleteCourse(@PathVariable UUID courseId){
        Optional<CourseModel> couserModelOptional = this.courseService.findById(courseId);
        if (couserModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        this.courseService.delete(couserModelOptional.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{courseId}")
    public  ResponseEntity<?> updateCourse(@PathVariable UUID courseId, @RequestBody @Valid CourseDto courseDto){
        Optional<CourseModel> couserModelOptional = this.courseService.findById(courseId);
        if (couserModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        var courseModel = couserModelOptional.get();
        courseModel.setName(courseDto.getName());
        courseModel.setDescription(courseDto.getDescription());
        courseModel.setImageUrl(courseDto.getImageUrl());
        courseModel.setCourseStatus(courseDto.getCourseStatus());
        courseModel.setCourseLevel(courseDto.getCourseLevel());
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.save(courseModel));

    }

    @GetMapping
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.findAll());

    }

    @GetMapping("/{idCourse}")
    public ResponseEntity<?> getAllCourses(@PathVariable UUID idCourse){
        Optional<CourseModel> couserModelOptional = this.courseService.findById(idCourse);
        if (couserModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(couserModelOptional.get());

    }

}
