package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Course;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.CourseRepository;
import registrationsystem.service.CourseService;
import registrationsystem.service.dto.CourseDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseDTO getCourse(Long id) {
        var course = courseRepository.findById(id);
        if(course == null){
            System.out.println("Course with id  "+ id + " not found");
        }
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
        log.info("new course added");
    }

    @Override
    public CourseDTO updateCourse(Long id, Course course) {
        var foundCourse = courseRepository.findById(id).get();
        if (foundCourse != null) {
            foundCourse.setId(course.getId());
            foundCourse.setCode(course.getCode());
            foundCourse.setName(course.getName());
            foundCourse.setDescription(course.getDescription());
            courseRepository.save(foundCourse);
        }
        else {
            System.out.println("Course with id : "+ id + " not found");
        }
        return modelMapper.map(foundCourse, CourseDTO.class);
    }

    @Override
    public Collection<CourseDTO> getAllCourses() {
        var allCourses = courseRepository.findAll();

        return allCourses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO getCourseByCode(String code) {
        var course = courseRepository.findCourseByCode(code).get();
        if(course == null){
            System.out.println("Course with code "+ code + " not found");
        }
        return modelMapper.map(course, CourseDTO.class);
    }
}
