package org.hrd.thymeleaf.thymeleafTest

import java.sql.Date

data class Student(val  studentId : Int, val studentName: String ,val studentGender:String ,val studentDob: Date , val studentPob:String) {

    init {

    }

    override fun toString(): String {
        return "Student(studentName='$studentName', studentGender='$studentGender', studentDob=$studentDob, studentPob='$studentPob')"
    }

}