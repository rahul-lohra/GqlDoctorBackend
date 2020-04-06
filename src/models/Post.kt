package com.rahul.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(val title: String?, val subTitle: String?, val visible: Boolean?, val viewCount: Int?)