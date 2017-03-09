package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.IReplyDAO;
import spring.model.memo.MemoDAO;
import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.model.memo.MemoreplyDAO;
import spring.model.memo.MemoreplyDTO;
import spring.utility.blog.Utility;

@Controller
public class MemoController {
	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MemoreplyDAO rdao;
	
	@Autowired
	private MemoService service; //추가
	
	@RequestMapping("/memo/rdelete")
	public String rdelete(int rnum,int memono, int nowPage,int nPage, String col, String word,Model model){ 
	 
	int total = rdao.total(memono);//댓글전체레코드값을 가져와서
	int totalPage = (int)(Math.ceil((double)total/3)); // 전체 페이지  
	if(rdao.delete(rnum)){
	if(nPage!=1&&nPage==totalPage&&total%3==1){//마지막페이지의 마지막레코드이면(3은 한페이지당보여줄 레코드 갯수)
	nPage=nPage-1;  //현재의 페이지값에서 1을 빼자 
	}
	model.addAttribute("memono", memono);
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	 
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	 
	@RequestMapping("/memo/rupdate")
	public String rupdate(MemoreplyDTO dto,int nowPage,int nPage, String col, String word,Model model){
	if(rdao.update(dto)){
	model.addAttribute("memono", dto.getMemono());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	@RequestMapping("/memo/rcreate")
	public String rcreate(MemoreplyDTO dto,int nowPage,String col, String word,Model model){
	 
	if(rdao.create(dto)){
	model.addAttribute("memono", dto.getMemono());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	
	@RequestMapping(value="/memo/delete",method=RequestMethod.POST)
	public String delete(String col,String word,String nowPage,Model model,int memono){
		String url = "";
		
	
			try{
			service.delete(memono);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			model.addAttribute("nowPage",nowPage);	
			
			url =  "redirect:./list";
			
			}catch(Exception e){
				  e.printStackTrace();
	                url = "error";
			}
			return url;

	}
	
	@RequestMapping(value="/memo/delete",method=RequestMethod.GET)
	public String delete(int memono){

			return "/memo/delete";

	}
	
	@RequestMapping(value="/memo/update",method=RequestMethod.POST)
	public String update(MemoDTO dto,String col,String word,String nowPage,Model model){
		
		boolean flag = dao.update(dto);
		if(flag){
			model.addAttribute("col",col);
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("word",word);
			
			return "redirect:./list";
		}else{
			return "error";
		}

	}
	
	@RequestMapping(value="/memo/update",method=RequestMethod.GET)
	public String update(int memono,Model model){
		
		model.addAttribute("dto",dao.read(memono));
		return "/memo/update";
	}
	

	@RequestMapping("/memo/read")
	public String read(int memono,Model model
			,int nowPage, String col, String word
			, HttpServletRequest request){
		
		dao.upViewcnt(memono);
		MemoDTO dto = dao.read(memono);
		String content = dto.getContent().replaceAll("\r\n", "<br>");
		dto.setContent(content);
		model.addAttribute("dto",dto);
		
		/* 댓글 관련  시작 */
		String url = "read";
		int nPage= 1; //시작 페이지 번호는 1부터 
		 String noname="memono";
		if (request.getParameter("nPage") != null) { 
		nPage= Integer.parseInt(request.getParameter("nPage"));  
		}
		int recordPerPage = 3; // 한페이지당 출력할 레코드 갯수
		 
		int sno = ((nPage-1) * recordPerPage) + 1; // 
		int eno = nPage * recordPerPage;
		 
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("memono", memono);
		 
		List<MemoreplyDTO> list = rdao.list(map);
		 
		int total = rdao.total(memono);
		 
		String paging = Utility.paging(noname, total, nPage, recordPerPage, url, memono, nowPage, col, word);
		 
		model.addAttribute("rlist",list);
		model.addAttribute("paging",paging);
		model.addAttribute("nPage",nPage);
		 
		/* 댓글 관련 끝 */ 
		
		return "/memo/read";
	}
	
	@RequestMapping(value="/memo/create",method=RequestMethod.POST)
	public String create(MemoDTO dto){
		if(dao.create(dto)){
			return "redirect:./list";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value="/memo/create",method=RequestMethod.GET)
	public String create(){
		
		return "/memo/create";
	}
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request){
				//검색관련
				String col = Utility.checkNull(request.getParameter("col"));
				String word = Utility.checkNull(request.getParameter("word"));

				if (col.equals("total")) {
					word = "";
				}
				
				//페이지관련----------------
				int nowPage = 1;//한페이지
				if(request.getParameter("nowPage")!=null){
					nowPage = Integer.parseInt(request.getParameter("nowPage"));
				}
				int recordPerPage = 5;//한페이지당 보여지는 리스트
				
				//DB에서 읽어오는 순서
				int sno = ((nowPage - 1)* recordPerPage) + 1;
				int eno = nowPage * recordPerPage;
				
				
				Map map = new HashMap();
				map.put("col", col);
				map.put("word", word);
				map.put("sno", sno);
				map.put("eno", eno);

				
				List<MemoDTO> list = dao.list(map);
				int total = dao.total(col,word);
				
				String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
				
				request.setAttribute("list", list);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("col", col);
				request.setAttribute("word", word);
				request.setAttribute("paging", paging);
				
				// list.jsp에서 댓글 갯수 가져올 <util:rcount(num,rdao)>에서 사용할 
				   // rdao(ReplyDAO)의 값을 request 객체에 담는다.
				IReplyDAO irdao = rdao;
				    request.setAttribute("irdao", irdao); 
				
		return "/memo/list";
	}
	
}
