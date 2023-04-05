package org.team404.gameOjirap.community.cGroup.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.team404.gameOjirap.common.Paging;
import org.team404.gameOjirap.community.cGroup.model.service.CGroupService;
import org.team404.gameOjirap.community.cGroup.model.vo.CGroup;
import org.team404.gameOjirap.community.cGroup.model.vo.CMember;
import org.team404.gameOjirap.community.cGroup.model.vo.CommunityReq;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
public class CGroupController {

    private static final Logger logger = LoggerFactory.getLogger(CGroupController.class);

    @Autowired
    private CGroupService cGroupService;


   /* @RequestMapping("commuMainList.do")
public String commuMainList() throws UnsupportedEncodingException {


        ArrayList<CGroup> list = cGroupService.selectMain10();
        logger.info("commuMainList.do : " + list.size()); // 10 출력 확인

        // 전송용 json 객체 준비
        JSONObject sendJson = new JSONObject();
        // 리스트 저장할 json 배열 객체 준비
        JSONArray jarr = new JSONArray();

        // list 를 jarr 에 옮기기 (복사)
        for (CGroup cGroup : list) {
            // notice 의 각 필드값 저장할 json 객체 생성함
            JSONObject job = new JSONObject();

            job.put("communityid", cGroup.getCommunityid());
            // 한글에 대해서는 인코딩해서 json에 담도록 함
            // 한글깨짐 방지
            job.put("communityname", URLEncoder.encode(cGroup.getCommunityname(), "utf-8"));
            // 날짜는 반드시 toString() 으로 문자열로 바꿔서
            // json 에 담아야 함
            job.put("user_id", cGroup.getUser_id());

            jarr.add(job); // job 를 jarr 에 추가함
        }

        // 전송용 객체에 jarr 을 담음
        sendJson.put("list", jarr);

        // json을 json string 타입으로 바꿔서 전송되게 함
        return sendJson.toJSONString(); // 뷰리졸버로 리턴함
        // servlet-context.xml 에 json string 내보내는
        // JsonView 라는 뷰리졸버 추가 등록해야 함


    }*/

    //커뮤니티 메인 화면으로 이동하는 method
    @RequestMapping(value = "commuMain.do", method={ RequestMethod.GET, RequestMethod.POST })
    public ModelAndView moveCommuMain(@RequestParam(name = "page", required = false) String page, ModelAndView mv) {

        int currentPage = 1;
        if(page != null) {
            currentPage = Integer.parseInt(page);
        }


// 한 페이지에 게시글 10개씩 출력되게 하는 경우 :
        // 페이징 계산 처리 - 별도의 클래스로 작성해서 이용해도 됨
        int limit = 10; // 한 페이지에 출력할 목록 갯수
        // 총 페이지 수 계산을 위해 게시글 총 갯수 조회해 옴
        int listCount = cGroupService.selectListCount();
        Paging paging = new Paging(listCount, currentPage, limit);
        paging.calculator();
        System.out.println(paging);

        ArrayList<CGroup> list = cGroupService.selectList(paging);


        if (list != null && list.size() > 0) {
            mv.addObject("list", list);
            mv.addObject("paging", paging);

            mv.setViewName("community/commuMain");
        } else {
            mv.addObject("message", currentPage + " 커뮤니티 조회 실패");
            mv.setViewName("common/error");
        }
        return mv;
    }

    //커뮤니티 메인에서 커뮤니티 생성 form으로 넘어가는 method
    @RequestMapping(value = "commuCreate.do", method={ RequestMethod.GET, RequestMethod.POST })
    public String creatCommuMethod(HttpSession session, Model model, @RequestParam(name = "communityname") String communityname) {

        //커뮤 메인에서 입력한 생성할 이름 생성 form에 기본적으로 적용 되도록
        model.addAttribute("communityname", communityname);
        return "community/commuCreate";
    }

    //커뮤니티 생성 form에서 submit을 눌렀을때
    @RequestMapping(value = "CommuCreateSubmit.do", method=RequestMethod.POST)
    public String creatCommuMethod(CGroup cGroup, Model model, HttpServletRequest request) {
        System.out.println("---------------------------user id check : "+cGroup.getUser_id());
        //이미지 첨부 미구현
        if (cGroupService.selectCGroup(cGroup.getCommunityname()) < 1 && cGroupService.insertCGroup(cGroup) > 0) {

            //커뮤 생성자 멤버 추가
            CMember cMember = new CMember(cGroup.getUser_id(), cGroup.getCommunityid(), "Y");
            System.out.println(cGroup);
            System.out.println(cMember);

            int check = cGroupService.insertCMember(cMember, cGroup);
            // 게시글 등록 성공시 목록 보기 페이지로 이동
            if(check == -1){
                model.addAttribute("message", "이름이 중복되는 커뮤니티가 이미 존재합니다.");
                return "common/error";
            }else{
                return "redirect:commuMain.do";
            }
        } else {
            model.addAttribute("message", "새 커뮤니티 생성 실패");
            return "common/error";
        }
    }

    //커뮤니티 detailView
    @RequestMapping("viewgroup.do")
    public ModelAndView commuDetailMethod(ModelAndView mv, @RequestParam("communityid") int communityid
                                        , @RequestParam(name = "page", required = false) String page, HttpSession session) {
        int currentPage = 1;
        if (page != null) {
            currentPage = Integer.parseInt(page);
        }
        CGroup cGroup = cGroupService.selectSingleCGroup(communityid);

        if (cGroup != null) {

            mv.addObject("communityid", communityid);
            mv.addObject("group", cGroupService.selectSingleCGroup(communityid));
            mv.addObject("currentPage", currentPage);

            mv.setViewName("community/viewGroup");
        } else {
            mv.addObject("message", communityid + "번 커뮤니티 조회 실패!");
            mv.setViewName("common/error");
        }
        return mv;
    }

    // 요청 페이지로 이동
    @RequestMapping("movejoinpage.do")
    public String moveRequestPage(@RequestParam("communityid") int communityid, Model model){
        CGroup cGroup = cGroupService.selectSingleCGroup(communityid);
        if(cGroup != null){
            model.addAttribute("communityname", cGroup.getCommunityname());
            model.addAttribute("communityid", communityid);
            return "community/joinRequest";}
        else {
            model.addAttribute("message", "신청 양식을 불러오지 못했습니다.");
            return "common/error";
        }
    }

    // 요청 정보 저장
    @RequestMapping(value="req.do", method=RequestMethod.POST)
    public String insertRequest(CommunityReq req, Model model){
        
        if(cGroupService.insertRequest(req) > 0){
            return "redirect:viewgroup.do?communityid=" + req.getCommunityid();
        } else {
            return "common/error";
        }
    }

}
