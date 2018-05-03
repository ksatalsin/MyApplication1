package android1.myapplication1.ui.views

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class ParallaxRecyclerView : RecyclerView {

  constructor(context: Context) : super(context) {}

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

  constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs,
      defStyle) {
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
  }

  fun setupParallax(context: Context) {
    layoutManager = LinearLayoutManager(context)
    val parallaxScrollListener = ParallaxScrollListener()
    addOnScrollListener(parallaxScrollListener)
  }

}
