package android1.myapplication1.ui.maincomicslist

import android.arch.lifecycle.ViewModelProvider.Factory
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.View
import android1.myapplication1.R.layout
import android1.myapplication1.data.LiveResponseStatus
import android1.myapplication1.data.model.ComicsEntity
import android1.myapplication1.ext.observe
import android1.myapplication1.ext.showToast
import android1.myapplication1.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.recycler
import javax.inject.Inject


class MainComicsListActivity : BaseActivity(), ComicsClickListener {

  @Inject
  lateinit var viewModelFactory: Factory

  private val viewModel by lazy {
    ViewModelProviders.of(MainActivity@ this, viewModelFactory).get(
        MainComicsListViewModel::class.java)
  }

  private var adapter: ParallaxAdapter? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(layout.activity_main)

    val decorView = window.decorView
    val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
    decorView.systemUiVisibility = uiOptions

    initRecycler()

    viewModel.comicsLive.observe(this) {
      it?.let {
        bindData(it.status, it.data, it.error)
      }
    }
  }

  private fun bindData(status: LiveResponseStatus, data: List<ComicsEntity>?, error: String?) {
    when (status) {
      LiveResponseStatus.LOADING -> showLoading()
      LiveResponseStatus.SUCCESS -> showData(data)
      LiveResponseStatus.ERROR -> showError(error)
    }
  }

  private fun showError(error: String?) {
    error?.let {
      this.showToast(it)
    }
  }

  private fun showData(data: List<ComicsEntity>?) {
    adapter?.let {
      it.items = ArrayList(data)
      it.notifyDataSetChanged()
    }
  }

  private fun showLoading() {

  }

  private fun initRecycler() {

    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    val width = metrics.heightPixels
    val height = metrics.widthPixels

    adapter = ParallaxAdapter(this, arrayListOf(), height, width, this)
    recycler.adapter = adapter
    recycler.layoutManager = LinearLayoutManager(this)
    recycler.setHasFixedSize(true)
    recycler.setupParallax(this)
  }

  override fun onItemClick(item: ComicsEntity, position: Int, view: View) {

  }
}