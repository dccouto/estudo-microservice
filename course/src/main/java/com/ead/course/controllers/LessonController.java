package com.ead.course.controllers;

import com.ead.course.dtos.LessonDto;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
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
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ModuleService moduleService;

    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<?> saveLesson(@RequestBody @Valid LessonDto lessonDto, @PathVariable UUID moduleId){
        Optional<ModuleModel> optionalModuleModel = this.moduleService.findById(moduleId);
        if (optionalModuleModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found!");

        }
        var lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonDto, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(optionalModuleModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.lessonService.save(lessonModel));
    }


    @DeleteMapping("/modules/{moduleId}/lessons/{lessonsId}")
    public  ResponseEntity<?> deleteLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonsId){
        Optional<LessonModel> optionalLessonModel = this.lessonService.findLessonIntoCourse(moduleId, lessonsId);
        if (optionalLessonModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");

        }

        this.lessonService.delete(optionalLessonModel.get());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modules/{moduleId}/lessons/{lessonsId}")
    public  ResponseEntity<?> updateLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonsId,
                                           @RequestBody @Valid LessonDto lessonDto){
        Optional<LessonModel> optionalLessonModel = this.lessonService.findLessonIntoCourse(moduleId, lessonsId);
        if (optionalLessonModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");

        }
        var lessonModel = optionalLessonModel.get();
        lessonModel.setTitle(lessonDto.getTitle());
        lessonModel.setDescription(lessonDto.getDescription());
        lessonModel.setVideoUrl(lessonDto.getVideoUrl());
        return ResponseEntity.status(HttpStatus.OK).body(this.lessonService.save(lessonModel));

    }


    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<?> getAllLessons(@PathVariable UUID moduleId){
        return ResponseEntity.status(HttpStatus.OK).body(this.lessonService.findAllByModule_ModuleId(moduleId));

    }


    @GetMapping("/modules/{moduleId}/lessons/{lessonsId}")
    public ResponseEntity<?> getAOneLesson(@PathVariable UUID moduleId, @PathVariable UUID lessonsId){
        Optional<LessonModel> optionalLessonModel = this.lessonService.findLessonIntoCourse(moduleId, lessonsId);
        if (optionalLessonModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found for this module.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalLessonModel.get());

    }

}
