package br.com.mizaeldouglas.sqlite_android.model.produto

import android.content.Context
import android.util.Log
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.DESCRICAO_PRODUTOS
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.ID_PRODUTO
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.TABELA_PRODUTOS
import br.com.mizaeldouglas.sqlite_android.database.DatabaseHelper.Companion.TITLE_PRODUTOS

class ProdutoDAO(context: Context) : IProdutoDAO {

    val write = DatabaseHelper(context).writableDatabase
    val read = DatabaseHelper(context).readableDatabase
    val escrita = DatabaseHelper(context).writableDatabase
    val leitura = DatabaseHelper(context).readableDatabase

    override fun create(produto: Produto): Boolean {
        val title = produto.title

        val sql = "INSERT INTO $TABELA_PRODUTOS VALUES(NULL, '$title', 'Descrição');"
        try {
            write.execSQL(sql)
            Log.i("info_db", "Sucesso ao inserir")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao inserir")
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun update(produto: Produto): Boolean {
        val title = produto.title
        val idProduto = produto.idProduto
        val sql =
            "UPDATE $TABELA_PRODUTOS SET $TITLE_PRODUTOS = '$title' WHERE $ID_PRODUTO = $idProduto"
        try {
            read.execSQL(sql)
            Log.i("info_db", "Sucesso ao atualizar")
        } catch (e: Exception) {
            Log.i("info_db", "Erro ao atualizar")
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun delete(idProduto: Int): Boolean {
        val sql = "DELETE FROM $TABELA_PRODUTOS WHERE $ID_PRODUTO = $idProduto"

        try {
            write.execSQL(
                sql
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    override fun findAll(): List<Produto> {

        val listProduto = mutableListOf<Produto>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_PRODUTOS};"
        val cursor = leitura.rawQuery(sql, null)


        val indiceId = cursor.getColumnIndex(ID_PRODUTO)
        val indiceTitle = cursor.getColumnIndex(TITLE_PRODUTOS)
        val indiceDescicao = cursor.getColumnIndex(DESCRICAO_PRODUTOS)

        while (cursor.moveToNext()) {
            val idProduto = cursor.getInt(indiceId)
            val title = cursor.getString(indiceTitle)
            val decricao = cursor.getString(indiceDescicao)
            listProduto.add(Produto(idProduto, title, decricao))
        }
        return listProduto
    }


}