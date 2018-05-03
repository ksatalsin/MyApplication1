package android1.myapplication1.ui.maincomicslist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android1.myapplication1.R
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.ui.views.ParallaxViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ParallaxAdapter(private val context: Context,
     var items: ArrayList<ComicsEntity> = ArrayList(), private val height: Int, private val width: Int,
    private val listener: ComicsClickListener) : RecyclerView.Adapter<ParallaxViewHolder>() {

  private val parallaxViewHolderList: MutableList<ParallaxViewHolder>

  init {
    parallaxViewHolderList = ArrayList()
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ParallaxViewHolder {
    val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_comics_item, viewGroup,
        false)
    val parallaxViewHolder = ParallaxViewHolderCustom(v, height)
    parallaxViewHolderList.add(parallaxViewHolder)
    return parallaxViewHolder
  }

  override fun onBindViewHolder(parallaxViewHolder: ParallaxViewHolder, position: Int) {
    val item = items[position]
    parallaxViewHolder.parallaxText.text = item.title
    parallaxViewHolder.translate()

    Glide.with(context)
        .load("${item.thumbnail?.path}.${item.thumbnail?.extension}")
        .apply( RequestOptions()
            .override(height, height)
            //.centerCrop()
        )
        .into(
        parallaxViewHolder.parallaxImage)

    parallaxViewHolder.itemView.tag = parallaxViewHolder

    parallaxViewHolder.itemView.setOnClickListener { _ ->
      listener.onItemClick(item,position,parallaxViewHolder.itemView)
    }
  }

  fun reTranslate() {
    for (parallaxViewHolder in parallaxViewHolderList) {
      parallaxViewHolder.translate()
    }
  }

  override fun getItemCount(): Int {
    return items.size
  }

  override fun onViewRecycled(parallaxViewHolder: ParallaxViewHolder) {
    super.onViewRecycled(parallaxViewHolder)
    parallaxViewHolder.translate()
  }

  private inner class ParallaxViewHolderCustom(itemView: View,
      recyclerViewHeight: Int) : ParallaxViewHolder(itemView, recyclerViewHeight)
}
