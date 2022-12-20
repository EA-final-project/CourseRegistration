package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.Faculty;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.FacultyRepository;
import registrationsystem.service.FacultyService;
import registrationsystem.service.dto.FacultyDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
    @Override
    public FacultyDTO getFaculty(Long id) {
        var faculty = facultyRepository.findById(id).get();

        if(faculty == null){
            throw new CourseExceptionHandler("Faculty with id "+id+ " not found");
        }
        return modelMapper.map(faculty,FacultyDTO.class);
    }

    @Override
    public Collection<FacultyDTO> getAllFaculty() {
        var allFaculty = facultyRepository.findAll();

        return allFaculty.stream()
                .map(faculty -> modelMapper.map(faculty, FacultyDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO updateFaculty(Long id, Faculty faculty) {
        var updateFaculty = facultyRepository.findById(id).get();

        if(updateFaculty != null){
            updateFaculty.setId(faculty.getId());
            updateFaculty.setFistName(faculty.getFistName());
            updateFaculty.setLastName(faculty.getLastName());
            updateFaculty.setTitle(faculty.getTitle());
            updateFaculty.setEmail(faculty.getEmail());
            facultyRepository.save(updateFaculty);
        } else {
            throw new CourseExceptionHandler("Faculty with id "+id+ " not found");
        }
        return modelMapper.map(updateFaculty, FacultyDTO.class);
    }
    @Override
    public void deleteFaculty(Long id) {
        var toDelete = facultyRepository.findById(id);

        if(toDelete == null){
            throw new CourseExceptionHandler("Faculty with id "+id+ " not found");
        }
        facultyRepository.deleteById(id);
    }
}
