package service;

import domain.ListDTO;
import domain.UserDTO;
import exception.BaseException;
import exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ListMapper;

@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListMapper listMapper;

    @Autowired
    private UserService userService;


    @Override
    public void createList(ListDTO list) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        listMapper.createList(list);
    }


    @Override
    public ListDTO getList(Long lid) throws Exception {
        ListDTO list = new ListDTO();

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리

        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 해당 cid의 카드가 존재하지 않는다면 임의로 값을 넣어 출력
        if(listMapper.isExistsList(lid)==null) {
            list.setTitle("");
            return list;
        }

        list = listMapper.getList(lid);

        return list;
    }

    @Override
    public void updateList(ListDTO list) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);
        Long userId = user.getUid();

        if(listMapper.isExistsList(list.getLid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        listMapper.updateList(list);
    }

    @Override
    public void moveList(ListDTO list) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(listMapper.isExistsList(list.getLid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        listMapper.moveList(list);
    }

    @Override
    public void deleteList(ListDTO list) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        if(listMapper.isExistsList(list.getLid())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        listMapper.deleteList(list);
    }
}
