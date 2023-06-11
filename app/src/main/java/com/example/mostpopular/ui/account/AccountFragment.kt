package com.example.mostpopular.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.authentication.ui.AuthenticationActivity
import com.example.mostpopular.core.SharedPreferance
import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var preferance: SharedPreferance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)
        getValidationUser(viewModel)
        collectLogin(viewModel = viewModel)
        initialListener()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initialListener() {
        binding.logoutButton.setOnClickListener {
            preferance.saveEmailUser("")
            val intent = Intent(requireActivity(), AuthenticationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun collectLogin(viewModel: AccountViewModel) {
        lifecycleScope.launchWhenStarted {

            viewModel.getUserInfoState.collect {
                when (it) {
                    is ResponseState.Success -> {
                        if (it != null) {
                            initialLayout(it.data as UserModel)
                        }
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

    private fun initialLayout(data: UserModel) {
        binding.userNameTextView.text = "user name : ${data.fullName}"
        binding.emailTextView.text = "email : ${data.email}"
        binding.phoneNumberTextView.text = "phone number : ${data.phoneNumber}"
        binding.birthdayTextView.text = "Birth Day : ${data.dateBirthDay}"
        binding.notionalIdTextview.text = "Notional ID : ${data.notionalID}"
    }

    private fun getValidationUser(viewModel: AccountViewModel) {

        viewModel.getUserInfo()

    }
}