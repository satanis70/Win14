package com.example.win14.room

import androidx.room.TypeConverter
import com.example.win14.model.Answer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class RoomConverter {
    @TypeConverter
    fun answerToString(answer: Answer): String = Gson().toJson(answer)

    @TypeConverter
    fun stringToAnswer(string: String): Answer = Gson().fromJson(string, Answer::class.java)
}
