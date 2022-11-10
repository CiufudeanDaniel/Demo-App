package com.example.raiffeisentest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.raiffeisentest.adapters.RecyclerViewAdapter
import com.example.raiffeisentest.databinding.FragmentUserListBinding
import com.example.raiffeisentest.view_models.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val TAG = "UserListFragment"
class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: UserViewModel by viewModel()


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

        viewModel.users.observe(requireActivity()) { u ->
            Log.v(TAG, u.toString())
            binding.usersList.adapter = RecyclerViewAdapter(u)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}