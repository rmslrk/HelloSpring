package repository;

import domain.TodoDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoMapper {
    void createTodolist(TodoDTO todo);

    TodoDTO getTodolist(Long tid);

    void updateTodolist(TodoDTO todo);

    void deleteTodolist(TodoDTO todo);

    Long isExistsTodolist(Long tid);
}
