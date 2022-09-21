package com.ead.course.controllers;

import com.ead.course.dtos.CourseDto;
import com.ead.course.dtos.ModuleDto;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import com.fasterxml.jackson.databind.Module;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<?> saveModule(@RequestBody @Valid ModuleDto moduleDto, @PathVariable UUID courseId){
        Optional<CourseModel> courseModelOptional = this.courseService.findById(courseId);
        if (courseModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found!");

        }
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModelOptional.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.moduleService.save(moduleModel));
    }

    @DeleteMapping("/courses/{courseId}/modules/{moduleId}")
    public  ResponseEntity<?> deleteModule(@PathVariable UUID courseId, @PathVariable UUID moduleId){
        Optional<ModuleModel> optionalModuleModel = this.moduleService.findModuleIntoCourse(courseId, moduleId);
        if (optionalModuleModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");

        }

        this.moduleService.delete(optionalModuleModel.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/courses/{courseId}/modules/{moduleId}")
    public  ResponseEntity<?> updateModule(@PathVariable UUID courseId, @PathVariable UUID moduleId,
                                           @RequestBody @Valid ModuleDto moduleDto){
        Optional<ModuleModel> optionalModuleModel = this.moduleService.findModuleIntoCourse(courseId, moduleId);
        if (optionalModuleModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");

        }
        var moduleModel = optionalModuleModel.get();
        moduleModel.setTitle(moduleDto.getTitle());
        moduleModel.setDescription(moduleDto.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(this.moduleService.save(moduleModel));

    }

    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<?> getAllModules(@PathVariable UUID courseId){
        return ResponseEntity.status(HttpStatus.OK).body(this.moduleService.findAllByCourse_CourseId(courseId));

    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<?> getAOneModule(@PathVariable UUID courseId, @PathVariable UUID moduleId){
        Optional<ModuleModel> optionalModuleModel = this.moduleService.findModuleIntoCourse(courseId, moduleId);
        if (optionalModuleModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalModuleModel.get());

    }

}
