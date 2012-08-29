package com.bookservice

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.Authentication

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 25/08/12
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
class CustomAppTokenAuthentication implements Authentication {

    String name
    Set<GrantedAuthority> authorities
    Object credentials
    Object details
    Object principal
    boolean authenticated

}
