package com.example.win14.model


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Answer(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "trueorfalse")
    @SerializedName("trueorfalse")
    val trueorfalse: String
)