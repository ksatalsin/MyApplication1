package android1.myapplication1.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.ui.maincomicslist.ComicsClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ComicsViewLayout(context: Context?, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

  lateinit var title: TextView
  lateinit var image: ImageView

  fun bind(comicsEntity: ComicsEntity, position: Int, listener: ComicsClickListener) {

    title =  findViewById(android1.myapplication1.R.id.title)
    image =  findViewById(android1.myapplication1.R.id.image)

    this.setOnClickListener {
      listener.onItemClick(comicsEntity, position, this)
    }

    title.text = comicsEntity.title

    Glide.with(context)
        .load(comicsEntity.thumbnail?.path +"."+comicsEntity.thumbnail?.extension)
        .apply(RequestOptions.circleCropTransform())
        .into(image)


  }

}