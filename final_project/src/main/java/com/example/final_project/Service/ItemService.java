package com.example.final_project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.final_project.Model.ItemQuantity;
import com.example.final_project.Repository.ItemRepository;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<ItemQuantity> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemQuantity getItemById(Long idItem) {
        return itemRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item non trovato con id: " + idItem));
    }

    // Mancava: creare un nuovo item
    public ItemQuantity createItem(ItemQuantity item) {
        return itemRepository.save(item);
    }

    public ItemQuantity updateQtn(Long idItem, int qtn) {
        ItemQuantity item = itemRepository.findById(idItem)
                .orElseThrow(() -> new RuntimeException("Item non trovato con id: " + idItem));
        item.setQtn(qtn);
        return itemRepository.save(item);
    }

    public void deleteItem(Long idItem) {
        itemRepository.deleteById(idItem);
    }
}