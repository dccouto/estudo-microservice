package com.ead.course.repositories;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    List<LessonModel> findAllLessonModelByModule_moduleId(UUID moduleId);
}
