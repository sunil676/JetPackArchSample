package com.sunil.arch.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class MovieEntity() : Parcelable{
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("poster_path")
    var posterPath: String=""

    @SerializedName("adult")
    var isAdult: Boolean = false

    @SerializedName("overview")
    var overview: String? = null

    @SerializedName("original_title")
    var originalTitle: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("vote_count")
    var voteCount: Int = 0

    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble()

    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @SerializedName("original_language")
    var originalLanguage: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        posterPath = parcel.readString()
        isAdult = parcel.readByte() != 0.toByte()
        overview = parcel.readString()
        originalTitle = parcel.readString()
        title = parcel.readString()
        voteCount = parcel.readInt()
        voteAverage = parcel.readDouble()
        backdropPath = parcel.readString()
        originalLanguage = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(posterPath)
        parcel.writeByte(if (isAdult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(originalTitle)
        parcel.writeString(title)
        parcel.writeInt(voteCount)
        parcel.writeDouble(voteAverage)
        parcel.writeString(backdropPath)
        parcel.writeString(originalLanguage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieEntity> {
        override fun createFromParcel(parcel: Parcel): MovieEntity {
            return MovieEntity(parcel)
        }

        override fun newArray(size: Int): Array<MovieEntity?> {
            return arrayOfNulls(size)
        }
    }
}