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

import spring.model.team.TeamDAO;
import spring.model.team.TeamDTO;
import spring.utility.blog.Utility;

@Controller
public class TeamController {
	
	@Autowired
	private TeamDAO dao;
	
	@RequestMapping("/admin/team/delete")
	public String delete(String col,String word,String nowPage,
			int no,HttpServletRequest request,Model model,String oldfile){
		
		String basePath = request.getRealPath("/team/storage"); 
		boolean flag=dao.delete(no);
		if(flag){
			model.addAttribute("flag", flag);
			return "/team/delete";
		}else{
			return "error";
		}

	}
	
	@RequestMapping(value="/admin/team/update",method=RequestMethod.GET)
	public String update(int no,Model model){
		
		model.addAttribute("dto",dao.read(no));
		return "/team/update";
	}
	
	@RequestMapping(value="/admin/team/update",method=RequestMethod.POST)
	public String update(TeamDTO dto,HttpServletRequest request,
			String oldfile,String col, String word,String nowPage
			,Model model){
		
		String basePath = request.getRealPath("/team/storage"); 
		int filesize =(int)dto.getFileMF().getSize();
		String filename = "";
		if(filesize > 0){
			if(oldfile != null && !oldfile.equals("member.jpg"))                              
				Utility.deleteFile(basePath, oldfile);
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);
		
		boolean flag = dao.update(dto);
		if(flag){
			model.addAttribute("nowPage",nowPage);
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			return "redirect:./list";
		}else{
			return "error";
		}
		
		
	}
	
	@RequestMapping(value="/admin/team/read",method=RequestMethod.GET)
	public String read(int no,Model model){
		
		model.addAttribute("dto",dao.read(no));
		
		return "/team/read";
	}
	
	@RequestMapping(value="/admin/team/read",method=RequestMethod.POST)
	public String read(){
		
		return "";
	}
	
	@RequestMapping(value="/admin/team/create",method=RequestMethod.GET)
	public String create(){
		
		return "/team/create";
	}
	@RequestMapping(value="/team/create",method=RequestMethod.POST)
	public String create(TeamDTO dto,HttpServletRequest request){
		
		String basePath = request.getRealPath("/team/storage");
		int filesize = (int)dto.getFileMF().getSize();
		String filename ="";
		if(filesize >0){
			filename = Utility.saveFile(dto.getFileMF(),basePath);
		}
		
		dto.setFilename(filename);
		
		if(dao.create(dto)){
			return "redirect:./list";
		}else{
			
			return "Error";
		}
		
	}
	
	
	@RequestMapping("/admin/team/list")
	public String list(HttpServletRequest request){
		
		//-검색관련
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if(col.equals("total")){
			word="";
		}
		
		int nowPage = 1; //�쁽�옱�럹�씠吏� ,蹂�寃쎄��뒫
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 5;// �븳�럹�씠吏��떦 蹂댁뿬以� �젅肄붾뱶 媛��닔
		
		int total = dao.total(col,word);
		int sno = ((nowPage-1)*recordPerPage) +1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno",sno);
		map.put("eno",eno);
		
		List<TeamDTO> list = dao.list(map);
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
		
		
		return "/team/list";
	}

}
