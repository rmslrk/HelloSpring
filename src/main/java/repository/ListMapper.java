package repository;

import domain.ListDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface ListMapper {
    void createList(ListDTO listDTO);

    ListDTO getList(Long id);

    void updateList(ListDTO listDTO);

    void moveList(ListDTO listDTO);

    void deleteList(ListDTO listDTO);

    Long isExistsList(Long id);
}
