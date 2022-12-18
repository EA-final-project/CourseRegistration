package registrationsystem.service;

import registrationsystem.domain.Faculty;
import registrationsystem.service.dto.FacultyDTO;

import java.util.Collection;

public interface FacultyService {
    void deleteFaculty(Long id);
    FacultyDTO getFaculty(Long id);
    void addFaculty(Faculty faculty);
    Collection<FacultyDTO> getAllFaculty();
    FacultyDTO updateFaculty(Long id, Faculty faculty);

}
