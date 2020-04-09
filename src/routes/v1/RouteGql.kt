package com.rahul.routes.v1

import com.rahul.models.Book
import com.rahul.models.CreateGqlRecord
import com.rahul.repository.GqlRecordRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val GQL_RECORD = Constants.V1 + "/gql"

class RouteGql @Inject constructor(val repository: GqlRecordRepository, routing: Routing) {

    init {
        routing {
            get(repository)
//            post(repository)
        }
    }
}

fun Route.get(repository: GqlRecordRepository) {
    get(GQL_RECORD) {
        withContext(Dispatchers.IO){
            val items = repository.getAllItems()
            if (!items.isNullOrEmpty())
                call.respond(HttpStatusCode.OK, items)
            else
                call.respond(HttpStatusCode.OK, emptyList<Book>())
        }
    }
}

//fun Route.post(repository: GqlRecordRepository) {
//    post<CreateGqlRecord> { request ->
//        repository.insert(request)
//        call.respond(HttpStatusCode.OK)
//    }
//}