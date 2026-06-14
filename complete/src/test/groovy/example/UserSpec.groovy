package example

import grails.testing.gorm.DataTest
import spock.lang.Specification

class UserSpec extends Specification implements DataTest {

    Class<?>[] getDomainClassesToMock() {
        [User, Role, UserRole] as Class[]
    }

    void 'username is required'() {
        expect:
        !new User(password: 'secret').validate()
    }

    void 'username must be unique'() {
        given:
        new User(username: 'alice', password: 'secret').save(flush: true)

        expect:
        !new User(username: 'alice', password: 'other').validate()
    }
}
