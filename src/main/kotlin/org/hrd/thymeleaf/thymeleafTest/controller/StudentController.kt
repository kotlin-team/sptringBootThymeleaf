package org.hrd.thymeleaf.thymeleafTest.controller

import org.hrd.thymeleaf.thymeleafTest.entity.Student
import org.hrd.thymeleaf.thymeleafTest.repository.StudentRepository
import org.hrd.thymeleaf.thymeleafTest.util.MemoryPagination
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
                map.put("MESSAGE" , "GET STUDENT SUCCESSFUL" as Object)
            }
        }catch (e: Exception){
            e.printStackTrace()
        }

        return ResponseEntity<MutableMap<String ,Any>>(map ,HttpStatus.OK);
    }

    /*
        getStudent as paginate
     */

    @GetMapping("/{p}")
    fun getStudentPerPage(model : ModelMap,@PathVariable("p") currentPage:Int) : String
    {
        val list= studentRepository.getAllStudentsWithPaginate(currentPage,5)
        val pagin=
        model.put("students",list)
        model.put("title" , "home | ")
        model.put("paging", MemoryPagination<Student>().getMemoryPagination(currentPage,5,list))
        return "index :: studentFragment"
    }


   /* @ResponseBody
    @GetMapping("/{p}")
    fun getStudentPerPage(model : ModelMap,@PathVariable("p") currentPage:Int) : ResponseEntity<MutableMap<String ,Any>>
    {
        val map : MutableMap<String ,Any>  = hashMapOf()
        try{
            val pagination = MemoryPagination<Student>().getMemoryPagination(currentPage,3,this.studentRepository.getAllStudents())
            val list : MutableList<Student> =this.studentRepository.
                    getAllStudentsWithPaginate(pagination.getStartRow(), pagination.getEndRow())
            if (list.isEmpty() || list!=null){
                map.put("DATA" , list)
                map.put("PAGINATION" , pagination)
                map.put("CODE", 200 )
                map.put("STATUS" , true)
                map.put("MESSAGE" , "GET STUDENT LIST SUCCESSFUL")
            }else{
                map.put("DATA" , list as Object)
                map.put("PAGINATION" , pagination)
                map.put("CODE", 200 as Object)
                map.put("STATUS" , true as Object)
                map.put("MESSAGE" , "GET STUDENT LIST SUCCESSFUL" )
            }
        }catch (e: Exception){
            map.put("DATA" , "")
            map.put("PAGINATION" , "")
            map.put("CODE", 200 )
            map.put("STATUS" , true)
            map.put("MESSAGE" , "HAVE AN ERROR WHILE REQUEST TO SERVER")
            e.printStackTrace()
        }
        return ResponseEntity<MutableMap<String ,Any>>(map ,HttpStatus.OK);
    }*/


    /*========================================
     * simple controller
     =========================================*/
    @GetMapping(value = *arrayOf("/",""))
    fun homePage(model : ModelMap) : String
    {
        val list : MutableList<Student> =this.studentRepository.getAllStudentsWithPaginate(startRow = 1, endRow = 5)
        model.put("students",list);
        model.put("title" , "home | ")
        model.put("paging",MemoryPagination<Student>().getMemoryPagination(1,5,list))
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
    @GetMapping("edit/{id}")
    fun getStudentEdit(model : ModelMap, @PathVariable("id") id : Int) : String
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

    //bind data with html page
    private fun studentsPaginate(page :Int , dataPerRow: Int ) : String {
        val list = studentRepository.getAllStudentsWithPaginate(page,dataPerRow)
        var totalData =""
        var stduentRow= (page -1)*dataPerRow +1
        list.forEach {
            totalData +=
                    "<tr>\n" +
                    "    <td >${stduentRow}</td>\n" +
                    "    <td><a href=\"/student/detail/${it.studentId}\" target=\"_self\"><span  th:text=\"\">${it.studentName}</span></a></td>\n" +
                    "    <td >${it.studentGender}</td>\n" +
                    "    <td >${it.studentDob}</td>\n" +
                    "    <td >${it.studentPob}</td>\n" +
                    "    <td>\n" +
                    "    <a class=\"btn btn-floating waves-effect waves-light yellow darken-1\" href=\"/student/edit/${it.studentId}\" ><i class=\"large material-icons\">mode_edit</i></a>\n" +
                    "    <a class=\"btn btn-floating waves-effect waves-light red\" href=\"/student/remove/${it.studentId}\"><i class=\" fa fa-trash-o\"></i></a>\n" +
                    "    </td>\n" +
                    "    </tr> ${list.size}"
            stduentRow ++
        }

        return totalData;
    }


}