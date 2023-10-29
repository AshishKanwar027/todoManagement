package net.javaguides.todo.service;

import java.util.List;

import net.javaguides.todo.dto.TodoDto;

public interface TodoService {
    
    TodoDto addTodo(TodoDto todoDto);
    
    TodoDto getTodo(Long id);
    
    List<TodoDto> getALLTodo();
    
    TodoDto updateTodo(TodoDto todoDto, Long id);
    
    void deleteTodo(Long id);
    
    TodoDto completeTodo(Long id);
    
    TodoDto inCompleteDto(Long id);

}
