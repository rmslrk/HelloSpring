package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TodoMapper;

@Service
public class TodoService implements TodoServiceImpl{

    @Autowired
    private TodoMapper todoMapper;


}
