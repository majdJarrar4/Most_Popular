package com.example.mostpopular.authentication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            ViewModelProvider(requireActivity()).get(AuthenticationViewModel::class.java)


        binding.loginButton.setOnClickListener {
            viewModel.validateUser(
                binding.userNameEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
        collectLogin(viewModel)
    }

    private fun collectLogin(viewModel: AuthenticationViewModel) {
        lifecycleScope.launchWhenStarted {
            viewModel.userLoginState.collect {
                when (it) {
                    is ResponseState.Success -> {
                        viewModel.intentToMainActivity.value = true
                    }
                    is ResponseState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Must Register First or Error in password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else -> {}
                }
            }
        }
    }
}