package ru.practicum.shareit.item.service;

import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.practicum.shareit.item.dto.ItemMapper.toItemDto;

@Service
@Slf4j
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemDto create(Long userId, ItemDto itemDto) {
        User user = findUserById(userId);
        validateByItem(itemDto);
        return toItemDto(itemRepository.save(ItemMapper.toItem(itemDto, user)));
    }

    @Override
    public ItemDto update(ItemDto itemDto, Long itemId, Long userId) {
        findUserById(userId);
        Item item = findItemById(itemId);
        validateByItem(itemDto);

        if (!item.getOwner().getId().equals(userId)) {
            throw new NotFoundException("Owner doesn't equals userId");
        }
        item.setName(itemDto.getName());
        item.setAvailable(itemDto.getAvailable());
        item.setDescription(itemDto.getDescription());
        return toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto getById(Long itemId) {
        Item item = findItemById(itemId);
        return toItemDto(itemRepository.getById(itemId));
    }

    @Override
    public List<ItemDto> getAll(Long userId) {
        List<ItemDto> items = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            if (item.getOwner().getId().equals(userId)) {
                items.add(toItemDto(item));
            }
        }
        return items;
    }

    @Override
    public List<ItemDto> search(String text) {
        List<ItemDto> items = new ArrayList<>();
        if (text.isBlank()) {
            return items;
        }
        for (Item item : itemRepository.search(text)) {
            items.add(ItemMapper.toItemDto(item));
        }
        return items;
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id:" + userId));
    }

    private Item findItemById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item not found with id:" + itemId));
    }

    private void validateByItem(ItemDto itemDto) {

        if (itemDto.getName().isBlank() || itemDto.getName() == null) {
            log.info("There is no name field");
            throw new ValidationException("There is no name field");
        }
        if (itemDto.getDescription().isBlank() || itemDto.getDescription() == null) {
            log.info("There if no description field");
            throw new ValidationException("There if no description field");
        }
        if (itemDto.getAvailable() == null) {
            log.info("There if no available field");
            throw new ValidationException("There if no available field");
        }
    }
}
