package ru.gb.dz4

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.checkbox.MaterialCheckBox.STATE_UNCHECKED
import com.google.android.material.snackbar.Snackbar
import ru.gb.dz4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, buttonId ->
            checkAll()
        }
        binding.switchMaterial.setOnCheckedChangeListener() { _, _ ->
            checkState()
            checkAll()
        }
        binding.textButton.setOnClickListener {
            showSnackbar("Изменения сохранены")
        }
        binding.editText1.doOnTextChanged { text, start, before, count ->
            checkAll()
        }
        binding.editText2.doOnTextChanged { text, start, before, count ->
            checkAll()
        }
        binding.checkbox1.setOnClickListener {
            checkAll()
        }
        binding.checkbox2.setOnClickListener {
            checkAll()
        }
    }

    private fun setup() {
        with(binding) {
            photo.scaleType = ImageView.ScaleType.CENTER_INSIDE
            textName.counterMaxLength = 40
            progress.progress = Random.nextInt(101)
            scoresDigit.text = binding.progress.progress.toString() + "/100"
            textButton.isEnabled = false
        }
    }

    private fun checkAll() {
        var switchAdd: Boolean = false
        if (((binding.switchMaterial.isChecked) &&
                    ((binding.checkbox1.isChecked) || (binding.checkbox2.isChecked))) ||
            (!binding.switchMaterial.isChecked)
        ) {
            switchAdd = true
        }
        if ((binding.editText1.length() in 1..40) &&
            (binding.editText2.length() in 1..17) &&
            ((binding.radioButtonOne.isChecked) || (binding.radioButtonTwo.isChecked)) &&
            (switchAdd)
        ) {
            binding.textButton.isEnabled = true
        } else binding.textButton.isEnabled = false
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    private fun checkState() {
        if (binding.switchMaterial.isChecked) {
            with(binding) {
                switchMaterial.isChecked = true
                checkbox1.isEnabled = true
                checkbox2.isEnabled = true
            }
        } else {
            with(binding) {
                switchMaterial.isChecked = false
                checkbox1.isEnabled = false
                checkbox2.isEnabled = false
                checkbox1.checkedState = STATE_UNCHECKED
                checkbox2.checkedState = STATE_UNCHECKED
            }
        }
    }
}