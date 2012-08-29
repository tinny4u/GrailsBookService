package com.bookservice


import org.springframework.beans.factory.*
import javax.servlet.*
import javax.servlet.http.*
import org.springframework.web.filter.GenericFilterBean
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
/**
 * Created with IntelliJ IDEA.
 * User: sean
 * Date: 25/08/12
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
class CustomAppTokenFilter extends GenericFilterBean implements InitializingBean {

    def authenticationManager
    def customProvider
    def authenticationEntryPoint

    //TODO
    int getOrder() {
        return FilterChainOrder.REMEMBER_ME_FILTER
    }

    //TODO
    void afterPropertiesSet() {
        def providers = authenticationManager.providers
        providers.add(customProvider)
        authenticationManager.providers = providers
    }



    private Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {

            def username = httpServletRequest.getParameter("username")
            def apiKey = httpServletRequest.getParameter("apiKey")

            def authentication

            //credentials provided, try and authenticate
            if ( username && apiKey ) {

                authentication = new CustomAppTokenAuthentication(
                        name: username,
                        credentials: apiKey,
                        principal: username,
                        authenticated: true
                )

                authentication = authenticationManager.authenticate(authentication);

                logger.info("Successfully Authenticated ${username} in object ${username}")

                return authentication

            } else {

                throw new CustomAppTokenException('Login credentials not provided')

            }

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        try {

            def authentication = attemptAuthentication(req, res)

            SecurityContextHolder.getContext().setAuthentication(authentication)
            chain.doFilter(req, res)

        } catch (AuthenticationException ex) {

            SecurityContextHolder.clearContext()
            authenticationEntryPoint.commence(req, res, ex)

        }

    }

}