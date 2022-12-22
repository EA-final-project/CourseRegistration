package registrationsystem.service;

import registrationsystem.domain.AcademicBlock;
import registrationsystem.domain.CourseOffering;
import registrationsystem.service.dto.AcademicBlockDTO;

import java.util.Collection;

public interface AcademicBlockService{

    void deleteBlock(Long id);
    AcademicBlockDTO getBlock(Long id);
    void addCourseOfferingToBlock(Long blockId, CourseOffering offering);
    void addBlock(AcademicBlock block);
    Collection<AcademicBlockDTO> getAllBlocks();
    AcademicBlockDTO updateBlock(Long id, AcademicBlock block);

}
