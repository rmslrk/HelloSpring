package repository;

import domain.ListDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListMapper {
    void createList(@Param("list") ListDTO listDTO);

    ListDTO getList(Long id);

    void updateList(@Param("list") ListDTO listDTO);

    void moveList(@Param("list") ListDTO listDTO);

    void deleteList(@Param("list") ListDTO listDTO);

    Long isExistsList(Long id);
}
