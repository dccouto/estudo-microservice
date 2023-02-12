package com.ead.course.models;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//não serializa atributos com campos nulos
@Entity
@Table(name = "TB_COURSES")
public class CourseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID courseId;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    @Column(nullable = false, length = 250)
    private String description;

    @Column
    private String imageUrl;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime creationDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(nullable = false)
    private UUID userInstructor;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    /**
     * FetchMode.JOIN o hibernate fará apenas uma única consulta, mas se tiver utilizando o "fetch = FetchType.LAZY"
     * o FetchMode.JOIN 'sobrescreverá' o fetch = FetchType.LAZY que passará a se comportar como EAGER, pois é
     * necessário trazer tudo de uma só vez.
     *
     * FetchMode.SUBSELECT o hibernate fará uma consulta principal para trazer a entidade e mais uma consulta para
     * trazer os relacionamentos, esse modo é o mais adequado quando se está utilizando o LAZY.
     *
     * Por default o FetchMode é o JOIN mas se o FetchType foi definido, ele irá trazer de acordo com a definição,
     * isso é, não ignorando o tipo de FetchType
    */
    @Fetch(FetchMode.SUBSELECT)
    //@OnDelete(action = OnDeleteAction.CASCADE) é um delete que o banco realiza o delete, o responsabilidade fica com o banco de dados
    private Set<ModuleModel> modules;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<CourseUserModel> coursesUsers;


    public CourseUserModel convertToCourseUserModel(UUID userId){
        return new CourseUserModel(null, userId,this);
    }
}
