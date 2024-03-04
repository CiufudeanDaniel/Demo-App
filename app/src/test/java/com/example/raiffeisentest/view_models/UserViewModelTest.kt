package com.example.raiffeisentest.view_models

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.raiffeisentest.MyApp
import com.example.raiffeisentest.UserListScreenState
import com.example.raiffeisentest.repository.UserRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertTrue

class UserViewModelTest {

    // This rule swaps the background executor used by the Architecture Components with a different one
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // Provides a test-coroutine dispatcher to use with delay and other suspending functions
    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    // Tells Mockito to mock the observer
    @Mock
    private lateinit var observer: Observer<UserListScreenState>

    // Class under test
    private lateinit var viewModel: UserViewModel
    private val repository = mockk<UserRepository>()
    private val application = mockk<MyApp>()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        // Initialize Mockito annotations
        MockitoAnnotations.initMocks(this)

        coEvery { repository.getUsersScreenState(any()) } returns UserListScreenState.Error
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0


    }

    @ExperimentalCoroutinesApi
    @Test
    fun `loadMessage updates inputScreenState to Error`() = runTest {
        // Replace the main dispatcher with the test dispatcher
        Dispatchers.setMain(testDispatcher)
        // Provide the scope explicitly, in this case, using TestCoroutineScope
        viewModel = UserViewModel(application, repository)

        // Attach the mock observer to the LiveData
        viewModel.inputScreenState.observeForever(observer)

        // Advance the coroutine dispatcher to execute any pending coroutines actions
        testDispatcher.scheduler.advanceUntilIdle()

        // Verify that the observer was called with the correct value
        assertTrue { viewModel.inputScreenState.value is UserListScreenState.Error }

        // Reset main dispatcher to the original Main dispatcher
        Dispatchers.resetMain()
    }
}