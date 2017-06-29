package org.hrd.thymeleaf.thymeleafTest.controller

import org.hrd.thymeleaf.thymeleafTest.entity.Student
import org.hrd.thymeleaf.thymeleafTest.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.sql.Date
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.validation.Valid

/**
 * Created by ratha on 23-Jun-17.
 */
@Controller
@RequestMapping("/student")
class StudentController
{
    @Autowired
    private lateinit var studentRepository  : StudentRepository;

    /*========================================
     * RestFul
     =========================================*/
    @ResponseBody
    @GetMapping("/info")
    fun getStudents() : MutableList<Student>
    {
        val list : MutableList<Student> = arrayListOf<Student>()
        //list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))
        //list.add(Student(studentName = "test" ,studentGender = "male", studentDob = Date.valueOf("1995-12-12"),studentPob = "Phmone Penh"))
        return list;
    }
    @ResponseBody
    @GetMapping("/get-edit")
    fun getAStudentEdit(model:ModelMap , @RequestParam("id") id : Int) : ResponseEntity<MutableMap<String ,Any>>
    {
        val map : MutableMap<String ,Any>  = hashMapOf()
        try{
            val student = studentRepository.getOneStudent(id)
            println(student.toString())
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
    @GetMapping(value = *arrayOf("/",""))
    fun homePage(model : ModelMap) : String
    {
        val list : MutableList<Student> =this.studentRepository.getAllStudents()
        model.put("students",list);
        model.put("title" , "home | ")

        return "index"
    }
    @GetMapping("/add")
    fun addStudent( model : ModelMap) : String {
        model.put("title", "register")
        return "frm-add-student"
    }

    //save student
    @PostMapping("/add")
    fun saveStudent(@RequestParam("name") name : String?,
                    @RequestParam("gender") gender: String?,
                    @RequestParam("dob")  dob: String?,
                    @RequestParam("pob")  pob: String?) : String
    {
        val dateFormat :DateFormat = SimpleDateFormat("dd MMM,yyyy")
        val date : java.util.Date = dateFormat.parse(dob)
        val sDob= Date(date.time)
        val student =Student(name!!,gender!!,sDob!!,pob!!)
        this.studentRepository.createStudent(student)
        return "redirect:/student/"
    }


    @GetMapping("/detail/{id}")
    fun getOneStudent(model:ModelMap , @PathVariable("id") id: Int) :String
    {
        val student =this.studentRepository.getOneStudent(id)
        model.put("student",student)
        model.put("title", "student Detail")
    return "student-detail"
    }


    /*
    * get a student to from edit */
    @GetMapping("{id}")
    fun homePage(model : ModelMap, @PathVariable("id") id : Int) : String
    {
        val student=this.studentRepository.getOneStudent(id)
        model.put("student",student);
        model.put("title", "edit student info")
        return "frm-edit-student"
    }
    /*save new student edited*/
    @PostMapping("/edit")
    fun editStudent(@RequestParam("studentId") id : Int,
                    @RequestParam("studentName") name : String,
                    @RequestParam("studentGender") gender : String,
                    @RequestParam("studentDob") dob : String,
                    @RequestParam("studentPob") pob : String) : String
    {
        var student: Student
        try{
            student =Student(id,name,gender,this.SqlDateFormater(date=dob,datePattern = "dd MMM,yyyy"),pob)
        }catch (e :Exception){
            student =Student(id,name,gender,Date.valueOf(dob),pob)
            //e.printStackTrace()
        }
        if(this.studentRepository.editStudent(student))
            return "redirect:/student/"
        else {
            return ""
        }
        return ""
    }

    @GetMapping("/remove/{id}")
    fun removeStudent(model: ModelMap , @PathVariable("id") id : Int) : String
    {
         if(this.studentRepository.removeStudent(id)) return "redirect:/student/"
         else {
             model.put("error" , false)
             return ""
         }
    }

    protected fun SqlDateFormater(date :String , datePattern :String) : Date
    {
        val dateFormat :DateFormat = SimpleDateFormat(datePattern)
        val utilDate : java.util.Date = dateFormat.parse(date)
        return  Date(utilDate.time)
    }
}