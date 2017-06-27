package org.hrd.thymeleaf.thymeleafTest.repository

import org.hrd.thymeleaf.thymeleafTest.Student
import org.springframework.stereotype.Component
import java.sql.Date

/**
 * Created by ratha on 26-Jun-17.
 */
@Component
class StudentRepository {

    private val studentLists= arrayListOf<Student>(
            Student(studentId = 1,studentName = "Polen" ,studentGender = "other" , studentDob = Date.valueOf("1995-02-14"),studentPob = "Phnom Penh"),
            Student(studentId = 2,studentName = "rathana" ,studentGender = "male" , studentDob = Date.valueOf("1993-10-25"),studentPob = "Phnom Penh"),
            Student(studentId = 3,studentName = "veyo" ,studentGender = "male" , studentDob = Date.valueOf("1995-05-25"),studentPob = "Phnom Penh"),
            Student(studentId = 4,studentName = "Ornrany" ,studentGender = "femal" , studentDob = Date.valueOf("1996-02-14"),studentPob = "Phnom Penh"),
            Student(studentId = 5,studentName = "Saravith" ,studentGender = "male" , studentDob = Date.valueOf("1995-05-15"),studentPob = "Phnom Penh"),
            Student(studentId = 6,studentName = "Sothearin" ,studentGender = "female" , studentDob = Date.valueOf("1996-01-25"),studentPob = "Phnom Penh"),
            Student(studentId = 7,studentName = "Vansa" ,studentGender = "male" , studentDob = Date.valueOf("1994-02-14"),studentPob = "Phnom Penh"),
            Student(studentId = 8,studentName = "Channy" ,studentGender = "other" , studentDob = Date.valueOf("1993-09-19"),studentPob = "Phnom Penh"),
            Student(studentId = 9,studentName = "Fickkry" ,studentGender = "male" , studentDob = Date.valueOf("1994-10-17"),studentPob = "Phnom Penh"),
            Student(studentId = 10,studentName = "Sevmey" ,studentGender = "female" , studentDob = Date.valueOf("1996-12-19"),studentPob = "Phnom Penh")
    )

    fun getAllStudents() : MutableList<Student>{
        return this.studentLists;
    }
    fun getOneStudent(id: Int) : Student?{
        for (student: Student in this.studentLists){
            if(student!!.studentId==id) {
                return student
            }
        }
        return null
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
}