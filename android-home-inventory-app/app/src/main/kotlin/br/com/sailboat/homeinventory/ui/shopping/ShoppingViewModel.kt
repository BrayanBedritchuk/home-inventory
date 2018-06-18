package br.com.sailboat.homeinventory.ui.shopping

import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem
import javax.inject.Inject

class ShoppingViewModel @Inject constructor() {

    val shoppingItems = mutableListOf<RecyclerViewItem>()
    val shoppingCart = HashMap<Long, Int>()

}