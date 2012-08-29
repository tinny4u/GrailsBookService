package com.bookservice

class Book {

    String title
    String author
    String isbn

    static constraints = {
        title blank: false
        author blank: false
        isbn blank: false
    }
}
