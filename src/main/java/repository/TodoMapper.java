package repository;

import domain.TodoDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoMapper {
    void createMemo(TodoDTO memo);
    TodoDTO getMemo(Long timeTableId);
    void updateMemo(TodoDTO memo);
    void deleteMemo(TodoDTO memo);
    Long isExistsMemo(Long timeTableId);
    Long getUserIdByTimeTablesId(Long timetable_id);

}
