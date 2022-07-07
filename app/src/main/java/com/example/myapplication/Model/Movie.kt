package com.example.moviesapp.Model

import java.io.Serializable

class Movie(
    var id: String? = null,
    var title: String? = null,
    var imDbRating: String? = null,
    var image: String? = null,
    var fullTitle: String? = null,
    var imDbRatingCount: String? = null,
    var year: String? = null,
    var index: String? = null,
    var description: String? = null
) : Serializable