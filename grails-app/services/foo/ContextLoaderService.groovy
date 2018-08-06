package foo

import grails.util.Holders

import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import org.springframework.core.io.Resource

class ContextLoaderService {

    GenericApplicationContext loadContext(Resource appCtxResource) {
        ApplicationContext context = new GenericXmlApplicationContext()
        context.setParent(Holders.grailsApplication.mainContext)
        context.load(appCtxResource)
        context.refresh()
        context
    }
}
