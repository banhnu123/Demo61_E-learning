package com.data.controller;

import com.data.entity.Courses;
import com.data.repository.CoursesRepository;
import dto.CoursesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@RestController
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepo;


    //lay ds khoá học
    @GetMapping("courses")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Courses> courses = coursesRepo.findAll(pageable);
        Page<CoursesDTO> coursesDTOs = courses.map(course -> {
            CoursesDTO coursesDTO = new CoursesDTO();
            coursesDTO.setId(course.getId());
            coursesDTO.setCoursesName(course.getCoursesName());
            coursesDTO.setHours(course.getHours());
            coursesDTO.setSessions(course.getSessions());
            coursesDTO.setDescription(course.getDescription());
            return coursesDTO;
        });
        return ResponseEntity.ok(coursesDTOs);
    }

    //lấy chi tiết khoá học
    @GetMapping("courses/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<Courses> opCourses = coursesRepo.findById(id);
        Optional<CoursesDTO> opCoursesDTO = coursesRepo.findById(id).map(course -> {
            CoursesDTO coursesDTO = new CoursesDTO();
            coursesDTO.setId(course.getId());
            coursesDTO.setCoursesName(course.getCoursesName());
            coursesDTO.setHours(course.getHours());
            coursesDTO.setSessions(course.getSessions());
            coursesDTO.setDescription(course.getDescription());
            return coursesDTO;
        });
        if (opCoursesDTO.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại" );
        }
        return ResponseEntity.ok(opCoursesDTO);
    }
    // tạo khoá học
    @PostMapping("courses")
    public ResponseEntity<?> create(@RequestBody CoursesDTO coursesDTO) {
        if (coursesDTO.getCoursesName() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền tên khoá học");
        }
        if (coursesDTO.getHours() == 0) {
            return ResponseEntity.badRequest().body("Cần phải điền số giờ");
        }
        if (coursesDTO.getSessions() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền số buổi");
        }
        if (coursesDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền mô tả");
        }
        Courses courses = new Courses();
        courses.setCoursesName(coursesDTO.getCoursesName());
        courses.setHours(coursesDTO.getHours());
        courses.setSessions(coursesDTO.getSessions());
        courses.setDescription(coursesDTO.getDescription());
        coursesRepo.save(courses);
        return ResponseEntity.ok("Tạo khoá học thành công" + courses);
    }

    // cập nhật khoá học
    @PutMapping("courses/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody CoursesDTO coursesDTO) {
        Optional<Courses> opCourses = coursesRepo.findById(id);
        if (opCourses.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại" );
        }else if (coursesDTO.getCoursesName() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền tên khoá học");
        }else if (coursesDTO.getHours() == 0) {
            return ResponseEntity.badRequest().body("Cần phải điền số giờ");
        }else if (coursesDTO.getSessions() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền số buổi");
        }else if (coursesDTO.getDescription() == null) {
            return ResponseEntity.badRequest().body("Cần phải điền mô tả");
        }
        Courses courses = opCourses.get();
        courses.setCoursesName(coursesDTO.getCoursesName());
        courses.setHours(coursesDTO.getHours());
        courses.setSessions(coursesDTO.getSessions());
        courses.setDescription(coursesDTO.getDescription());
        coursesRepo.save(courses);
        return ResponseEntity.ok("Cập nhật khoá học thành công" + courses);


    }

    // xoá khoá học
    @DeleteMapping("courses/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<Courses> opCourses = coursesRepo.findById(id);
        if (opCourses.isEmpty()) {
            return ResponseEntity.badRequest().body("Id không tồn tại" );
        }
        coursesRepo.deleteById(id);
        return ResponseEntity.ok("Xoá khoá học thành công" );
    }
    //Tìm khoá học theo từ khoá

}
