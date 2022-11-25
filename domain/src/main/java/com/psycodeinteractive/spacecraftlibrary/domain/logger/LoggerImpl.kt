package com.psycodeinteractive.spacecraftlibrary.domain.logger

import android.util.Log

class LoggerImpl : Logger {
    override fun v(message: String) {
        Log.v(getTag(), message)
    }

    override fun v(throwable: Throwable) {
        Log.v(getTag(), "", throwable)
    }

    override fun v(message: String, throwable: Throwable) {
        Log.v(getTag(), message, throwable)
    }

    override fun e(message: String) {
        Log.e(getTag(), message)
    }

    override fun e(throwable: Throwable) {
        Log.e(getTag(), "", throwable)
    }

    override fun e(message: String, throwable: Throwable) {
        Log.e(getTag(), message, throwable)
    }

    override fun i(message: String) {
        Log.i(getTag(), message)
    }

    override fun i(throwable: Throwable) {
        Log.i(getTag(), "", throwable)
    }

    override fun i(message: String, throwable: Throwable) {
        Log.i(getTag(), message, throwable)
    }

    override fun d(message: String) {
        Log.d(getTag(), message)
    }

    override fun d(throwable: Throwable) {
        Log.d(getTag(), "", throwable)
    }

    override fun d(message: String, throwable: Throwable) {
        Log.d(getTag(), message, throwable)
    }

    override fun w(message: String) {
        Log.w(getTag(), message)
    }

    override fun w(throwable: Throwable) {
        Log.w(getTag(), "", throwable)
    }

    override fun w(message: String, throwable: Throwable) {
        Log.w(getTag(), throwable)
    }

    private fun getTag(): String {
        val stackTrace = Thread.currentThread().stackTrace
        val stackTraceElement = stackTrace[5]
        return stackTraceElement.run { "${className.substringAfterLast('.')}.$methodName()/line:$lineNumber" }
    }
}
