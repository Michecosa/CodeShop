package com.example.final_project.Service;

import java.util.List;

@Service
public class CarrelloMapper {
    @Autowired
    ItemMapper itemMapper;

    public CarrelloPageDto toDto(Carrello c) {
        List<ItemAmmazzonDto> items = itemMapper.toDtos(c.getItems());

        return new CarrelloPageDto(c.getId(), items, c.getTotale());
    }

}
