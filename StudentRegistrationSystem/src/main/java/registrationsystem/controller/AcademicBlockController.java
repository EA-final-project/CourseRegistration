package registrationsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrationsystem.domain.AcademicBlock;
import registrationsystem.domain.CourseOffering;
import registrationsystem.repository.CourseOfferingRepository;
import registrationsystem.service.AcademicBlockService;

@RestController
@RequestMapping("/academic-blocks")
public class AcademicBlockController {

    @Autowired
    private AcademicBlockService academicBlockService;
    @Autowired
    private AcademicBlockService service;
    @PostMapping
    public ResponseEntity<?> addBlock(@RequestBody AcademicBlock academicBlock){
        service.addBlock(academicBlock);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{blockId}/addcourseoffering")
    public ResponseEntity<?> addCourseOfferingToBlock(@PathVariable Long blockId, @RequestBody CourseOffering offering) {
           academicBlockService.addCourseOfferingToBlock(blockId,offering);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}") //working
    public ResponseEntity<?> getBlock(@PathVariable Long id) {
        var block = service.getBlock(id);
        if (block == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(block);
    }

    @GetMapping //working
    public ResponseEntity<?> getAllBlocks() {
        var allBlocks = service.getAllBlocks();
        return ResponseEntity.ok(allBlocks);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBlock(@PathVariable Long id, @RequestBody AcademicBlock block) {
        var update = service.updateBlock(id, block);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlock(@PathVariable Long id) {
        service.deleteBlock(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
