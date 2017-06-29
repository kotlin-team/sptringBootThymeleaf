package org.hrd.thymeleaf.thymeleafTest.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.sql.Date
import javax.persistence.PersistenceProperty
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations


data class Student(
                   @SerializedName("STUDENT_NAME") var studentName: String,
                   @SerializedName("STUDENT_GENDER") var studentGender:String,
                   @SerializedName("STUDENT_DOB") var studentDob: Date,
                   @SerializedName("STUDENT_POB") var studentPob:String)
{

    @SerializedName("STUDENT_ID") var studentId : Int = 0
    @SerializedName("PAGINATION") lateinit var pagination : Pagination

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