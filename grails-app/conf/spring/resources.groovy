import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint
import com.bookservice.CustomAppTokenFilter
import com.bookservice.CustomAppTokenAuthenticationProvider

// Place your Spring DSL code here
beans = {


    customAuthenticationEntryPoint(Http403ForbiddenEntryPoint) { }

    customAppTokenFilter(CustomAppTokenFilter) {
        authenticationManager = ref("authenticationManager")
        customProvider = ref("customAppTokenAuthenticationProvider")
        authenticationEntryPoint = ref("customAuthenticationEntryPoint")
    }

    customAppTokenAuthenticationProvider(CustomAppTokenAuthenticationProvider) {
        userDetailsService = ref("userDetailsService")
    }


}
