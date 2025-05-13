package com.data.controller;

import com.data.entity.Lessons;
import com.data.repository.LessonsReopository;
import dto.LessonsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LessonsController {

    private LessonsReopository lessonsRepo;
    public LessonsController(LessonsReopository lessonsReopository) {
        this.lessonsRepo = lessonsReopository;
    }


    //Thêm bài học vào khoá học
    @PostMapping("/courses/{id}/lessons")
    public ResponseEntity<?> create(@RequestBody LessonsDTO lessonsDTO) {
        if (lessonsDTO.getLessonsName() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền tên bài học");
        }
        if (lessonsDTO.getHours() == 0) {
            return ResponseEntity.badRequest().body("Cần phải điền số giờ");
        }
        Lessons lessons = new Lessons();
        lessons.setLessonsName(lessonsDTO.getLessonsName());
        lessons.setHours(lessonsDTO.getHours());
        lessons.setDescription(lessonsDTO.getDescription());

        lessonsRepo.save(lessons);
        return ResponseEntity.ok(lessons);
    }

    // Xem nội dung bài học
    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Lessons> opLessons = lessonsRepo.findById(id);
        Optional<LessonsDTO> opLessonsDTO = lessonsRepo.findById(id).map(lessons -> {
            LessonsDTO lessonsDTO = new LessonsDTO();
            lessonsDTO.setId(lessons.getId());
            lessonsDTO.setLessonsName(lessons.getLessonsName());
            lessonsDTO.setHours(lessons.getHours());
            lessonsDTO.setDescription(lessons.getDescription());
            return lessonsDTO;
        });
        if (opLessonsDTO.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại");
        }
        return ResponseEntity.ok(opLessonsDTO);
    }

    //Cập nhật bài học
    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody LessonsDTO lessonsDTO) {
        Optional<Lessons> opLessons = lessonsRepo.findById(id);
        if (opLessons.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại");
        } else if (lessonsDTO.getLessonsName() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền tên bài học");
        } else if (lessonsDTO.getHours() == 0) {
            return ResponseEntity.badRequest().body("Cần phải điền số giờ");
        }else if (lessonsDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền mô tả");
        }
        Lessons lessons = opLessons.get();
        lessons.setLessonsName(lessonsDTO.getLessonsName());
        lessons.setHours(lessonsDTO.getHours());
        lessons.setDescription(lessonsDTO.getDescription());
        lessonsRepo.save(lessons);
        return ResponseEntity.ok( "cập nhật thành công ");
    }

    //Xoá bài học
    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Lessons> opLessons = lessonsRepo.findById(id);
        if (opLessons.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại");
        }
        lessonsRepo.deleteById(id);
        return ResponseEntity.ok("Xoá thành công");

    }

}
