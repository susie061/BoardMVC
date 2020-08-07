package com.springbook.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/board") // 주소값
public class BoardController {

	@Autowired
	private BoardService service;

//Autowired 주소값이 필요하구나-- 자동으로 투입 시킨다
	@RequestMapping(value = "/list", method = RequestMethod.GET) // 2단계 과정
	public String boardList(Model model) {

		return "board/list";
	}

	@ResponseBody
	@RequestMapping(value = "/getListData", method = RequestMethod.GET)
	public Map<String, Object> getListData(@RequestParam int page, @RequestParam String searchText) {
		
		System.out.println("page : " + page);
		System.out.println("searchText : " + searchText);
		
		Map<String, Object> map = new HashMap();
		map.put("result", service.selBoardList(page ,searchText));
		
		return map;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String boardWrite() {

		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String boardWrite(BoardVO param) {
		System.out.println("title: " + param.getTitle());
		System.out.println("ctnt: " + param.getCtnt());
		int result = service.insBoard(param);
//		service.insBoard(param);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String boardDetail(BoardVO param, Model model) { // 빼도 상관 없다.
		model.addAttribute("data", service.selBoard(param));
		System.out.println("i_board: " + param.getI_board());
		return "board/detail";
	}

//	@RequestMapping(value = "/delete", method = RequestMethod.GET)
//	public String boardDelete(BoardVO param) {
//		System.out.println("i_board" + param.getI_board());
//		service.delBoard(param);
//		return "redirect:/board/list";
//	}
//	 

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam int i_board) {
		System.out.println("i_board: " + i_board);
		int result = service.delBoard(i_board);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/upd", method = RequestMethod.GET)
	public String boardUpd(BoardVO param, Model model) {
		model.addAttribute("data", service.selBoard(param));
		return "board/write";
	}

	@RequestMapping(value = "/upd", method = RequestMethod.POST)
	public String boardUpd(BoardVO param) {
		int result = service.updBoard(param);
		return "redirect:/board/detail?i_board=" + param.getI_board();
	}

}
