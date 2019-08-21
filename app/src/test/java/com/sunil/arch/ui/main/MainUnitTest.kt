package com.sunil.arch.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sunil.arch.data.DataSet
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.extention.blockingObserve
import com.sunil.arch.navigation.NavigationController
import com.sunil.arch.remote.repository.AppDispatchers
import com.sunil.arch.remote.repository.Resource
import com.sunil.arch.remote.useCase.GetMovieUseCases
import com.sunil.arch.utility.Event
import com.sunil.arch.viewModel.MainViewModel
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainUnitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getMovieUseCases: GetMovieUseCases
    private lateinit var mainViewModel: MainViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.Default, Dispatchers.Default)


    @Before
    fun setUp() {
        getMovieUseCases = mockk()
    }

    @Test
    fun User_requested_when_viewModel_is_created() {
        val observer = mockk<Observer<Resource<List<MovieEntity>>>>(relaxed = true)
        val result = Resource.success(DataSet.FAKE_MOVIE)

        coEvery { getMovieUseCases() } returns MutableLiveData<Resource<List<MovieEntity>>>().apply { value = result }


        mainViewModel = MainViewModel(getMovieUseCases, appDispatchers)
        mainViewModel.movie.observeForever(observer)

        try {
            verify {
                observer.onChanged(result)
            }

            confirmVerified(observer)

        } catch (ex: AssertionError) {
            ex.printStackTrace()
        }


    }

    /*  @Test
      fun User_requested_but_failed_when_viewModel_Created() {
          val observer = mockk<Observer<Resource<List<MovieEntity>>>>(relaxed = true)
          val observeSnackbar = mockk<Observer<Event<Int>>>()
          val result = Resource.error(Exception("fail"), null)

          coEvery { getMovieUseCases() } returns MutableLiveData<Resource<List<MovieEntity>>>().apply { value = result }

          mainViewModel = MainViewModel(getMovieUseCases, appDispatchers)
          mainViewModel.movie.observeForever(observer)
          mainViewModel.snackBarError.observeForever(observeSnackbar)

          verify {
              observer.onChanged(result)
              observeSnackbar.onChanged(mainViewModel.snackBarError.value)
          }

          confirmVerified(observer)
      }
  */
    @Test
    fun User_click_on_RecyclerView_Item() {
        val event =
            Event(NavigationController.To(MainFragmentDirections.actionMainFragmentToDetailFragment(DataSet.FAKE_MOVIE.first())))


        coEvery { getMovieUseCases() } returns MutableLiveData<Resource<List<MovieEntity>>>().apply {
            value = Resource.success(
                DataSet.FAKE_MOVIE
            )
        }

        mainViewModel = MainViewModel(getMovieUseCases, appDispatchers)
        mainViewModel.userClicksOnItem(DataSet.FAKE_MOVIE.first())

        Assert.assertEquals(event.peekContent(), mainViewModel.navigation.blockingObserve()!!.peekContent())
    }

}