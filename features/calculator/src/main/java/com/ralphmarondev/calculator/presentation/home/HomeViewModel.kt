package com.ralphmarondev.calculator.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class HomeViewModel : ViewModel() {

    private val _equationText = MutableStateFlow("")
    val equationText = _equationText.asStateFlow()

    private val _resultText = MutableStateFlow("0")
    val resultText = _resultText.asStateFlow()

    fun onButtonClick(btn: String) {

        _equationText.value.let {
            if (btn == "AC") {
                if (_equationText.value.isNotEmpty())
                    _equationText.value = ""
                if (_resultText.value.isNotEmpty())
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
                _resultText.value = calculateResult(_equationText.value)
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