package service;

import com.data.entity.Courses;

import java.util.List;

public interface CoursesService {
    List<Courses> getListCoursesByCoursesName(String coursesName);
}
