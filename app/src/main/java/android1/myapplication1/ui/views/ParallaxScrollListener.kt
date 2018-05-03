package android1.myapplication1.ui.views

import android.support.v7.widget.RecyclerView
import android1.myapplication1.ui.maincomicslist.ParallaxAdapter


class ParallaxScrollListener : RecyclerView.OnScrollListener() {

  override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
    (recyclerView!!.adapter as ParallaxAdapter).reTranslate()
  }
}
