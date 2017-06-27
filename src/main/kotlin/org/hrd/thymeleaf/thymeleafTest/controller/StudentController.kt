package org.hrd.thymeleaf.thymeleafTest.controller

import org.hrd.thymeleaf.thymeleafTest.Student
import org.hrd.thymeleaf.thymeleafTest.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import java.sql.Date

/**
 * Created by ratha on 23-Jun-17.
 */
@Controller
@RequestMapping("/student")
class StudentController {

    @Autowired
    private lateinit var studentRepository  : StudentRepository;

    /*========================================
     * RestFul
     =========================================*/
    @ResponseBody
    @GetMapping("/info")
    fun getStudents() : MutableList<Student>{
        val list : MutableList<Student> =arrayListOf<Student>()
        //list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))
        //list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))

        return list;
    }


    @ResponseBody
    @GetMapping("/get-edit")
    fun getAStudentEdit(model:ModelMap , @RequestParam("id") id : Int) : ResponseEntity<MutableMap<String ,Any>>{
        val map : MutableMap<String ,Any>  = hashMapOf()
        try{
            val student = studentRepository.getOneStudent(id)
            if (student!=null){
                map.put("DATA" , student as Object)
                map.put("CODE", 200 as Object)
                map.put("STATUS" , true as Object)
                map.put("MESSAGE" , "GET STUDENT SUCCESSFULL" as Object)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return ResponseEntity<MutableMap<String ,Any>>(map ,HttpStatus.OK);
    }

    /*========================================
     * simple controller
     =========================================*/
    @GetMapping("/")
    fun homePage(model : ModelMap) : String{
        val list : MutableList<Student> =this.studentRepository.getAllStudents()
        model.put("students",list);
        return "index"
    }
    @GetMapping("/add")
    fun addStudent() : String {

        return "frm_add_student"
    }

    @GetMapping("/detail/{id}")
    fun getOneStudent(model:ModelMap , @PathVariable("id") id: Int) :String{
        val student =this.studentRepository.getOneStudent(id)
        model.put("student",student)
    return "student-detail"
    }

    @PostMapping("/edit")
    fun editStudent(model: ModelMap ) : String{

        return ""
    }

    @GetMapping("/remove/{id}")
    fun removeStudent(model: ModelMap , @PathVariable("id") id : Int) : String{
         if(this.studentRepository.removeStudent(id)) return "redirect:/student/"
         else {
             model.put("error" , false)
             return ""
         }
    }

}