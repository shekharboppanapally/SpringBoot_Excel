package com.tc.excel.service;

import com.tc.excel.entity.Student;
import com.tc.excel.helper.Helper;
import com.tc.excel.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void save(MultipartFile file){
        try{
            List<Student> students= Helper.convertExcelToListOfStudent(file.getInputStream());
            studentRepository.saveAll(students);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
