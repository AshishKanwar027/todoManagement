package net.javaguides.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.entity.Todo;
import net.javaguides.todo.exception.ResourceNotFoundException;
import net.javaguides.todo.repository.TodoRepository;
import net.javaguides.todo.service.TodoService;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    
    private ModelMapper modelMapper;
    
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert todoDTo into todo jpa entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
        Todo todo =modelMapper.map(todoDto, Todo.class);
        
        //todo jpa entity
        Todo savedTodo = todoRepository.save(todo);
        
        //convert saved saved todo jpa entity object into todoDto objet
//        TodoDto saveTodoDto =new TodoDto();
//        saveTodoDto.setId(savedTodo.getId());
//        saveTodoDto.setTitle(savedTodo.getTitle());
//        saveTodoDto.setDescription(savedTodo.getDescription());
//        saveTodoDto.setCompleted(savedTodo.isCompleted());
        TodoDto saveTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return saveTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo Not Found with id : "+id));
        
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getALLTodo() {
        // TODO Auto-generated method stub
        List<Todo> findAll = todoRepository.findAll();
        return findAll.stream().map((todo)->modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        // TODO Auto-generated method stub
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->
                new ResourceNotFoundException("Todo Not Found with id : "+id));
        
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        
        Todo save = todoRepository.save(todo);
        
        return modelMapper.map(save, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        // TODO Auto-generated method stub
       Todo todo =  todoRepository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Todo not found id : %s "+id)
        );
       todoRepository.deleteById(id); 
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException("Todo not found id : %s "+id)
    );
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteDto(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException("Todo not found id : %s "+id)
    );
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

}
