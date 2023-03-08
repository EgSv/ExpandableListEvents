package ru.startandroid.develop.expandablelistevents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import android.widget.TextView

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {

    private var elvMain:ExpandableListView? = null
    private var ah:AdapterHelper? = null
    private var adapter:SimpleExpandableListAdapter? = null
    private var tvInfo:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInfo = findViewById(R.id.tvInfo)

        ah = AdapterHelper(this)
        adapter = ah?.getAdapter()
        elvMain = findViewById(R.id.elvMain)
        elvMain?.setAdapter(adapter)

        elvMain?.setOnChildClickListener { parent, v, groupPosition, childPosition , id ->
            Log.d(LOG_TAG, "onChildClick groupPosition = $groupPosition " +
                    "childPosition = $childPosition " +
                    "id = $id")
            tvInfo?.text = ah?.getGroupChildText(groupPosition, childPosition)
            false
        }

        elvMain?.setOnGroupClickListener { parent, v, groupPosition, id ->
            Log.d(LOG_TAG, "onGroupClick groupPosition = $groupPosition, " +
                    "id = $id")
            if (groupPosition == 1) true else false
        }

        elvMain?.setOnGroupCollapseListener { groupPosition ->
            Log.d(LOG_TAG, "onGroupCollapse groupPosition = $groupPosition")
            tvInfo?.text= "Свернули ${ah?.getGroupText(groupPosition)}"
        }

        elvMain?.setOnGroupExpandListener { groupPosition ->
            Log.d(LOG_TAG, "onGroupExpand groupPosition = $groupPosition")
            tvInfo?.text = "Рaзвернули ${ah?.getGroupText(groupPosition)}"
        }

        elvMain?.expandGroup(2)
    }
}