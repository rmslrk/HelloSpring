package service;

import domain.TodoDTO;
import domain.UserDTO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import repository.TodoMapper;
import response.BaseResponse;

import java.util.Calendar;
import java.util.Date;

@Service
public class TodoService implements TodoServiceImpl{

    @Autowired
    private TodoMapper todoMapper;

/*
    @Override
    public BaseResponse createTodolist(TodoDTO todo){

    }

 */
}
