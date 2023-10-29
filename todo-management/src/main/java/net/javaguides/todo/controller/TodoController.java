package net.javaguides.todo.controller;

import java.security.PublicKey;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    
    private TodoService todoService;
    

    //Build add todo Rest API
     @PostMapping("/add")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto saveTodoDto = todoService.addTodo(todoDto);
        
        return new ResponseEntity<>(saveTodoDto,HttpStatus.CREATED);
    }
     
     
     //build get todo Rest api
     
     @GetMapping("/get/{id}")
     public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
         TodoDto todoDto = todoService.getTodo(todoId);
         
         return new ResponseEntity<>(todoDto,HttpStatus.OK);
         
     }
    
     //build gell ALL todo Rest ApI
     
     @GetMapping("/getAll")
     public ResponseEntity<List<TodoDto>> getAllTodo(){
         List<TodoDto> allTodo = todoService.getALLTodo();
         
         return new ResponseEntity<>(allTodo,HttpStatus.OK);
     }
     
     
     //build update todo rest Api
     @PutMapping("/update/{id}")
     public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId){
         
         TodoDto updateTodo = todoService.updateTodo(todoDto, todoId);
         
         return ResponseEntity.ok(updateTodo);
         
     }
     
     //delete
     @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
         todoService.deleteTodo(todoId);
         return ResponseEntity.ok("Todo Delteted");
     }
     
     //Complete todo Rest API
     @PatchMapping("/complete/{id}")
     public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId){
         TodoDto completeTodo = todoService.completeTodo(todoId);
         return ResponseEntity.ok(completeTodo);
     }
     
     // incomplete todo
     @PatchMapping("/inComplete/{id}")
     public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
         TodoDto completeTodo = todoService.inCompleteDto(todoId);
         return ResponseEntity.ok(completeTodo);
     }
    
}
