package ru.practicum.shareit.item;

import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

@UtilityClass
public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static Item toItem(ItemDto itemDto, User user) {
        return Item.builder()
                .owner(user)
                .id(itemDto.getId())
                .request(itemDto.getRequest())
                .available(itemDto.getAvailable())
                .description(itemDto.getDescription())
                .name(itemDto.getName())
                .build();
    }
}
