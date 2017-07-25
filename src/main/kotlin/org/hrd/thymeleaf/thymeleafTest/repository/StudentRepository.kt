package org.hrd.thymeleaf.thymeleafTest.repository

import org.hrd.thymeleaf.thymeleafTest.entity.Pagination
import org.hrd.thymeleaf.thymeleafTest.entity.Student
import org.hrd.thymeleaf.thymeleafTest.util.MemoryPagination
import org.springframework.stereotype.Component
import java.sql.Date

/**
 * Created by ratha on 26-Jun-17.
 */
@Component
class StudentRepository {

    private val studentLists= arrayListOf<Student>(
            Student(studentId = 1, studentName = "Polen", studentGender = "other", studentDob = Date.valueOf("1995-02-14"), studentPob = "Phnom Penh"),
            Student(studentId = 2, studentName = "rathana", studentGender = "male", studentDob = Date.valueOf("1993-10-25"), studentPob = "Phnom Penh"),
            Student(studentId = 3, studentName = "veyo", studentGender = "male", studentDob = Date.valueOf("1995-05-25"), studentPob = "Phnom Penh"),
            Student(studentId = 4, studentName = "Ornrany", studentGender = "female", studentDob = Date.valueOf("1996-02-14"), studentPob = "Phnom Penh"),
            Student(studentId = 5, studentName = "Saravith", studentGender = "male", studentDob = Date.valueOf("1995-05-15"), studentPob = "Phnom Penh"),
            Student(studentId = 6, studentName = "Sothearin", studentGender = "female", studentDob = Date.valueOf("1996-01-25"), studentPob = "Phnom Penh"),
            Student(studentId = 7, studentName = "Vansa", studentGender = "male", studentDob = Date.valueOf("1994-02-14"), studentPob = "Phnom Penh"),
            Student(studentId = 8, studentName = "Channy", studentGender = "other", studentDob = Date.valueOf("1993-09-19"), studentPob = "Phnom Penh"),
            Student(studentId = 9, studentName = "Fickkry", studentGender = "male", studentDob = Date.valueOf("1994-10-17"), studentPob = "Phnom Penh"),
            Student(studentId = 10, studentName = "Sevmey", studentGender = "female", studentDob = Date.valueOf("1996-12-19"), studentPob = "Phnom Penh") ,
            Student(studentId = 11, studentName = "Sothearin", studentGender = "female", studentDob = Date.valueOf("1996-01-25"), studentPob = "Phnom Penh"),
            Student(studentId = 12, studentName = "Vansa", studentGender = "male", studentDob = Date.valueOf("1994-02-14"), studentPob = "Phnom Penh"),
            Student(studentId = 13, studentName = "Channy", studentGender = "other", studentDob = Date.valueOf("1993-09-19"), studentPob = "Phnom Penh"),
            Student(studentId = 14, studentName = "Fickkry", studentGender = "male", studentDob = Date.valueOf("1994-10-17"), studentPob = "Phnom Penh"),
            Student(studentId = 15, studentName = "Sevmey", studentGender = "female", studentDob = Date.valueOf("1996-12-19"), studentPob = "Phnom Penh"),
            Student(studentId = 16, studentName = "Sothearin", studentGender = "female", studentDob = Date.valueOf("1996-01-25"), studentPob = "Phnom Penh"),
            Student(studentId = 17, studentName = "Vansa", studentGender = "male", studentDob = Date.valueOf("1994-02-14"), studentPob = "Phnom Penh"),
            Student(studentId = 18, studentName = "Channy", studentGender = "other", studentDob = Date.valueOf("1993-09-19"), studentPob = "Phnom Penh"),
            Student(studentId = 19, studentName = "Fickkry", studentGender = "male", studentDob = Date.valueOf("1994-10-17"), studentPob = "Phnom Penh"),
            Student(studentId = 20, studentName = "Sevmey", studentGender = "female", studentDob = Date.valueOf("1996-12-19"), studentPob = "Phnom Penh")


    )

    fun getAllStudents() : MutableList<Student>{
        return this.studentLists
    }

    fun getAllStudentsWithPaginate(pagination: Pagination) : MutableList<Student>{

        val studentOut= this.studentLists.subList(pagination.getOffset(),this.limitList(pagination,this.studentLists))
        return studentOut;
    }
    fun getOneStudent(id: Int) : Student?{
        for(student: Student in this.studentLists){
            if(student!!.studentId==id) {
                return student
            }
        }
        return null
    }

    fun createStudent(student : Student) : Boolean {
        if(student!==null){

            this.studentLists.add(0, student)
            return true
        }else{
            return false
        }
    }

    fun editStudent(student : Student) : Boolean {
        for( i in 0..this.studentLists.size ){
            if(this.studentLists.get(i).getId() == student.getId() ){
                this.studentLists.set(i,student)
                return true;
            }
        }
        return false
    }
    fun removeStudent(id :Int): Boolean {
        for (student: Student in this.studentLists){
            if(student!!.studentId==id) {
                this.studentLists.remove(student)
                return true
            }
        }
        return false
    }

    fun getLastId() : Int {
        return this.studentLists.get(this.studentLists.size-1).getId()
    }

    fun limitList (pagination: Pagination, list : MutableList<Student>) :Int{
        if(pagination.getOffset()+pagination.getRowPerPage() >= list.size){
            return list.size
        }else{
            return pagination.getOffset()+pagination.getRowPerPage()
        }
    }
}