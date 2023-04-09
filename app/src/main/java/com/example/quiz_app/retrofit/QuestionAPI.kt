package com.example.quiz_app.retrofit

import com.example.quiz_app.model.QuestionList
import retrofit2.Response
import retrofit2.http.GET

interface QuestionAPI {

    @GET("questionsapi.php")
    suspend fun getQuestions() : Response<QuestionList>
}