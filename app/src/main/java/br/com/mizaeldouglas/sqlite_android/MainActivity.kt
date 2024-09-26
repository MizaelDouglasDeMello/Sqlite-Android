package br.com.mizaeldouglas.sqlite_android

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.DESCRICAO_PRODUTOS
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.ID_PRODUTO
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.TABELA_PRODUTOS
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.TITLE_PRODUTOS
import br.com.mizaeldouglas.sqlite_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val bd by lazy {
        DatabaseHelper(this)
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding) {
            btnSalvar.setOnClickListener {
                create()
            }
            btnAtualizar.setOnClickListener {
                update()
            }
            btnListar.setOnClickListener {
                findAll()
            }
            btnDeletar.setOnClickListener {
                delete()
            }

        }

    }

    private fun create() {

        val title = binding.editProduto.text.toString()

        val sql = "INSERT INTO $TABELA_PRODUTOS VALUES(NULL, '${title}', 'Descricao...');"
        try {
            bd.writableDatabase.execSQL(
                sql
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findAll() {
        val sql = "SELECT * FROM $TABELA_PRODUTOS"
        val cursor = bd.readableDatabase.rawQuery(sql, null)


        val indiceId = cursor.getColumnIndex(ID_PRODUTO)
        val indiceTitle = cursor.getColumnIndex(TITLE_PRODUTOS)
        val indiceDescicao = cursor.getColumnIndex(DESCRICAO_PRODUTOS)

        while (cursor.moveToNext()){
            val idProduto = cursor.getInt(indiceId)
            val title = cursor.getString(indiceTitle)
            val decricao = cursor.getString(indiceDescicao)
            Log.i("findAll", "id: $idProduto - titulo: $title.")
        }
    }

    private fun update() {
        val title = binding.editProduto.text.toString()

        val sql = "UPDATE $TABELA_PRODUTOS SET $TITLE_PRODUTOS = '$title' WHERE $ID_PRODUTO = 1"
        try {
            bd.writableDatabase.execSQL(
                sql
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun delete() {
        val sql = "DELETE FROM $TABELA_PRODUTOS WHERE $ID_PRODUTO = 1"

        try {
            bd.writableDatabase.execSQL(
                sql
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}