package android1.myapplication1.ui.maincomicslist

import android.view.View
import android1.myapplication1.data.model.ComicsEntity

interface ComicsClickListener {
  fun onItemClick(item: ComicsEntity, position: Int, view: View)
}
