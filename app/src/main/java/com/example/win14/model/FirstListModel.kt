package com.example.win14.model


import com.google.gson.annotations.SerializedName

data class FirstListModel(
    @SerializedName("firstListQuestions")
    val firstListQuestions: List<FirstQuestions>
)