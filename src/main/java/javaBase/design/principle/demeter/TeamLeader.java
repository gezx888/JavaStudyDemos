package javaBase.design.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: Teacher
 * @description: TODO 类描述
 * @author: gezx
 * @date: 2021/3/2
 **/
public class TeamLeader {
    // V1版
    public void checkNumberOfCourse(List<Course> courseList){
        System.out.println(courseList.size());
    }

    public void checkNumberOfCourse(){
        List<Course> courseList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            courseList.add(new Course());
        }
        System.out.println(courseList.size());
    }


}
