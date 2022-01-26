package service;

import domain.CardCriteria;
import domain.CardDTO;
import domain.UserDTO;
import exception.BaseException;
import exception.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.CardMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private UserService userService;

    @Autowired
    private CardMapper cardMapper;

    // 카드 생성 메소드
    @Override
    public void createCard(CardDTO card, Long lid) throws Exception {

        UserDTO user = userService.getLoginUser();

        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        cardMapper.createCard(card, lid);
    }

    // 검색하여 읽어온 카드를 정렬한 메소드
    @Override
    public Map<String, Object> getSearchCardList(CardCriteria cardCriteria) throws Exception {
        String[] sortList = {"생성순", "수정순", "목표일자순"};
        // 정렬 기준 검사
        if(cardCriteria.getSort()!=null && !Arrays.asList(sortList).contains(cardCriteria.getSort()))
            throw new BaseException(ErrorMessage.VALIDATION_FAIL_EXCEPTION);

        Map<String, Object> map = new HashMap<>();
        map.put("count", cardMapper.getCountCardList(cardCriteria, userService.getLoginUser()));
        map.put("result", cardMapper.getSearchCardList(cardCriteria, userService.getLoginUser()));

        return map;
    }

    // 목표 일자가 3일 이내이며 아직 성공하지 못한 카드를 읽어오는 메소드 
    @Override
    public Map<String, Object> getExpireCardList(CardCriteria cardCriteria) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("result", cardMapper.getExpireCardList(cardCriteria, userService.getLoginUser()));

        return map;
    }

    // 카드 정보 읽기 메소드
    @Override
    public CardDTO getCard(Long id) throws Exception {
        CardDTO card = new CardDTO();

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 해당 id의 카드가 존재하지 않는다면 임의로 값을 넣어 출력
        if(cardMapper.isExistsCard(id)==null) {
            card.setTitle("");
            return card;
        }

        card = cardMapper.getCard(id);

        return card;
    }

    // 카드 정보 업데이트 메소드
    @Override
    public void updateCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);
        
        // 카드가 존재하지 않는 경우 예외 처리
        if(cardMapper.isExistsCard(card.getId())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.updateCard(card);
    }

    // 카드의 위치 변경 메소드
    @Override
    public void moveCard(CardDTO card, Long other_position) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 카드가 존재하지 않는 경우 예외 처리
        if(cardMapper.isExistsCard(card.getId())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);



        cardMapper.moveCard(card, other_position);
    }

    // 카드의 성공여부 변경 메소드
    @Override
    public void successCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 카드가 존재하지 않는 경우 예외 처리
        if(cardMapper.isExistsCard(card.getId())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.successCard(card);
    }

    // 카드 삭제 메소드
    @Override
    public void deleteCard(CardDTO card) throws Exception {

        UserDTO user = userService.getLoginUser();
        //유저 정보가 없는 경우 예외 처리
        if (user==null)
            throw new BaseException(ErrorMessage.INVALID_USER_EXCEPTION);

        // 카드가 존재하지 않는 경우 예외 처리
        if(cardMapper.isExistsCard(card.getId())==null)
            throw new BaseException(ErrorMessage.CONTENT_NOT_EXISTS);

        cardMapper.deleteCard(card);
    }
}
