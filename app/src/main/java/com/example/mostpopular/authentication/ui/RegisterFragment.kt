package com.example.mostpopular.authentication.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.core.BaseValidationFragment
import com.example.mostpopular.core.LocalValidationError
import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.regex.Pattern


@AndroidEntryPoint
class RegisterFragment : BaseValidationFragment<FragmentRegisterBinding>() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    var dateBirthday = ""
    lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(requireActivity()).get(AuthenticationViewModel::class.java)
        initialListener()
        collectRegister()

    }

    private fun collectRegister() {
        lifecycleScope.launchWhenStarted {
            this@RegisterFragment.viewModel.userRegisterState.collect {
                when (it) {
                    is ResponseState.Success -> {
                        viewModel.intentToMainActivity.postValue(true)

                    }
                    else -> {}
                }
            }
        }
    }

    private fun initialListener() {
        binding.registerButton.setOnClickListener {
            validate()
        }
        binding.dateOfBirthdayTextPersonName.setOnTouchListener { v, event ->
            showCalender()
            return@setOnTouchListener true
        }

    }

    private fun showCalender() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            { view, year, monthOfYear, dayOfMonth ->
                dateBirthday = "${year}/$monthOfYear/$dayOfMonth"
                binding.dateOfBirthdayTextPersonName.setText("${year}/$monthOfYear/$dayOfMonth")
            }, year, month, day

        )


        datePickerDialog.show()
    }

    override fun handleInputValidation() {

        validatePhoneNumber()
        validateUserName()
        validateNotionalID()
        validatePassword()
        validateEmail()
        validateDate()

    }

    private fun validatePhoneNumber() {
        if (!(Patterns.PHONE.matcher(binding.phoneNumberTextPersonName.text)
                .matches()) && !(binding.phoneNumberTextPersonName.text?.startsWith(
                "07"
            ) == true)
        ) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "Must Phone number start with 07",
                    viewId = binding?.phoneNumberTextPersonName?.id
                )
            )
        }

    }

    private fun validateUserName() {
        if (!(binding?.editTextTextPersonName?.text.toString().length in 1..4) && !(binding?.editTextTextPersonName?.text?.contains(
                " "
            ) == true
                    )
        ) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "please enter a full name",
                    viewId = binding?.editTextTextPersonName?.id
                )
            )
        }
    }

    private fun validateNotionalID() {
        if (binding?.nationalIdTextPersonName?.text?.startsWith("9") != true) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "Most National ID start with 9",
                    viewId = binding?.nationalIdTextPersonName?.id
                )
            )
        }

    }

    private fun validatePassword() {
        if (Pattern.matches(
                binding.passwordTextPersonName.text.toString(), PASSWORD_PATTERN
            )
        ) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "please enter a valid password",
                    viewId = binding?.passwordTextPersonName?.id
                )
            )
        }
    }

    private fun validateEmail() {

        if (!(Patterns.EMAIL_ADDRESS.matcher(binding.emailTextPersonName.text).matches())) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "please enter a valid email",
                    viewId = binding?.emailTextPersonName?.id
                )
            )
        }
    }

    private fun validateDate() {
        if (dateBirthday.isNullOrEmpty()) {
            localValidationErrors.add(
                LocalValidationError(
                    message = "please enter a date birthdate",
                    viewId = binding?.dateOfBirthdayTextPersonName?.id
                )
            )
        }
    }

    override fun onValidationSucceed() {
        viewModel.registerUser(
            UserModel(
                0,
                binding.editTextTextPersonName.text.toString(),
                binding.nationalIdTextPersonName.text.toString(),
                binding.phoneNumberTextPersonName.text.toString(),
                binding.emailTextPersonName.text.toString(),
                dateBirthday,
                binding.passwordTextPersonName.text.toString()
            )
        )
        super.onValidationSucceed()

    }

    companion object {
        private const val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
        const val USER_EMAIL = "USER_EMAIL"
    }
}