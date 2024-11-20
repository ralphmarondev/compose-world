package com.ralphmarondev.calculator.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class HomeViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    fun onButtonClick(btn: String) {

        _equationText.value?.let {
            if (btn == "AC") {
                if (_equationText.value!!.isNotEmpty())
                    _equationText.value = ""
                if (_resultText.value!!.isNotEmpty())
                    _resultText.value = ""
                return
            }
            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)
                }
                return
            }

            if (btn == "=") {
                _equationText.value = _resultText.value
                return
            }

            _equationText.value = it + btn

            try {
                _resultText.value = calculateResult(_equationText.value.toString())
            } catch (e: Exception) {
                Log.e("CALCULATOR", "Error: ${e.message}")
            }
        }
    }

    private fun calculateResult(equation: String): String {
        val context = Context.enter()
        context.optimizationLevel = -1
        val scriptable: Scriptable = context.initStandardObjects()
        var finalResult =
            context.evaluateString(scriptable, equation, "Javascript", 1, null).toString()

        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0", "")
        }

        return finalResult
    }
}