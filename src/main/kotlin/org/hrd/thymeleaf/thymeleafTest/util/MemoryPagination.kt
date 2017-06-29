package org.hrd.thymeleaf.thymeleafTest.util

/**
 * Created by ratha on 29-Jun-17.
 */
class MemoryPagination <E> {

    fun getMenoryPagination (rowPerPage : Int, list: MutableList<E>) : MutableList<E>{
        var pageNumber = list.size/rowPerPage

        ///pageNumber= Math.ceil(pageNumber) as Int;
        return list
    }
}