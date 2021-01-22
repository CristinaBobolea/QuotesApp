package com.carintis.android.quotesapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carintis.android.quotesapp.domain.Quote
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Quotes")
data class CachedQuote(
    @PrimaryKey
     val id : Long,
    val title: String,
    val media: String,
    val author: String,
    val cat: String,
    val isFavourite: Boolean = false
) {

  fun toDomainEntity(): Quote = Quote(id, title,media,author,cat, isFavourite)
}
