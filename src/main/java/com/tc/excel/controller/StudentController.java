package com.tc.excel.controller;

import com.tc.excel.entity.Student;
import com.tc.excel.helper.Helper;
import com.tc.excel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/students/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        if (Helper.checkExcelFotmat(file)) {
            studentService.save(file);
            return ResponseEntity.ok().body("File Saved SuccessFully");
        } else {
            return ResponseEntity.ok().body("File not Saved SuccessFully");
        }
    }
    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
}
