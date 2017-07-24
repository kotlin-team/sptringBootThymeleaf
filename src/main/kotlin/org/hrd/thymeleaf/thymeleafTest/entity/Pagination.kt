package org.hrd.thymeleaf.thymeleafTest.entity

data class Pagination(var currentPage: Int , var totalCount: Int)
{
    var rowPerPage: Int=10
    var startPage : Int =0
    var endPage: Int =0
    var totalPages: Int =0
    var pagesToShow : Int=5

    var nextPage : Int=0
    var PreviousPage : Int=0

    constructor( currentPage : Int, rowPerPage : Int , totalCount: Int , pagesToShow: Int) :
            this( currentPage=currentPage,totalCount=totalCount)
    {
        this.pagesToShow=pagesToShow
        this.rowPerPage=rowPerPage
    }

    internal fun setCurrentPage( currentPage: Int){
        this.currentPage= if(currentPage<=1) 1 else currentPage
    }

    internal fun getTotalPages() : Int {
        return  Math.ceil( (this.totalCount/rowPerPage).toDouble()).toInt()
    }

    internal fun getNextPage() : Int { if(currentPage >= getTotalPage())  return getTotalPage() else  return currentPage+1 }
    internal fun getPreviousPage() : Int {if(currentPage<=1) return 1 else return currentPage-1}
    internal fun getRowPerPage() : Int = this.rowPerPage
    internal fun getTotalPage() : Int = this.totalPages
    internal fun getCurrentPage() : Int = this.currentPage
    internal fun getStartRow() : Int = this.startPage
    internal fun getEndRow() : Int = this.endPage
    internal fun getPagesToShow(): Int = this.pagesToShow
    internal fun getTotalCount() : Int =this.totalCount

    internal fun setTotalCount(totalCount : Int){
        this.totalCount=totalCount
        this.setStartPageEndPage(this.totalCount)
    }

    private fun setStartPageEndPage(totalPages :Int){

        var halfPagesToShow = pagesToShow / 2;

        if (totalPages <= pagesToShow) {
            startPage = 1;
            endPage = totalPages;

        } else if (currentPage - halfPagesToShow <= 0) {
            startPage = 1;
            endPage = pagesToShow;

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
}