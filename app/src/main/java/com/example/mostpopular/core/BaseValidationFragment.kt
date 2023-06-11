package com.example.mostpopular.core

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.allViews
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

import com.google.android.material.textfield.TextInputLayout

abstract class BaseValidationFragment<T : ViewBinding> :Fragment() {

    protected var localValidationErrors = mutableListOf<LocalValidationError>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleValidationEditTextOnTextChange()
    }

    abstract fun handleInputValidation()

    protected fun validate() {
        handleInputValidation()
        if (localValidationErrors.isEmpty()) {
            onValidationSucceed()
        } else {
            onValidationFailed()
        }
    }

    protected open fun onValidationSucceed() {
        //No Impl
    }

    protected fun onValidationFailed() {
        view?.allViews?.forEach { validatedView ->
            if (validatedView is TextInputLayout) {
                localValidationErrors
                    .firstOrNull { validatedView.editText?.id == it.viewId }
                    ?.let {
                        validatedView.isErrorEnabled = true
                        validatedView.error = it.message
                    }
            }
        }


    }

    private fun handleValidationEditTextOnTextChange() {
        view?.allViews?.forEach { validatedView ->
            if (validatedView is EditText) {
                validatedView.doOnTextChanged { _, _, _, _ ->
                    (validatedView.parent.parent as TextInputLayout).error = ""
                    localValidationErrors.clear()
                }
            }
        }
    }


}