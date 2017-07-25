package org.hrd.thymeleaf.thymeleafTest.util

import org.hrd.thymeleaf.thymeleafTest.entity.Pagination
import org.hrd.thymeleaf.thymeleafTest.entity.Student
import org.hrd.thymeleaf.thymeleafTest.repository.StudentRepository

/**
 * Created by ratha on 29-Jun-17.
 */
class MemoryPagination <E> {

    fun getMemoryPagination (currentPage : Int ,rowPerPage : Int , list: MutableList<E>) : Pagination{
       /* var total : Double = list.size.toDouble()/rowPerPage
        val totalPage= Math.ceil(total).toInt()
        //calculate start pageNumber and lastPage Number

        var current=currentPage
        if(current<1) current=1
        if(current > totalPage) current =totalPage

        val startRow=(current-1)*rowPerPage
        var endRow =0
        if(startRow+rowPerPage > list.size) endRow =list.size else endRow=startRow+rowPerPage
*/
        val pagination= Pagination(currentPage ,rowPerPage)
        pagination.setTotalCount(list.size)
        print(pagination.toString())
        return  pagination
    }
}
