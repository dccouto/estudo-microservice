package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID> {
    List<ModuleModel> findAllModuleModelByCourse_courseId(UUID courseId);

    /**
     * Nesse exemplo, utilizando o @EntityGraph ele irá despresar o FetchType.LAZY do atributo course e irá trazer como
     * se fosse FetchType.EAGER
     * */
    //@EntityGraph(attributePaths = {"course"})
    //ModuleModel findByTitle(String title);
}
