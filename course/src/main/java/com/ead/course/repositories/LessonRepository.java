package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    List<LessonModel> findAllLessonModelByModule_moduleId(UUID moduleId);

    @Query(value = "SELECT lesson " +
            " from LessonModel lesson " +
            " JOIN lesson.module module " +
            " where lesson.lessonId = :lessonId " +
            " and module.moduleId = :moduleId ")
    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);
}
