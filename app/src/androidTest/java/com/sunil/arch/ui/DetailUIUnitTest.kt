package com.sunil.arch.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sunil.arch.R
import com.sunil.arch.data.DataSet
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.ui.detail.DetailFragment
import com.sunil.arch.ui.detail.DetailFragmentDirections
import com.sunil.arch.viewModel.DetailViewModel
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import androidx.test.rule.ActivityTestRule
import com.sunil.arch.MainActivity
import org.junit.Rule
import android.content.Intent

@RunWith(AndroidJUnit4::class)
@LargeTest
class DetailUIUnitTest : KoinTest {

    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)


    @Before
    fun beforeTest() {
        mActivityRule.launchActivity(Intent()) // breakpoint here
        detailViewModel = mockk()
    }

    @After
    fun afterTest() {

    }

    @Test
    fun test_data_inflating_on_correct_view() {
        val movie = DataSet.FAKE_MOVIE.first()

        launchFragment(movie)

        Espresso.onView(ViewMatchers.withId(R.id.movie_name)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    CoreMatchers.containsString(movie.title)
                )
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.movie_overview)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    CoreMatchers.containsString(movie.overview)
                )
            )
        )

        Espresso.onView(ViewMatchers.withId(R.id.rating)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    CoreMatchers.containsString(movie.voteAverage.toString())
                )
            )
        )
    }

    @Test
    fun test_navigate_to_detail_to_cover() {

        val movie = DataSet.FAKE_MOVIE.first()

        val mockkNavigator = launchFragment(movie)

        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())

        verify {
            mockkNavigator.navigate(
                DetailFragmentDirections.actionDetailFragmentToMovieCoverFragment(movie.posterPath),
                any<FragmentNavigator.Extras>()
            )
        }

    }

    private fun launchFragment(movieEntity: MovieEntity): NavController {
        val mockNavController = mockk<NavController>(relaxed = true)
        val detailScenario =
            launchFragmentInContainer<DetailFragment>(fragmentArgs = Bundle().apply {
                putString(
                    "imageUrl",
                    movieEntity.posterPath
                )
            })
        detailScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        return mockNavController
    }

}