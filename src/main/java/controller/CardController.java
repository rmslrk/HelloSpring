package controller;

import domain.CardDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import response.BaseResponse;
import service.CardService;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @ApiOperation(value = "카드 생성", notes = "리스트 내부에 카드를 추가합니다.")
    @RequestMapping(value = "/createCard", method = RequestMethod.POST)
    public ResponseEntity createCard(@Validated(CardDTO.class) @RequestBody CardDTO card) throws Exception{
        cardService.createCard(card);
        return new ResponseEntity( new BaseResponse("카드가 정상적으로 추가되었습니다.", HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation( value = "카드 읽기", notes = "리스트 내부의 카드를 읽어옵니다.")
    @RequestMapping(value = "/getCard", method = RequestMethod.GET)
    public ResponseEntity<CardDTO> getMemo(@RequestParam Long cid) throws Exception{
        return new ResponseEntity<CardDTO>(cardService.getCard(cid), HttpStatus.OK);
    }

    @ApiOperation( value = "카드 수정", notes = "카드를 수정합니다.")
    @RequestMapping(value = "/updateCard", method = RequestMethod.PATCH)
    public ResponseEntity updateCard(@Validated(CardDTO.class) @RequestBody CardDTO card) throws Exception{
        cardService.updateCard(card);
        return new ResponseEntity( new BaseResponse("카드가 정상적으로 수정됐습니다.", HttpStatus.OK), HttpStatus.OK);
    }

    @ApiOperation( value = "카드 삭제", notes = "카드를 삭제합니다.")
    @RequestMapping(value = "/deleteCard", method = RequestMethod.DELETE)
    public ResponseEntity deleteMemo(@RequestBody CardDTO card) throws Exception{
        cardService.deleteCard(card);
        return new ResponseEntity( new BaseResponse("카드가 정상적으로 삭제됐습니다.", HttpStatus.OK), HttpStatus.OK);
    }
}
