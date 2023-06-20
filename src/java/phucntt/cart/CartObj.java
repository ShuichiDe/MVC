/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phucntt.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class CartObj implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addItemToCart(String sku){
        if(sku == null){
            return;
        }
        if(sku.trim().isEmpty()){
            return;
        }
        //1. Check existed items
        if(this.items == null){
            this.items = new HashMap<>();
        }// items have not existed
        //2. Check esixted item
        int quantity = 1;
        if(this.items.containsKey(sku)){
            quantity = this.items.get(sku) + 1;
        }
        //3. Update items
        this.items.put(sku, quantity);
    }
    
    public void removeItemFromCart(String sku){
        //1. Check existed items ( Gio hang co ton tai hay ko)
        if(this.items != null){
            return;
        }
        //2. Check existed item ( Mon hang co ton tai hay ko)
        if (this.items.containsKey(sku)){
            this.items.remove(sku);
            
            if(this.items.isEmpty()){
                this.items = null;
            }// Khong bao gio chap nhan 1 doi tuong co ton tai ma ben trong khong chua gi ca
             // Neu ben trong khong co gi thi huy vung nho luon, khong cap phat vung nho cho no
            
        }// end item has esixted in items
    }
}
