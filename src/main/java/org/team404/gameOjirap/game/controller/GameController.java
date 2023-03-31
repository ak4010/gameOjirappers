package org.team404.gameOjirap.game.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.team404.gameOjirap.game.model.service.GameService;
import org.team404.gameOjirap.game.model.vo.Game;


@Controller("gameController")
public class GameController {
	private static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;

	// 이동 처리용 메소드
	@RequestMapping("moveGameDetail.do")
	public String moveGameInfoView(
			//Model model, @RequestParam("appid") String appid
			) {
		//model.addAttribute("appid", appid);
		return "game/gameDetailView";


	}
	
	// 게임 도전과제 보기 사이트
	@RequestMapping("goChallenge.do")
	public String moveGameAchievement(
			//Model model, @RequestParam("appid") String appid
			) {
		//model.addAttribute("appid", appid);
		return "game/achievementView";
	}
	
	// 기능용 메소드 	-----------------------------------
	
	// 게임 정보 삭제
	@RequestMapping("gdeleteData.do")
	public String deleteGameInfo(Model model, @RequestParam("appid") String appid) {
		
		if(gameService.deleteGameInfo(appid) > 0) {
			
			return "common/main";
		} else {
			model.addAttribute("message", appid + "번 게임 정보 삭제 실패!");
			return "common/error";
		}
		
	}

	//게임 top5
	@RequestMapping(value = "gametop5.do", method = RequestMethod.POST)
	@ResponseBody
	public String gameTop5Method() throws UnsupportedEncodingException {
		// ajax로 요청시, 리턴 방법은 여러가지가 있음
		// response 객체 이용시
		// 1. 출력스트림으로 응답하는 방법 (아이디 중복 체크 예)
		// 2. 뷰리졸버로 리턴하는 방법 : response body 에 값을 저장함

		// 조회수 많은 인기 게시글 5개 조회해 옴
		ArrayList<Game> list = gameService.selectgameTop5();
		logger.info("gametop5.do : " + list.size()); // 5개 출력 확인

		// 전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// 리스트 저장할 json 배열 객체 준비
		JSONArray jarr = new JSONArray();

		// list 를 jarr 에 옮기기 (복사)
		for (Game game : list) {
			// notice 의 각 필드값 저장할 json 객체 생성
			JSONObject job = new JSONObject();
			job.put("name", game.getName());
			job.put("headerImg",game.getHeaderimgs());
			
			// 한글에 대해서는 인코딩해서 json에 담도록 함 : 한글깨짐 방지
			job.put("short_description", URLEncoder.encode(game.getShort_description(), "UTF-8"));			
			// 날짜는 반드시 toString() 으로 문자열로 바꿔서 json에 담아야 함
			job.put("achievement", game.getreleasedate().toString());

			jarr.add(job); // job 를 jarr 에 추가함

			jarr.add(job); // job 를 jarr 에 추가함
		}

		// 전송용 객체에 jarr을 담음
		sendJson.put("list", jarr);

		// json을 json string 타입으로 바꿔서 전송되게 함
		return sendJson.toJSONString(); // 뷰리졸버로 리턴함
		// servlet-context.xml 에 json string 내보내는 JsonView 라는 뷰리졸버 추가 등록해야 함

	}

	
}
