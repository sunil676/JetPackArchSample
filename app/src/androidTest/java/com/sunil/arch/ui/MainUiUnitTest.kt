package com.sunil.arch.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sunil.arch.R
import com.sunil.arch.data.DataSet
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.remote.repository.MovieRepository
import com.sunil.arch.remote.repository.Resource
import com.sunil.arch.ui.main.MainFragment
import com.sunil.arch.ui.main.MainFragmentDirections
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainUiUnitTest : KoinTest {

    private val movieRepository = mockk<MovieRepository>()

    @Before
    fun beforeSetup() {

    }

    @After
    fun afterSetup() {

    }

    @Test
    fun test_the_behaviours_of_screen_when_it_get_error() {
        coEvery { movieRepository.getMovieList() } returns MutableLiveData<Resource<List<MovieEntity>>>().apply {
            postValue(
                Resource.error(Exception("Exception"), listOf())
            )
        }

        launchFragment()

        Espresso.onView(ViewMatchers.withId(R.id.fragment_home_rv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(com.google.android.material.R.id.snackbar_text))
            .check(ViewAssertions.matches(ViewMatchers.withText("An Error occoured, Please try again.")))
    }

    @Test
    fun test_navigation_to_detaol_screen() {
        val movieEntity = mockk<MovieEntity>()
        coEvery { movieRepository.getMovieList() } returns MutableLiveData<Resource<List<MovieEntity>>>().apply {
            postValue(
                Resource.success(DataSet.FAKE_MOVIE)
            )
        }
        val mockNavController = launchFragment()

        Espresso.onView(ViewMatchers.withId(R.id.fragment_home_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        verify {
            mockNavController.navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(movieEntity),
                any<FragmentNavigator.Extras>()
            )
        }

    }

    fun launchFragment(): NavController {

        val navController = mockk<NavController>(relaxed = true)
        val mainScreenScnario = launchFragmentInContainer<MainFragment>()
        mainScreenScnario.onFragment { fragment ->
            Navigation.setViewNavController(
                fragment.requireView(),
                navController
            )
        }
        return navController

    }
}