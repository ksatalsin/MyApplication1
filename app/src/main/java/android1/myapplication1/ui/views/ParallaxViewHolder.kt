package android1.myapplication1.ui.views

import android.graphics.Matrix
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android1.myapplication1.R

open class ParallaxViewHolder(itemView: View,
    private val recyclerViewHeight: Int) : RecyclerView.ViewHolder(itemView) {
  var parallaxImage: ImageView
  var parallaxText: TextView

  init {
    parallaxImage = itemView.findViewById(R.id.image)
    parallaxImage.scaleType = ImageView.ScaleType.MATRIX
    parallaxText = itemView.findViewById(R.id.title)
  }

  fun translate() {
    val translate = -itemView.y * (parallaxImage.measuredHeight.toFloat() / recyclerViewHeight.toFloat())
    val matrix = Matrix()
    matrix.postTranslate(0f, translate)

    parallaxImage.imageMatrix = matrix
  }
}

