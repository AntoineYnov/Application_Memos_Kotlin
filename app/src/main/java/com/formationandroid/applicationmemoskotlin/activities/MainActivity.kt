package com.formationandroid.applicationmemoskotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.formationandroid.applicationmemoskotlin.R
import com.formationandroid.applicationmemoskotlin.adapter.MemoAdapter
import com.formationandroid.applicationmemoskotlin.database.AppDatabaseHelper
import com.formationandroid.applicationmemoskotlin.dto.MemoDTO
import com.formationandroid.applicationmemoskotlin.fragment.DetailFragment
import com.formationandroid.applicationmemoskotlin.repository.Repository
import com.formationandroid.applicationmemoskotlin.swipe.Swiper
import com.formationandroid.applicationmemoskotlin.viewmodels.MemoVIewModels


class MainActivity : AppCompatActivity(), OnItemTouchListener {

    // Models
    lateinit var memoViewModel: MemoVIewModels
    lateinit var memoList: MutableList<MemoDTO>

    //Vues
    private var recyclerView: RecyclerView? = null
    private var editTextMemo: EditText? = null
    private var frameLayoutConteneurDetail: FrameLayout? = null

    // Adapter :
    private var memoAdapter: MemoAdapter? = null

    // Gesture detector :
    private var gestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val lastPosition = preferences.getInt("lastPosition", 0)
        if (lastPosition != 0) {
            Toast.makeText(this, "la dernière position était :$lastPosition", Toast.LENGTH_SHORT)
                .show()
        }
        AppDatabaseHelper.getDatabase(this)
        editTextMemo = findViewById(R.id.saisie_memo)
        recyclerView = findViewById(R.id.liste_memos)
        frameLayoutConteneurDetail = findViewById(R.id.conteneur_detail)

        memoViewModel = ViewModelProvider(this).get(MemoVIewModels::class.java)
        memoViewModel.init(Repository())

        this.recyclerView = findViewById(R.id.liste_memos)

        // à ajouter pour de meilleures performances :
        this.recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        this.recyclerView?.layoutManager = layoutManager

        // contenu :
        memoList = memoViewModel.getMemos()!!.toMutableList()


        // adapter
        memoAdapter = MemoAdapter(memoList, memoViewModel)
        this.recyclerView?.adapter = memoAdapter


        // listener :
        this.recyclerView?.addOnItemTouchListener(this)

        val itemTouchHelper = ItemTouchHelper(Swiper(memoAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        gestureDetector = GestureDetector(this,
            object : SimpleOnGestureListener() {
                override fun onSingleTapUp(event: MotionEvent): Boolean {
                    return true
                }
            })
    }

    /**
     * Listener clic bouton valider.
     * @param view Bouton valider
     */
    fun onClickBoutonValider(view: View?) {
        // ajout du mémo :
        if(editTextMemo!!.text.isNotBlank()) {
            val memoDTO = MemoDTO(editTextMemo!!.text.toString())
            memoAdapter?.ajouterMemo(memoDTO)
            memoViewModel.addMemo(memoDTO)
            // animation de repositionnement de la liste (sinon on ne voit pas l'item ajouté) :
            recyclerView!!.smoothScrollToPosition(0)

            // on efface le contenu de la zone de saisie :
            editTextMemo!!.setText("")
        }
    }

    override fun onInterceptTouchEvent(
        rv: RecyclerView,
        motionEvent: MotionEvent
    ): Boolean {
        if (gestureDetector!!.onTouchEvent(motionEvent)) {
            // récupération de l'item cliqué :
            val child =
                recyclerView!!.findChildViewUnder(motionEvent.x, motionEvent.y)
            if (child != null) {
                // position dans la liste d'objets métier (position à partir de zéro !) :
                val position = recyclerView!!.getChildAdapterPosition(child)

                // récupération du mémo à cette position :
                val memo: MemoDTO = memoAdapter!!.getItemParPosition(position)
                Toast.makeText(
                    this,
                    "La position du mémo est:" + (position + 1),
                    Toast.LENGTH_SHORT
                ).show()

                // affichage du détail :
                if (frameLayoutConteneurDetail != null) {
                    // fragment :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString(DetailFragment.EXTRA_MEMO, memo.intitule)
                    fragment.setArguments(bundle)

                    // le conteneur de la partie détail est disponible, on est donc en mode "tablette" :
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.conteneur_detail, fragment).commit()
                } else {
                    // le conteneur de la partie détail n'est pas disponible, on est donc en mode "smartphone" :
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(DetailFragment.EXTRA_MEMO, memo.intitule)
                    startActivity(intent)
                }
                val preferences =
                    PreferenceManager.getDefaultSharedPreferences(this)
                val editor = preferences.edit()
                editor.putInt("lastPosition", position + 1)
                editor.apply()
                return true
            }
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}
