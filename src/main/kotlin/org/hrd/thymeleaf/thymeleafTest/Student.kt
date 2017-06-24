package org.hrd.thymeleaf.thymeleafTest

import java.sql.Date

/**
 * Created by ratha on 23-Jun-17.
 */
data class Student(val studentName: String ,val studentGender:String ,val studentDob: Date , val studentPob:String) {

    init {

    }

    override fun toString(): String {
        return "Student(studentName='$studentName', studentGender='$studentGender', studentDob=$studentDob, studentPob='$studentPob')"
    }

}