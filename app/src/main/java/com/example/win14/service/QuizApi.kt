package com.example.win14.service

import com.example.win14.model.FirstListModel
import retrofit2.Call
import retrofit2.http.GET

interface QuizApi {
    @GET("firstlistquestions.json")
    fun getFistList():Call<FirstListModel>
}