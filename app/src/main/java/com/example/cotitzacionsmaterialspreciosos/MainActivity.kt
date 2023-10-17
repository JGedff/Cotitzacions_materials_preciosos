package com.example.cotitzacionsmaterialspreciosos

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.ErrnoException
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {
    private var isDot = false
    private var inputValue: Double = 0.0
    private var relativePriceWon: Double = 0.0
    private var relativePriceRuby: Double = 0.0
    private var relativePriceDollar: Double = 0.0
    private var relativePriceEmerald: Double = 0.0
    private var relativePriceDiamond: Double = 0.0
    private var relativePriceSapphire: Double = 0.0

    private var wonSimble: String = "₩"
    private var euroSimble: String = "€"
    private var dollarSimble: String = "$"
    private var close: String = ""
    private var accept: String = ""
    private var strWon: String = ""
    private var strRuby: String = ""
    private var strDollar: String = ""
    private var strEquals: String = ""
    private var coinPrice: String = ""
    private var strEmerald: String = ""
    private var strDiamond: String = ""
    private var outputValue: String = ""
    private var isNotNumber: String = ""
    private var strSapphire: String = ""
    private var priceNotSet: String = ""
    private var numberTooBig: String = ""
    private var askValueCoin: String = ""
    private var materialPrice: String = ""
    private var askValueMaterial: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.number0).setOnClickListener(this)
        findViewById<Button>(R.id.number1).setOnClickListener(this)
        findViewById<Button>(R.id.number2).setOnClickListener(this)
        findViewById<Button>(R.id.number3).setOnClickListener(this)
        findViewById<Button>(R.id.number4).setOnClickListener(this)
        findViewById<Button>(R.id.number5).setOnClickListener(this)
        findViewById<Button>(R.id.number6).setOnClickListener(this)
        findViewById<Button>(R.id.number7).setOnClickListener(this)
        findViewById<Button>(R.id.number8).setOnClickListener(this)
        findViewById<Button>(R.id.number9).setOnClickListener(this)
        findViewById<Button>(R.id.button_dot).setOnClickListener(this)
        findViewById<Button>(R.id.button_clear).setOnClickListener(this)
        findViewById<Button>(R.id.button_delete).setOnClickListener(this)
        findViewById<Button>(R.id.radio_won).setOnClickListener(this)
        findViewById<Button>(R.id.radio_dollar).setOnClickListener(this)
        findViewById<Button>(R.id.radio_ruby).setOnClickListener(this)
        findViewById<Button>(R.id.radio_emerald).setOnClickListener(this)
        findViewById<Button>(R.id.radio_diamond).setOnClickListener(this)
        findViewById<Button>(R.id.radio_sapphire).setOnClickListener(this)

        findViewById<Button>(R.id.radio_won).setOnLongClickListener(this)
        findViewById<Button>(R.id.radio_dollar).setOnLongClickListener(this)
        findViewById<Button>(R.id.radio_ruby).setOnLongClickListener(this)
        findViewById<Button>(R.id.radio_emerald).setOnLongClickListener(this)
        findViewById<Button>(R.id.radio_diamond).setOnLongClickListener(this)
        findViewById<Button>(R.id.radio_sapphire).setOnLongClickListener(this)

        coinPrice = getString(R.string.coin_price)
        materialPrice = getString(R.string.material_price)
        askValueCoin = getString(R.string.what_value_coin)
        askValueMaterial = getString(R.string.what_value_material)
        close = getString(R.string.close)
        accept = getString(R.string.accept)
        priceNotSet = getString(R.string.price_not_setted)
        isNotNumber = getString(R.string.is_not_number)
        strEquals = getString(R.string.equals)
        strDollar = getString(R.string.dollar)
        strWon = getString(R.string.won)
        strDiamond = getString(R.string.diamond)
        strRuby = getString(R.string.ruby)
        strSapphire = getString(R.string.sapphire)
        strEmerald = getString(R.string.emerald)
        numberTooBig = getString(R.string.number_too_big)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.number0 -> addNumber(view, 0.0)
            R.id.number1 -> addNumber(view, 1.0)
            R.id.number2 -> addNumber(view, 2.0)
            R.id.number3 -> addNumber(view, 3.0)
            R.id.number4 -> addNumber(view, 4.0)
            R.id.number5 -> addNumber(view, 5.0)
            R.id.number6 -> addNumber(view, 6.0)
            R.id.number7 -> addNumber(view, 7.0)
            R.id.number8 -> addNumber(view, 8.0)
            R.id.number9 -> addNumber(view, 9.0)
            R.id.button_dot -> addDot()
            R.id.button_clear -> clearNumbers()
            R.id.button_delete -> deleteLastNumber()
            R.id.radio_dollar -> onMoneyButtonClicked(view)
            R.id.radio_won -> onMoneyButtonClicked(view)
            R.id.radio_ruby -> onMaterialButtonClicked(view)
            R.id.radio_emerald -> onMaterialButtonClicked(view)
            R.id.radio_diamond -> onMaterialButtonClicked(view)
            R.id.radio_sapphire -> onMaterialButtonClicked(view)
        }
    }

    override fun onLongClick(view: View): Boolean {
        when (view.id) {
            R.id.radio_dollar -> onMoneyButtonLongClicked(view)
            R.id.radio_won -> onMoneyButtonLongClicked(view)
            R.id.radio_ruby -> onMaterialButtonLongClicked(view)
            R.id.radio_emerald -> onMaterialButtonLongClicked(view)
            R.id.radio_diamond -> onMaterialButtonLongClicked(view)
            R.id.radio_sapphire -> onMaterialButtonLongClicked(view)
        }

        return true
    }

    fun addNumber(view: View, number: Double) {
        var intValue = inputValue.toString().split('.')
        if (intValue[0].toInt() >= 1000000 && !isDot) {
            Snackbar.make(view, numberTooBig, Snackbar.LENGTH_SHORT).show()
        } else {
            val input: TextView = findViewById(R.id.tvInput)
            val inputRes: TextView = findViewById(R.id.tvResult)
            var tvInput = input.text.toString()

            tvInput = tvInput.replace(',', '.')

            inputValue = setInputValue(tvInput, number)

            handleDot(input, number)

            val textResult = getResultValue()

            outputValue = textResult
            inputRes.setText(textResult)
        }
    }

    fun getResultValue(): String {
        var result: String

        if (isMaterialSet()) {
            val value: Double = getValueMaterialSelected()
            val resultValue: Double = inputValue * value

            result = resultValue.toString() + " " + euroSimble + " / "
        } else {
            result = "0"
        }

        if (isMaterialSet() && isCoinSet()) {
            val newValue: Double = result.dropLast(5).toDouble()
            val coin: Double = getValueCoinSelected()
            val resultValue: Double = (newValue * coin)

            result += resultValue.toString() + " " + getCoinSimbleSelected()
        }

        return result.replace('.', ',')
    }

    fun isCoinSet(): Boolean {
        val radioGroup: RadioGroup = findViewById(R.id.group_coins)

        return radioGroup.getCheckedRadioButtonId() != -1
    }

    fun isMaterialSet(): Boolean {
        val radioGroup: RadioGroup = findViewById(R.id.group_materials)

        return radioGroup.getCheckedRadioButtonId() != -1
    }

    fun handleDot(input: TextView, addedNumber: Double) {
        val resultValue : String

        if (!isDot) {
            resultValue = inputValue.toString()
            input.setText(resultValue)

            deleteLastNumber()
            deleteLastNumber()
        } else {
            if (addedNumber == 0.0) {
                val valueString = input.text.toString() + "0"
                input.setText(valueString)
            } else {
                val valueString = inputValue.toString()

                input.setText(valueString.replace('.', ','))
            }
        }
    }

    fun setInputValue(tvInput: String, number: Double): Double {
        if (tvInput.toDoubleOrNull() == null) {
            return number
        } else if(isDot) {
            if (number != 0.0) {
                val newNumber = number.toString().dropLast(2)
                val tvNew = tvInput + newNumber

                return tvNew.toDouble()
            }

            return tvInput.toDouble()
        }

        return (tvInput.toDouble() * 10) + number
    }

    fun addDot() {
        val input: TextView = findViewById(R.id.tvInput)

        if (!isDot) {
            val resultValue = input.text.toString() + ','

            input.setText(resultValue)
            isDot = true
        }
    }

    fun clearNumbers() {
        val input: TextView = findViewById(R.id.tvInput)
        val result: TextView = findViewById(R.id.tvResult)

        inputValue = 0.0
        input.setText("0")

        outputValue = "0"
        result.setText("0")

        isDot = false
    }

    fun deleteLastNumber() {
        val input: TextView = findViewById(R.id.tvInput)
        var textValueNew = "0"

        if (input.text.toString().length != 1) {
            textValueNew = input.text.toString().dropLast(1)
        }

        if (textValueNew.contains(',')) {
            inputValue = textValueNew.replace(',', '.').toDouble()
        } else {
            inputValue = textValueNew.toDouble()
        }

        input.setText(textValueNew)
    }

    fun onMoneyButtonClicked(view: View) {
        getMoneyPrice(view, view.getId())
    }

    fun onMoneyButtonLongClicked(view: View) {
        longPopUpGetValueCoin(view, view.getId())
    }

    fun popUpGetValueCoin(view: View, id: Int) {
        val edtDada = EditText(this)

        MaterialAlertDialogBuilder(this)
            .setTitle(coinPrice)
            .setCancelable(false)
            .setMessage(askValueCoin)
            .setView(edtDada)
            .setNegativeButton(close) { dialog, which ->
                setValueCoin(id, 0.0)
                clearCoinCheck()
                Snackbar.make(view, priceNotSet, Snackbar.LENGTH_SHORT).show()

                setSelectedCoinRadio(id)
                setNotSelectedRadio(id)
            }
            .setPositiveButton(accept) { dialog, which ->
                val textValue: String = edtDada.text.toString().replace(',', '.')

                setSelectedCoinRadio(id)

                if (textValue.toDoubleOrNull() == null || textValue.toDouble() == 0.0) {
                    setValueCoin(id, 0.0)
                    clearCoinCheck()
                    Snackbar.make(view, isNotNumber, Snackbar.LENGTH_SHORT).show()

                    setNotSelectedRadio(id)
                } else {
                    setValueCoin(id, textValue.toDouble())

                    Snackbar.make(view, getCoinName(id) + strEquals + textValue, Snackbar.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    fun longPopUpGetValueCoin(view: View, id: Int) {
        val edtDada = EditText(this)

        MaterialAlertDialogBuilder(this)
            .setTitle(coinPrice)
            .setCancelable(false)
            .setMessage(askValueCoin)
            .setView(edtDada)
            .setNegativeButton(close) { dialog, which ->
                setValueCoin(id, 0.0)
                clearCoinCheck()
                Snackbar.make(view, priceNotSet, Snackbar.LENGTH_SHORT).show()
            }
            .setPositiveButton(accept) { dialog, which ->
                val textValue: String = edtDada.text.toString().replace(',', '.')

                if (textValue.toDoubleOrNull() == null || textValue.toDouble() == 0.0) {
                    setValueCoin(id, 0.0)
                    clearCoinCheck()
                    Snackbar.make(view, isNotNumber, Snackbar.LENGTH_SHORT).show()
                } else {
                    setValueCoin(id, textValue.toDouble())

                    Snackbar.make(view, getCoinName(id) + strEquals + textValue, Snackbar.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    fun onMaterialButtonClicked(view: View) {
        getMaterialPrice(view, view.getId())
    }

    fun onMaterialButtonLongClicked(view: View) {
        longPopUpGetMaterialPrice(view, view.getId())

        var test = findViewById<RadioButton>(view.getId()).isChecked
        Log.d("Clicked", test.toString())
    }

    fun getMoneyPrice(view: View, id: Int) {
        if (getValueCoin(id) == 0.0) {
            popUpGetValueCoin(view, id)
        } else {
            setSelectedCoinRadio(id)
        }
    }

    fun getCoinName(id: Int): String {
        var coinName = ""
        when (id) {
            R.id.radio_won -> coinName = strWon
            R.id.radio_dollar -> coinName = strDollar
        }
        return coinName
    }

    fun getMaterialPrice(view: View, id: Int) {
        if (getValueMaterial(id) == 0.0) {
            popUpGetMaterialPrice(view, id)
        } else {
            setSelectedMaterialRadio(id)
        }
    }

    fun popUpGetMaterialPrice(view: View, id: Int) {
        val edtDada = EditText(this)

        MaterialAlertDialogBuilder(this)
            .setTitle(materialPrice)
            .setCancelable(false)
            .setMessage(askValueMaterial)
            .setView(edtDada)
            .setNegativeButton(close) { dialog, which ->
                setValueMaterial(id, 0.0)
                clearMaterialCheck()
                Snackbar.make(view, priceNotSet, Snackbar.LENGTH_SHORT).show()

                setSelectedMaterialRadio(id)
                setNotSelectedRadio(id)
            }
            .setPositiveButton(accept) { dialog, which ->
                val textValue: String = edtDada.text.toString().replace(',', '.')

                setSelectedMaterialRadio(id)

                if (textValue.toDoubleOrNull() == null || textValue.toDouble() == 0.0) {
                    setValueMaterial(id, 0.0)
                    clearMaterialCheck()
                    Snackbar.make(view, isNotNumber, Snackbar.LENGTH_SHORT).show()

                    setNotSelectedRadio(id)
                } else {
                    setValueMaterial(id, textValue.toDouble())

                    Snackbar.make(view, getMaterialName(id) + strEquals + textValue, Snackbar.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    fun longPopUpGetMaterialPrice(view: View, id: Int) {
        val edtDada = EditText(this)

        MaterialAlertDialogBuilder(this)
            .setTitle(materialPrice)
            .setCancelable(false)
            .setMessage(askValueMaterial)
            .setView(edtDada)
            .setNegativeButton(close) { dialog, which ->
                setValueMaterial(id, 0.0)
                clearMaterialCheck()
                Snackbar.make(view, priceNotSet, Snackbar.LENGTH_SHORT).show()
            }
            .setPositiveButton(accept) { dialog, which ->
                val textValue: String = edtDada.text.toString().replace(',', '.')

                if (textValue.toDoubleOrNull() == null || textValue.toDouble() == 0.0) {
                    setValueMaterial(id, 0.0)
                    clearMaterialCheck()
                    Snackbar.make(view, isNotNumber, Snackbar.LENGTH_SHORT).show()
                } else {
                    setValueMaterial(id, textValue.toDouble())

                    Snackbar.make(view, getMaterialName(id) + strEquals + textValue, Snackbar.LENGTH_SHORT).show()
                }
            }
            .show()
    }

    fun getMaterialName(id: Int): String {
        var materialName = ""
        when (id) {
            R.id.radio_diamond -> materialName = strDiamond
            R.id.radio_emerald -> materialName = strEmerald
            R.id.radio_sapphire -> materialName = strSapphire
            R.id.radio_ruby -> materialName = strRuby
        }
        return materialName
    }

    fun getCoinSimbleSelected(): String {
        val dollar: RadioButton = findViewById(R.id.radio_dollar)
        val won: RadioButton = findViewById(R.id.radio_won)

        if (dollar.isChecked) {
            return dollarSimble
        } else if (won.isChecked) {
            return wonSimble
        }

        return ""
    }

    fun getValueCoinSelected(): Double {
        val dollar: RadioButton = findViewById(R.id.radio_dollar)
        val won: RadioButton = findViewById(R.id.radio_won)

        if (dollar.isChecked) {
            return getValueCoin(R.id.radio_dollar)
        } else if (won.isChecked) {
            return getValueCoin(R.id.radio_won)
        }

        return 0.0
    }

    fun getValueMaterialSelected(): Double {
        val ruby: RadioButton = findViewById(R.id.radio_ruby)
        val emerald: RadioButton = findViewById(R.id.radio_emerald)
        val diamond: RadioButton = findViewById(R.id.radio_diamond)
        val sapphire: RadioButton = findViewById(R.id.radio_sapphire)

        if (ruby.isChecked) {
            return getValueMaterial(R.id.radio_ruby)
        } else if (emerald.isChecked) {
            return getValueMaterial(R.id.radio_emerald)
        } else if (diamond.isChecked) {
            return getValueMaterial(R.id.radio_diamond)
        } else if (sapphire.isChecked) {
            return getValueMaterial(R.id.radio_sapphire)
        }

        return 0.0
    }

    fun setSelectedMaterialRadio(id: Int) {
        setSelectedRadio(id)

        if (id != R.id.radio_ruby) {
            setNotSelectedRadio(R.id.radio_ruby)
        }

        if (id != R.id.radio_emerald) {
            setNotSelectedRadio(R.id.radio_emerald)
        }

        if (id != R.id.radio_diamond) {
            setNotSelectedRadio(R.id.radio_diamond)
        }

        if (id != R.id.radio_sapphire) {
            setNotSelectedRadio(R.id.radio_sapphire)
        }
    }

    fun setSelectedCoinRadio(id: Int) {
        setSelectedRadio(id)

        if (id != R.id.radio_dollar) {
            setNotSelectedRadio(R.id.radio_dollar)
        }

        if (id != R.id.radio_won) {
            setNotSelectedRadio(R.id.radio_won)
        }
    }

    fun setNotSelectedRadio(id: Int) {
        var buttonSelected: RadioButton = findViewById(id)

        buttonSelected.setBackgroundResource(R.drawable.border)
        buttonSelected.setTypeface(null, Typeface.NORMAL)
    }

    fun setSelectedRadio(id: Int) {
        var buttonSelected: RadioButton = findViewById(id)

        buttonSelected.setBackgroundResource(R.drawable.border_selected)
        buttonSelected.setTypeface(null, Typeface.BOLD)
    }

    fun setValueCoin(id: Int, value: Double) {
        when (id) {
            R.id.radio_dollar -> relativePriceDollar = value
            R.id.radio_won -> relativePriceWon = value
        }
    }

    fun setValueMaterial(id: Int, value: Double) {
        when (id) {
            R.id.radio_diamond -> relativePriceDiamond = value
            R.id.radio_emerald -> relativePriceEmerald = value
            R.id.radio_ruby -> relativePriceRuby = value
            R.id.radio_sapphire -> relativePriceSapphire = value
        }
    }

    fun getValueMaterial(id: Int): Double {
        when (id) {
            R.id.radio_diamond -> return relativePriceDiamond
            R.id.radio_emerald -> return relativePriceEmerald
            R.id.radio_ruby -> return relativePriceRuby
            R.id.radio_sapphire -> return relativePriceSapphire
        }

        return 0.0
    }

    fun getValueCoin(id: Int): Double {
        when (id) {
            R.id.radio_won -> return relativePriceWon
            R.id.radio_dollar -> return relativePriceDollar
        }

        return 0.0
    }

    fun clearMaterialCheck() {
        val radioMaterials: RadioGroup = findViewById(R.id.group_materials)
        radioMaterials.clearCheck()
    }

    fun clearCoinCheck() {
        val radioMaterials: RadioGroup = findViewById(R.id.group_coins)
        radioMaterials.clearCheck()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("inputValue", inputValue)

        outState.putDouble("priceWon", relativePriceWon)
        outState.putDouble("priceDollar", relativePriceDollar)

        outState.putDouble("priceRuby", relativePriceRuby)
        outState.putDouble("priceEmerald", relativePriceEmerald)
        outState.putDouble("priceDiamond", relativePriceDiamond)
        outState.putDouble("priceSapphire", relativePriceSapphire)

        outState.putString("outputValue", outputValue)

        var wonCheck = findViewById<RadioButton>(R.id.radio_won).isChecked
        var dollarCheck = findViewById<RadioButton>(R.id.radio_dollar).isChecked

        var rubyCheck = findViewById<RadioButton>(R.id.radio_ruby).isChecked
        var emeraldCheck = findViewById<RadioButton>(R.id.radio_emerald).isChecked
        var diamondCheck = findViewById<RadioButton>(R.id.radio_diamond).isChecked
        var sappgireCheck = findViewById<RadioButton>(R.id.radio_sapphire).isChecked

        outState.putBoolean("wonCheck", wonCheck)
        outState.putBoolean("dollarCheck", dollarCheck)

        outState.putBoolean("rubyCheck", rubyCheck)
        outState.putBoolean("emeraldCheck", emeraldCheck)
        outState.putBoolean("diamondCheck", diamondCheck)
        outState.putBoolean("sapphireCheck", sappgireCheck)

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        // get values from saved state
        inputValue = savedInstanceState.getDouble("inputValue")

        relativePriceWon = savedInstanceState.getDouble("priceWon")
        relativePriceDollar = savedInstanceState.getDouble("priceDollar")

        relativePriceRuby = savedInstanceState.getDouble("priceRuby")
        relativePriceEmerald = savedInstanceState.getDouble("priceEmerald")
        relativePriceDiamond = savedInstanceState.getDouble("priceDiamond")
        relativePriceSapphire = savedInstanceState.getDouble("priceSapphire")

        outputValue = savedInstanceState.getString("outputValue", "outputValue")

        var wonCheck = savedInstanceState.getBoolean("wonCheck")
        var dollarCheck = savedInstanceState.getBoolean("dollarCheck")

        var rubyCheck = savedInstanceState.getBoolean("rubyCheck")
        var emeraldCheck = savedInstanceState.getBoolean("emeraldCheck")
        var diamondCheck = savedInstanceState.getBoolean("diamondCheck")
        var sapphireCheck = savedInstanceState.getBoolean("sapphireCheck")

        findViewById<TextView>(R.id.tvInput).setText(inputValue.toString().replace('.', ','))
        findViewById<TextView>(R.id.tvResult).setText(outputValue)

        if (wonCheck) {
            setSelectedCoinRadio(R.id.radio_won)
        } else if (dollarCheck) {
            setSelectedCoinRadio(R.id.radio_dollar)
        }

        if (rubyCheck) {
            setSelectedMaterialRadio(R.id.radio_ruby)
        } else if (emeraldCheck) {
            setSelectedMaterialRadio(R.id.radio_emerald)
        } else if (diamondCheck) {
            setSelectedMaterialRadio(R.id.radio_diamond)
        } else if (sapphireCheck) {
            setSelectedMaterialRadio(R.id.radio_sapphire)
        }

        super.onRestoreInstanceState(savedInstanceState)
    }
}