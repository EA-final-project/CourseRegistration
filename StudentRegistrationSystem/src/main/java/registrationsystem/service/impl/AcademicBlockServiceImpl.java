package registrationsystem.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import registrationsystem.domain.AcademicBlock;
import registrationsystem.repository.AcademicBlockRepository;
import registrationsystem.service.AcademicBlockService;
import registrationsystem.service.dto.AcademicBlockDTO;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AcademicBlockServiceImpl implements AcademicBlockService {
    @Autowired
    private AcademicBlockRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addBlock(AcademicBlock block) {
        repository.save(block);
    }

    @Override
    public AcademicBlockDTO getBlock(Long id) {
        var block = repository.findById(id).get();
        return modelMapper.map(block, AcademicBlockDTO.class);
    }

    @Override
    public Collection<AcademicBlockDTO> getAllBlocks() {
        var allBlocks = repository.findAll();

        return allBlocks.stream()
                .map(block -> modelMapper.map(block, AcademicBlockDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public AcademicBlockDTO updateBlock(Long id, AcademicBlock block) {
        var updateBlock = repository.findById(id).get();

        if (updateBlock != null) {
            updateBlock.setId(block.getId());
            updateBlock.setName(block.getName());
            updateBlock.setCode(block.getCode());
            updateBlock.setSemester(block.getSemester());
            updateBlock.setStartDate(block.getStartDate());
            updateBlock.setEndDate(block.getEndDate());
            updateBlock.setCourseOfferings(block.getCourseOfferings());
            repository.save(updateBlock);
        }
        return modelMapper.map(updateBlock, AcademicBlockDTO.class);
    }

    @Override
    public void deleteBlock(Long id) {
        repository.deleteById(id);
    }
}
