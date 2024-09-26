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
import br.com.mizaeldouglas.sqlite_android.model.produto.Produto
import br.com.mizaeldouglas.sqlite_android.model.produto.ProdutoDAO

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
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(-1, title, "Descrição")
        produtoDAO.create(produto)

    }

    private fun findAll() {
        val produtoDAO = ProdutoDAO(this)
        val listProduto = produtoDAO.findAll()

        if (listProduto.isNotEmpty()){
            listProduto.forEach{ produto ->
                Log.i("info_db", "${produto.idProduto} - ${produto.title}")
            }
        }
    }

    private fun update() {
        val title = binding.editProduto.text.toString()
        val produtoDAO = ProdutoDAO(this)
        val produto = Produto(2, title, "Descrição")
        produtoDAO.update(produto)

    }

    private fun delete() {
        val produtoDAO = ProdutoDAO(this)
        produtoDAO.delete(3)
    }
}