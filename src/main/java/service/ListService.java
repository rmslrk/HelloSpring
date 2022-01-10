package service;

import domain.ListDTO;

public interface ListService {
    void createList(ListDTO listDTO) throws Exception;
    ListDTO getList(Long cid) throws Exception;
    void deleteList(ListDTO listDTO) throws Exception;
    void updateList(ListDTO listDTO) throws Exception;
}
