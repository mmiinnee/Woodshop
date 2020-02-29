package th.com.woodshop.employee.mainemployee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.layout_list_item.view.*
import th.com.woodshop.R
import th.com.woodshop.model.EmployeeEntity

class EmployeeAdapter(private val callBack: (Boolean, String) -> Unit) : RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {

    private var mItems: MutableList<EmployeeEntity> = mutableListOf()
    private val viewBinderHelper = ViewBinderHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewBinderHelper.setOpenOnlyOne(true)
        viewBinderHelper.bind(holder.itemView.swipelayout, mItems[position].toString())
        viewBinderHelper.closeLayout(mItems[position].toString())
        holder.bindView(mItems[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(item: EmployeeEntity) {
            itemView.tv_employee_number.text = "รหัสพนักงาน : ${item.employeeNumber}"

            itemView.tv_employee_name.text = "ชื่อพนักงาน : ${item.employeeName} ${item.employeeLastName}"

            itemView.tv_employee_number_phone.text = "เบอร์โทรศัพท์ : ${item.employeePhoneNumber}"

            itemView.tv_edit.setOnClickListener { callBack.invoke(true, item.employeeNumber!!) }

            itemView.tv_delete.setOnClickListener { callBack.invoke(false, item.employeeNumber!!) }
        }
    }

    fun setListData(response: MutableList<EmployeeEntity>) {
        mItems.clear()
        mItems.addAll(response)
        notifyDataSetChanged()
    }
}