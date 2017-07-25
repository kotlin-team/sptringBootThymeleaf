package org.hrd.thymeleaf.thymeleafTest.entity

import java.sql.Date
data class Student(
                   var studentName: String,
                   var studentGender:String,
                   var studentDob: Date,
                   var studentPob:String)
{

    var studentId : Int = 0

    constructor ( studentId : Int, studentName: String , studentGender: String, studentDob: Date, studentPob: String) :
            this(studentName,studentGender,studentDob,studentPob) {
            this.studentId=studentId
    }

    fun getId() :Int = this.studentId
    fun getName() : String =this.studentName
    fun getGender(): String =this.studentGender
    fun getDob(): Date =this.studentDob
    fun getPob(): String =this.studentPob

    override fun toString(): String {
        return "Student(studentId= ${studentId} studentName='$studentName', studentGender='$studentGender', studentDob=$studentDob, studentPob='$studentPob')"
    }

}