package com.example.quiz_app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quiz_app.model.QuestionList
import com.example.quiz_app.retrofit.QuestionAPI
import com.example.quiz_app.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {

    var questionsAPI: QuestionAPI

    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance()
            .create(QuestionAPI::class.java)
    }

    fun getQuestionsFromAPI(): LiveData<QuestionList> {

        // Live Data
        var data = MutableLiveData<QuestionList>()

        var questionsList : QuestionList

        GlobalScope.launch(Dispatchers.IO) {

            // Returning the Response<QuestionsList>
            val response = questionsAPI.getQuestions()

            if (response != null){

                // saving the data to list
                questionsList = response.body()!!

                data.postValue(questionsList)
                Log.i("TAGY", "" +data.value)

            }
        }
        return data
    }
}