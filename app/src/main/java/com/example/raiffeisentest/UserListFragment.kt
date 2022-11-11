package com.example.raiffeisentest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.raiffeisentest.adapters.RecyclerViewAdapter
import com.example.raiffeisentest.databinding.FragmentUserListBinding
import com.example.raiffeisentest.models.UsersModel
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val TAG = "UserListFragment"
class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModel()
    private lateinit var mUsers: UsersModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val dividerItemDecoration = DividerItemDecoration(
            binding.usersList.context,
            LinearLayout.VERTICAL
        )
        binding.usersList.addItemDecoration(dividerItemDecoration)

        binding.usersList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val lastItemPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    val lastCalledPage = viewModel.lastCalledPage.get()
                    if (lastCalledPage < 3 && lastItemPosition >= (lastCalledPage * mUsers.info.results - 3))
                        viewModel.getUsers(lastCalledPage + 1)
                }
            }
        })

        viewModel.users.observe(requireActivity()) { u ->
            Log.v(TAG, u.toString())
            if (this::mUsers.isInitialized) {
                if (mUsers.results.size + u.info.results > viewModel.lastCalledPage.get() * u.info.results && u.results.size > 0)
                    mUsers = u
                else
                    mUsers.results.addAll(u.results)
            } else
                mUsers = u

            binding.userModel = mUsers
            val firstItemPosition = (binding.usersList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            binding.usersList.adapter = RecyclerViewAdapter(mUsers.results)
            binding.usersList.scrollToPosition(firstItemPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}