package android1.myapplication1.ui.maincomicslist.di

import android.arch.lifecycle.ViewModel
import android1.myapplication1.ui.maincomicslist.MainComicsListViewModel
import com.gc.gctodo.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class MainComicsListModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainComicsListViewModel::class)
  abstract fun bindMainComicsListViewModel(viewModel: MainComicsListViewModel): ViewModel

}