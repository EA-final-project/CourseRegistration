package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.AcademicBlock;
import registrationsystem.domain.CourseOffering;
import registrationsystem.exception.CourseExceptionHandler;
import registrationsystem.repository.AcademicBlockRepository;
import registrationsystem.repository.CourseOfferingRepository;
import registrationsystem.service.AcademicBlockService;
import registrationsystem.service.dto.AcademicBlockDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AcademicBlockServiceImpl implements AcademicBlockService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Autowired
    private AcademicBlockRepository academicBlockRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addBlock(AcademicBlock block) {
        log.info("Saving Block with Id :" + block.toString());
        academicBlockRepository.save(block);
    }

    @Override
    public void addCourseOfferingToBlock(Long blockId, CourseOffering courseOffering) {
        log.info("Saving courseOffering with Id :" + courseOffering.toString());
        var block = academicBlockRepository.findById(blockId).get();
        block.getCourseOfferings().add(courseOffering);
        academicBlockRepository.save(block);
    }
    @Override
    public AcademicBlockDTO getBlock(Long id) {
        var block = academicBlockRepository.findById(id).get();
        if (block == null) {
            log.info("Block with Id :" + id + " not found");
            throw new CourseExceptionHandler("Block with Id :" + id + " not found");
        }
        return modelMapper.map(block, AcademicBlockDTO.class);
    }

    @Override
    public Collection<AcademicBlockDTO> getAllBlocks() {
        var allBlocks = academicBlockRepository.findAll();
        log.info("fetching all blocks :");
        return allBlocks.stream()
                .map(block -> modelMapper.map(block, AcademicBlockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AcademicBlockDTO updateBlock(Long id, AcademicBlock block) {
        var updateBlock = academicBlockRepository.findById(id).get();

        if (updateBlock != null) {
            updateBlock.setId(block.getId());
            updateBlock.setName(block.getName());
            updateBlock.setCode(block.getCode());
            updateBlock.setSemester(block.getSemester());
            updateBlock.setStartDate(block.getStartDate());
            updateBlock.setEndDate(block.getEndDate());
            updateBlock.setCourseOfferings(block.getCourseOfferings());
            log.info("updating Block with Id :" + id);
            academicBlockRepository.save(updateBlock);
        } else {
            throw new CourseExceptionHandler("Block with Id :" + id + " not found");
        }
        return modelMapper.map(updateBlock, AcademicBlockDTO.class);
    }

    @Override
    public void deleteBlock(Long id) {
        var toDelete = academicBlockRepository.findById(id);
        if (toDelete == null) {
            log.info("Deleting Block with Id :" + id);
            throw new CourseExceptionHandler("Block with Id :" + id + " not found");
        }
        academicBlockRepository.deleteById(id);
    }
}
