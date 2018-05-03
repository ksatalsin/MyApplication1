package android1.myapplication1.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable


@Entity
data class ComicsEntity(
    @PrimaryKey
    var id: Int? = null,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @Embedded
    var thumbnail: Thumbnail? = null

) : Serializable