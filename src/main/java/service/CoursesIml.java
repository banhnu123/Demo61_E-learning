package service;

import com.data.entity.Courses;
import com.data.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoursesIml implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepo;

    @Override
    public List<Courses> getListCoursesByCoursesName(String coursesName) {
        return coursesRepo.findByCoursesNameContaining(coursesName);
    }

}
