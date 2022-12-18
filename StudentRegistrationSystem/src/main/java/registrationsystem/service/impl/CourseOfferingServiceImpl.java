package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.CourseOffering;
import registrationsystem.repository.CourseOfferingRepository;
import registrationsystem.service.CourseOfferingService;
import registrationsystem.service.dto.CourseOfferingDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void deleteCourseOffer(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CourseOfferingDTO getCourseOffer(Long id) {
        var courseOffer = repository.findById(id).get();
        return modelMapper.map(courseOffer,CourseOfferingDTO.class);
    }

    @Override
    public Collection<CourseOfferingDTO> getAllCourseOffers() {
        var allOffer = repository.findAll();

        return allOffer.stream()
                .map(offer -> modelMapper.map(offer,CourseOfferingDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addCourseOffer(CourseOffering courseOffering) {
        repository.save(courseOffering);
    }

    @Override
    public CourseOfferingDTO updateCourseOffer(Long id, CourseOffering course) {
        var updateOffer = repository.findById(id).get();

        if(updateOffer != null){
            updateOffer.setId(course.getId());
            updateOffer.setCode(course.getCode());
            updateOffer.setCourse(course.getCourse());
            updateOffer.setFaculty(course.getFaculty());
            updateOffer.setCapacity(course.getCapacity());
            updateOffer.setAvailableSeats(course.getAvailableSeats());
            repository.save(updateOffer);
        }
        return modelMapper.map(updateOffer,CourseOfferingDTO.class);
    }
}
