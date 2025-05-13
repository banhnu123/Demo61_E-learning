package dto;

import lombok.Data;

@Data
public class CoursesDTO {
    private int id;
    private String coursesName;
    private String sessions;
    private int hours;
    private String description;

}
