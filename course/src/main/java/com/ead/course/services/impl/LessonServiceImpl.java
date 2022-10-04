package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public LessonModel save(LessonModel lessonModel) {
        return this.lessonRepository.save(lessonModel);
    }

    @Override
    public Optional<LessonModel> findLessonIntoCourse(UUID moduleId, UUID lessonsId) {
        return this.lessonRepository.findLessonIntoModule(moduleId, lessonsId);
    }

    @Override
    public void delete(LessonModel lessonModel) {
        this.lessonRepository.delete(lessonModel);
    }

    @Override
    public List<LessonModel> findAllByModule_ModuleId(UUID moduleId) {
        return this.lessonRepository.findAllLessonModelByModule_moduleId(moduleId);
    }
}
