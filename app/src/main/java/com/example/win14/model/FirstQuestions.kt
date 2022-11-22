package com.example.win14.model


import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "quiz_table")
data class FirstQuestions(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "answer1")
    @SerializedName("answer1")
    val answer1: Answer,
    @ColumnInfo(name = "answer2")
    @SerializedName("answer2")
    val answer2: Answer,
    @ColumnInfo(name = "answer3")
    @SerializedName("answer3")
    val answer3: Answer,
    @ColumnInfo(name = "answer4")
    @SerializedName("answer4")
    val answer4: Answer,
    @ColumnInfo(name = "question")
    @SerializedName("question")
    val question: String
)