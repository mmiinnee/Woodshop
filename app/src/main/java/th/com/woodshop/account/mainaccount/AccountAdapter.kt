package th.com.woodshop.account.mainaccount

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chauthai.swipereveallayout.ViewBinderHelper
import kotlinx.android.synthetic.main.layout_list_item.view.swipelayout
import kotlinx.android.synthetic.main.layout_list_item.view.tv_delete
import kotlinx.android.synthetic.main.layout_list_item_account.view.*
import th.com.woodshop.R
import th.com.woodshop.model.AccountEntity

class AccountAdapter(private val callBack: (Int) -> Unit) : RecyclerView.Adapter<AccountAdapter.ViewHolder>() {

    private var mItems: MutableList<AccountEntity> = mutableListOf()
    private val viewBinderHelper = ViewBinderHelper()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item_account, parent, false)
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
        fun bindView(item: AccountEntity) {
            itemView.tv_type.text = "ประเภท : ${item.type}"

            itemView.tv_date.text = "วันที่ : ${item.date}"

            itemView.tv_detail.text = "รายละเอียด : ${item.detail}"

            itemView.tv_price.text = "จำนวนเงิน : ${item.price} บาท"

            itemView.tv_delete.setOnClickListener { callBack.invoke(item.rowid) }
        }
    }

    fun setListData(response: MutableList<AccountEntity>) {
        mItems.clear()
        mItems.addAll(response)
        notifyDataSetChanged()
    }
}