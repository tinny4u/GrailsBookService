package com.bookservice

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException

/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 25/08/12
 * Time: 12:34 AM
 * To change this template use File | Settings | File Templates.
 */
class CustomAppTokenAuthenticationProvider implements AuthenticationProvider {

    def userDetailsService

    public Authentication authenticate(Authentication customAuth) throws AuthenticationException {

        def userDetails = userDetailsService.loadUserByUsername(customAuth.principal)

        if (userDetails?.domainClass?.apiKey == customAuth.credentials) {

            customAuth.authorities = userDetails.authorities
            return customAuth

        } else {

            throw new CustomAppTokenException('Login failed, user credentials not valid')

        }
    }

    //TODO
    boolean supports(Class authentication) {
        return CustomAppTokenAuthentication.class.isAssignableFrom(authentication)
    }

}
