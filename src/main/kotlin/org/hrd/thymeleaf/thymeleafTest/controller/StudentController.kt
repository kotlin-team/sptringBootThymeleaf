package org.hrd.thymeleaf.thymeleafTest.controller

import org.hrd.thymeleaf.thymeleafTest.Student
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

/**
 * Created by ratha on 23-Jun-17.
 */
@Controller
@RequestMapping("/student")
class StudentController {

    @ResponseBody
    @GetMapping("/info")
    fun getStudents() : MutableList<Student>{
        val list : MutableList<Student> =arrayListOf<Student>()
        list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))
        list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))

        return list;
    }

    @GetMapping("/")
    fun homePage(model : ModelMap) : String{
        model.addAttribute("header","My Header Message")
        return "index"
    }
}