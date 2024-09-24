package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto update(ItemDto itemDto, Long itemId, Long userId);

    ItemDto getById(Long itemId);

    List<ItemDto> getAll(Long userId);

    List<ItemDto> search(String text);
}
