package foo

import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import grails.util.Holders
import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.io.ClassPathResource
import spock.lang.Specification

@Integration
@Rollback
@Slf4j
class ContextLoaderServiceSpec extends Specification {

    @Autowired
    ContextLoaderService contextLoaderService

    void "test managing child app context"() {

        given:
            ClassPathResource myAppCtx = new ClassPathResource("AppCtx.xml")
            GenericApplicationContext context = contextLoaderService.loadContext(myAppCtx)

        when:
            MyBean bean = context.getBean("myBean", MyBean)

        then:
            bean != null
            Holders.getGrailsApplication() != null

        when:
            context.close()

        then:
            Holders.getGrailsApplication() != null

    }
}
