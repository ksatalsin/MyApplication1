package android1.myapplication1.data.model

import java.io.Serializable

open class DataWrapper (var offset :Int = 0,var limit :Int = 0,var total :Int = 0,var count :Int = 0,var results :List<ComicsEntity>):Serializable