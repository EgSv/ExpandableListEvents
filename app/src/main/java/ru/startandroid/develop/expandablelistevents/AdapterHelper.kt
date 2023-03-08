package ru.startandroid.develop.expandablelistevents

import android.content.Context
import android.widget.SimpleExpandableListAdapter


const val ATTR_GROUP_NAME = "groupName"
const val ATTR_PHONE_NAME = "phoneName"

class AdapterHelper(_ctx: Context) {

    var groups = arrayOf("HTC", "Samsung", "LG")

    var phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero")
    var phonesSams = arrayOf("Galaxy S II", "Galaxy Nexus", "Wave")
    var phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    var groupData:ArrayList<Map<String, String?>>? = null
    var childDataItem:ArrayList<Map<String, String?>>? = null
    var childData:ArrayList<ArrayList<Map<String, String?>>>? = null

    var m:MutableMap<String, String?>? = null
    var ctx: Context
    var adapter: SimpleExpandableListAdapter? = null

    init {
        ctx = _ctx
    }

    @JvmName("getAdapter1")
    fun getAdapter(): SimpleExpandableListAdapter {
        groupData = ArrayList()
        for (group in groups) {
            m = HashMap()
            (m as HashMap<String, String>).put(ATTR_GROUP_NAME, group)
            groupData?.add(m!!)
        }

        val groupFrom = arrayOf(ATTR_GROUP_NAME)
        val groupTo = intArrayOf(android.R.id.text1)

        childData = arrayListOf()

        childDataItem = arrayListOf()
        for (phone in phonesHTC) {
            m = HashMap()
            (m as HashMap<String, String>).put(ATTR_PHONE_NAME, phone)
            childDataItem?.add(m!!)
        }
        childData?.add(childDataItem!!)

        childDataItem = arrayListOf()
        for (phone in phonesSams) {
            m = HashMap()
            (m as HashMap<String, String>).put(ATTR_PHONE_NAME, phone)
            childDataItem?.add(m!!)
        }
        childData?.add(childDataItem!!)

        childDataItem = arrayListOf()
        for (phone in phonesLG) {
            m = HashMap()
            (m as HashMap<String, String>).put(ATTR_PHONE_NAME, phone)
            childDataItem?.add(m!!)
        }
        childData?.add(childDataItem!!)

        val childFrom = arrayOf(ATTR_PHONE_NAME)

        val childTo = intArrayOf(android.R.id.text1)

        adapter = SimpleExpandableListAdapter(
            ctx,
            groupData,
            android.R.layout.simple_expandable_list_item_1,
            groupFrom,
            groupTo,
            childData,
            android.R.layout.simple_list_item_1,
            childFrom,
            childTo)
        return adapter!!
    }

    fun getGroupText(groupPos: Int): String? {
        return (adapter!!.getGroup(groupPos) as Map<String?, String?>)[ATTR_GROUP_NAME]
    }

    fun getChildText(groupPos: Int, childPos: Int): String? {
        return (adapter!!.getChild(groupPos, childPos) as Map<String, String?>)[ATTR_PHONE_NAME]
    }

    fun getGroupChildText(groupPos: Int, childPos: Int): String {
        return "${getGroupText(groupPos)} ${getChildText(groupPos, childPos)}"
    }
}