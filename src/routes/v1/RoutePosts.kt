package com.rahul.routes.v1

import com.rahul.repository.PostsRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.Document
import javax.inject.Inject

const val TEST_POST_ROUTE = "/v1/posts"

@KtorExperimentalLocationsAPI
@Location(TEST_POST_ROUTE)
class TestPostRoute

class RoutePosts @Inject constructor(val postsRepository: PostsRepository, routing: Routing) {

    init {
        routing {
            posts(postsRepository)
        }
    }
}

fun Route.posts(postsRepository: PostsRepository) {
    get(TEST_POST_ROUTE) {
        withContext(Dispatchers.IO) {
            val data = postsRepository.getPosts()
            if (!data.isNullOrEmpty())
                call.respond(HttpStatusCode.OK, data)
            else
                call.respond(HttpStatusCode.OK, emptyList<Document>())
        }

    }
}