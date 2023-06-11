package com.example.mostpopular.authentication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mostpopular.popular.ui.MainActivity
import com.example.mostpopular.R
import com.example.mostpopular.core.SharedPreferance
import com.example.mostpopular.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding

    @Inject
    lateinit var prefs: SharedPreferance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel =
            ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        getValidationUser()
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLoginFragment()

        binding.loginButton.setOnClickListener {
            initLoginFragment()
        }
        binding.registerButton.setOnClickListener {
            initRegisterFragment()
        }
        collectIntent(viewModel)
    }

    private fun collectIntent(viewModel: AuthenticationViewModel) {
        viewModel.intentToMainActivity.observe(this, Observer {
            if (it) {
                goToMainActivity()
            }
        })
    }

    fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun getValidationUser() {
        if (!(prefs.getEmail().equals("", ignoreCase = true))) {
            goToMainActivity()
            finish()
        }
    }

    private fun initLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_fragment, LoginFragment())
            .commitAllowingStateLoss()
    }

    private fun initRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.authentication_fragment, RegisterFragment())
            .commitAllowingStateLoss()
    }
}