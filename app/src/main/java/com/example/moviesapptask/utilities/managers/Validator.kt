package com.example.moviesapptask.utilities.managers

import java.util.regex.Pattern
import javax.inject.Inject

interface ValidatorInterface {
    fun isPasswordStrong(password: String): Boolean
    fun isEmailValid(email: String): Boolean
    fun isUsernameValid(username: String): Boolean
    fun isPhoneValid(phone: String, thereIsPhoneCode: Boolean = false): Boolean
}

class Validator @Inject constructor() : ValidatorInterface {

    override fun isPasswordStrong(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{8,}\$".toRegex())
    }

    override fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun isUsernameValid(username: String): Boolean {
//        return username.matches("^[a-zA-Z0-9._-]{3,}\$".toRegex())
        return username.matches("^[a-z0-9._-]{3,}\$".toRegex())
    }

    override fun isPhoneValid(phone: String, thereIsPhoneCode: Boolean): Boolean {

        val pattern = if (thereIsPhoneCode) {
            Pattern.compile("\\+[0-9]+")
        } else {
            Pattern.compile("[0-9]+")
        }

        return pattern.matcher(phone).matches()
    }
}