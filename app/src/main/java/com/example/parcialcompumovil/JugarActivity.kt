package com.example.parcialcompumovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JugarActivity : AppCompatActivity() {

    private lateinit var sudokuBoard: Array<Array<Button>>
    private var currentValue: Array<Array<Int>> = Array(3) { Array(3) { 0 } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jugar)

        initializeSudokuBoard()
        setButtonClickListeners()

        val validacion = findViewById<TextView>(R.id.validacion)
        val verificar = findViewById<Button>(R.id.jugarOtraVez)
        verificar.setOnClickListener {
            val esValido = validateSudoku()
            if (esValido) {
                validacion.text = "El sudoku es valido"
            } else {
                validacion.text = "El sudoku no es valido"
            }
        }
    }

    private fun initializeSudokuBoard() {
        sudokuBoard = Array(3) { Array(3) { Button(this) } }

        sudokuBoard[0][0] = findViewById(R.id.btn11)
        sudokuBoard[0][1] = findViewById(R.id.btn12)
        sudokuBoard[0][2] = findViewById(R.id.btn13)
        sudokuBoard[1][0] = findViewById(R.id.btn21)
        sudokuBoard[1][1] = findViewById(R.id.btn22)
        sudokuBoard[1][2] = findViewById(R.id.btn23)
        sudokuBoard[2][0] = findViewById(R.id.btn31)
        sudokuBoard[2][1] = findViewById(R.id.btn32)
        sudokuBoard[2][2] = findViewById(R.id.btn33)
    }

    private fun setButtonClickListeners() {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val button = sudokuBoard[i][j]
                button.setOnClickListener {
                    updateButtonValue(button)
                }
            }
        }
    }

    private fun updateButtonValue(button: Button) {
        val i = sudokuBoard.indexOfFirst { row -> row.contains(button) }
        val j = sudokuBoard[i].indexOf(button)

        currentValue[i][j]++
        if (currentValue[i][j] > 9) currentValue[i][j] = 1
        button.text = currentValue[i][j].toString()
    }

    private fun validateSudoku(): Boolean {
        if (!isSudokuEmpty()) return false // Si el Sudoku está vacío, no es válido

        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (!isValidRow(i) || !isValidColumn(j) || !isValidSubgrid(i, j)) {
                    return false
                }
            }
        }

        // Si el Sudoku no está vacío y se cumplen todas las reglas hasta el punto actual, es válido
        return true
    }


    private fun isSudokuEmpty(): Boolean {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val button = sudokuBoard[i][j]
                if (button.text.isBlank()) {
                    return false // Si al menos un botón está vacío, retorna false
                }
            }
        }
        return true // Si ningún botón está vacío, retorna true
    }


    private fun isValidRow(row: Int): Boolean {
        val values = mutableSetOf<Int>()
        for (j in 0 until 3) {
            val button = sudokuBoard[row][j]
            val value = button.text.toString().toIntOrNull()
            if (value != null && values.contains(value)) {
                return false
            }
            if (value != null) {
                values.add(value)
            }
        }
        return true
    }

    private fun isValidColumn(column: Int): Boolean {
        val values = mutableSetOf<Int>()
        for (i in 0 until 3) {
            val button = sudokuBoard[i][column]
            val value = button.text.toString().toIntOrNull()
            if (value != null && values.contains(value)) {
                return false
            }
            if (value != null) {
                values.add(value)
            }
        }
        return true
    }

    private fun isValidSubgrid(row: Int, column: Int): Boolean {
        val values = mutableSetOf<Int>()
        val startRow = row / 3 * 3
        val startColumn = column / 3 * 3
        for (i in startRow until startRow + 3) {
            for (j in startColumn until startColumn + 3) {
                val button = sudokuBoard[i][j]
                val value = button.text.toString().toIntOrNull()
                if (value != null && values.contains(value)) {
                    return false
                }
                if (value != null) {
                    values.add(value)
                }
            }
        }
        return true
    }
}
