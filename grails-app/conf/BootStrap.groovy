import com.bookservice.SecRole
import com.bookservice.SecUser
import com.bookservice.Book

class BootStrap {

    def init = { servletContext ->

        //Roles
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true, flush: true)

        assert 1 == SecRole.count()


        //Add a user
        if (!SecUser.findByUsername('user')) {
            def basicUser =  new SecUser(
                    username: 'user',
                    password: 'password',
                    apiKey: '1234567890',
                    firstName: 'John',
                    surname: 'Doe',
                    enabled: true).save(failOnError: true, flush: true)


            basicUser.addToAuthorities(userRole)
        }

        assert 1 == SecUser.count()

        new Book(title: 'Hackers & Painters', author: 'Paul Graham', isbn: '978-0-596-00662-4').save(flush: true, failOnError: true)
        new Book(title: 'On The Edge', author: 'Brian Bagnall', isbn: '0-9738649-0-7').save(flush: true, failOnError: true)
        new Book(title: 'Introduction To Algorithms', author: 'Thomas H. Cormen', isbn: '0-262-53196-8').save(flush: true, failOnError: true)

        assert 3 == Book.count()




    }
    def destroy = {
    }
}
