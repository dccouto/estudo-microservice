package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {
    List<ModuleModel> findAllModuleModelByCourse_courseId(UUID courseId);


    @Query(value = "SELECT module " +
            " from ModuleModel module " +
            " JOIN module.course course " +
            " where module.moduleId = :moduleId " +
            " and course.courseId = :courseId ")
    Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);

    /**
     * Nesse exemplo, utilizando o @EntityGraph ele irá despresar o FetchType.LAZY do atributo course e irá trazer como
     * se fosse FetchType.EAGER
     * */
    //@EntityGraph(attributePaths = {"course"})
    //ModuleModel findByTitle(String title);
}
