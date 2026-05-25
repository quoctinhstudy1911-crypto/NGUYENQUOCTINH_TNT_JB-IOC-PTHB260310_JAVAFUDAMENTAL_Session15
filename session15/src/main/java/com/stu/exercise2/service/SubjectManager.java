package com.stu.exercise2.service;

import com.stu.exercise2.entity.Subject;
import java.util.ArrayList;

public class SubjectManager<T extends Subject> {

    private ArrayList<T> subjects = new ArrayList<>();

    // Thêm môn học
    public void addSubject(T subject) {
        subjects.add(subject);
    }

    // Hiển thị danh sách
    public void displaySubjects() {

        if(subjects.isEmpty()) {
            System.out.println("Danh sách môn học trống");
            return;
        }
        subjects.forEach(System.out::println);
    }

    // Xóa theo code
    public void removeSubjectByCode(String code) {

        boolean removed = subjects.removeIf(subject -> subject.getCode().equalsIgnoreCase(code));

        if(removed) {
            System.out.println("Xóa môn học thành công");
        }
        else {
            System.out.println("Không tìm thấy môn học");
        }
    }

    // Tìm theo tên
    public void findByName(String name)
    {
        boolean found = false;
        for (T subject : subjects)
        {
            if(subject.getName().equalsIgnoreCase(name))
            {
                System.out.println(subject);
                found = true;
            }
        }

        if(!found)
        {
            System.out.println("Không có môn học phù hợp");
        }
    }

    // Lọc credits > 3
    public void filterCreditsGreaterThan3()
    {
        boolean found = false;
        for (T subject : subjects)
        {
            if(subject.getCredits() > 3)
            {
                System.out.println(subject);
                found = true;
            }
        }

        if(!found)
        {
            System.out.println("Không có môn học phù hợp");
        }
    }
}