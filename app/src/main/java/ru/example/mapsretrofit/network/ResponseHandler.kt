package ru.mrapple100.mapsretrofit.network

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ResponseHandler {
    suspend operator fun <T> invoke(block: suspend () -> T) = try {
        Resource.Success(block())
    } catch (e: Exception) {
        Log.e(javaClass.name, e.toString())
        val errorCode = when (e) {
           // is HttpException -> e.code
            is SocketTimeoutException -> ErrorCodes.SocketTimeOut.code
            is UnknownHostException,
            is ConnectException -> ErrorCodes.UnknownHost.code
            else -> Int.MAX_VALUE
        }
        Resource.Error(getErrorMessage(errorCode))
    }

    private fun getErrorMessage(code: Int) = when (code) {
        ErrorCodes.UnknownHost.code   -> "No connection"
        ErrorCodes.SocketTimeOut.code -> "Timeout"
        401                           -> "Unauthorized"
        404                           -> "Not found"
        in 400..499             -> "Check entered data"
        in 500..599             -> "Error with connecting to server. Code: $code"
        else                          -> "Something went wrong"
    }

    private enum class ErrorCodes(val code: Int) {
        SocketTimeOut(-1),
        UnknownHost(-2)
    }
}