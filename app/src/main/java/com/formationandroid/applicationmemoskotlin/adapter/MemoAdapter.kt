package com.formationandroid.applicationmemoskotlin.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.formationandroid.applicationmemoskotlin.R
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO
import com.formationandroid.applicationmemoskotlin.viewHolder.MemoViewHolder
import com.formationandroid.applicationmemoskotlin.viewmodels.MemoVIewModels
import java.util.*


class MemoAdapter(var listeMemos: MutableList<MemoDTO>, val memoVIewModels: MemoVIewModels) : RecyclerView.Adapter<MemoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val viewMemo: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(viewMemo)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.textViewIntitule?.text = listeMemos[position].intitule
    }

    override fun getItemCount(): Int {
        return listeMemos.size
    }

    /**
     * Ajout d'un mémo à la liste.
     * @param memo Mémo
     */
    fun ajouterMemo(memo: MemoDTO) {
        listeMemos.add(0, memo)
        notifyItemInserted(0)
    }

    fun getItemParPosition(position: Int): MemoDTO {
        return listeMemos[position]
    }

    // Appelé à chaque changement de position, pendant un déplacement.
    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(listeMemos, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }
    // Appelé une fois à la suppression.
    fun onItemDismiss(position: Int) {
        if (position > -1) {
            memoVIewModels.removeMemo(listeMemos[position])
            listeMemos.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Constructeur.
     * @param listeMemos Liste de mémos
     */
    init {
        this.listeMemos = listeMemos
    }
}