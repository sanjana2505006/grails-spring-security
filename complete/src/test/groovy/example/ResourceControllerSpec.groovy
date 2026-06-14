package example

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class ResourceControllerSpec extends Specification implements ControllerUnitTest<ResourceController>, DataTest {

    Class<?>[] getDomainClassesToMock() {
        [Resource, User, Role, UserRole] as Class[]
    }

    void 'index lists resources without authentication'() {
        given:
        new Resource(name: 'Public').save(flush: true)

        when:
        controller.index()

        then:
        model.resourceList
        model.resourceCount == 1
    }

    void 'show returns 404 when resource missing'() {
        when:
        controller.show(99L)

        then:
        response.status == 404
    }
}
