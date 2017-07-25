package org.hrd.thymeleaf.thymeleafTest.entity

import com.fasterxml.jackson.annotation.JsonIgnore

data class Pagination(var currentPage: Int=1, var rowPerPage: Int=10)
{
    var totalCount: Int=0
    var startPage : Int =0
    var endPage: Int =0
    var totalPages: Int =0
    var pagesToShow : Int=5

    var nextPage : Int=0
    var previousPage : Int=0

    @JsonIgnore
    var offset : Int=0

    constructor( currentPage : Int, rowPerPage : Int , totalCount: Int,totalPages :Int , pagesToShow: Int ) :
            this( currentPage=currentPage,rowPerPage = rowPerPage)
    {
        this.pagesToShow=pagesToShow
        this.totalCount=totalCount
        this.totalPages=totalPages
    }

    internal fun getRowPerPage() : Int = this.rowPerPage
    internal fun getCurrentPage() : Int = this.currentPage
    internal fun getStartRow() : Int = this.startPage
    internal fun getEndRow() : Int = this.endPage
    internal fun getPagesToShow(): Int = this.pagesToShow
    internal fun getTotalCount() : Int =this.totalCount

    internal fun setCurrentPage( currentPage: Int){
        this.currentPage= if(currentPage<=1) 1 else currentPage
    }
    internal fun getTotalPages() : Int {
        //println("totalCount ${this.totalCount} rowPerPage ${this.getRowPerPage()}")
        return  Math.ceil( (this.totalCount/this.getRowPerPage()).toDouble()).toInt()
    }

    internal fun getNextPage() : Int { if(currentPage >= getTotalPages())  return getTotalPages() else  return currentPage+1 }
    internal fun getPreviousPage() : Int {if(currentPage<=1) return 1 else return currentPage-1}

    internal fun setTotalCount(totalCount : Int){
        this.totalCount=totalCount
        this.totalPages=this.getTotalPages()
        this.setStartPageEndPage(this.totalPages)
        this.nextPage=getNextPage()
        this.previousPage=getPreviousPage()
       // println(" NextPage Number:  ${getNextPage()}")
    }
    internal fun getOffset(): Int  {return (this.currentPage-1) * this.rowPerPage }

    private fun setStartPageEndPage(totalPages :Int){

        var halfPagesToShow = pagesToShow / 2;

        if (totalPages <= pagesToShow) {
            startPage = 1;
            endPage = totalPages;
            print("totalpage : ${totalPages} Start page : ${startPage} endPage: ${endPage}")

        } else if (currentPage - halfPagesToShow <= 0) {
            startPage = 1;
            endPage = pagesToShow;
            print("currentPage - halfPagesToShow (Start page : ${startPage} endPage: ${endPage})")

        } else if (currentPage + halfPagesToShow == totalPages) {
            startPage = currentPage - halfPagesToShow;
            endPage = totalPages;

        } else if (currentPage + halfPagesToShow > totalPages) {
            startPage = totalPages - pagesToShow + 1;
            endPage = totalPages;

        } else {
            startPage = currentPage - halfPagesToShow;
            endPage = currentPage + halfPagesToShow;
        }
    }

    override fun toString(): String {
        return "Pagination(currentPage=$currentPage, totalCount=$totalCount, rowPerPage=$rowPerPage, startPage=$startPage, endPage=$endPage, totalPages=$totalPages, pagesToShow=$pagesToShow, nextPage=$nextPage, PreviousPage=$previousPage)"
    }


}