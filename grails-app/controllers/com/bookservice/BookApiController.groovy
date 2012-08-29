package com.bookservice

import grails.converters.JSON

class BookApiController {

    def index() {
        redirect action: 'list'
    }

    def list() {

        render Book.list() as JSON

    }
}
