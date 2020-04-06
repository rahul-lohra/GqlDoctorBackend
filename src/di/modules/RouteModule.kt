package di.modules

import dagger.Module
import dagger.Provides
import io.ktor.routing.Routing

@Module
class RouteModule(val routing: Routing) {

    @Provides
    fun provideRouting(): Routing {
        return routing
    }
}