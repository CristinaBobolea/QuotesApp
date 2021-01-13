package com.carintis.quotesapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.carintis.quotesapp.db.Converters
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Quote")
@TypeConverters(Converters::class)
data class Quote
    (
    @PrimaryKey
    @SerializedName("key")
    val id: String,

    @ColumnInfo(name = "author_name")
    @SerializedName("author_name")
    val authorName: List<String>,

    @ColumnInfo(name = "author_key")
    @SerializedName("author_key")
    val authorKey: List<String>,

    @ColumnInfo(name = "text")
    @SerializedName("text")
    val text: String?

) : Serializable
