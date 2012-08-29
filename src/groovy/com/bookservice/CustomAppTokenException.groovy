package com.bookservice

import org.springframework.security.core.AuthenticationException

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 26/08/12
 * Time: 8:21 PM
 * To change this template use File | Settings | File Templates.
 */
class CustomAppTokenException extends AuthenticationException {

    public CustomAppTokenException(String msg) {
        super(msg)
    }

}
