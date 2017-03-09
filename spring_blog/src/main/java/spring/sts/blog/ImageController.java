package spring.sts.blog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.Image.ImageDAO;
import spring.model.Image.ImageDTO;
import spring.utility.blog.Utility;

@Controller
public class ImageController {
	@Autowired
	private ImageDAO dao;

	@RequestMapping(value = "/image/delete", method = RequestMethod.POST)
	public String delete(ImageDTO dto, String col, String word, String nowPage,
			HttpServletRequest request, Model model,
			int no, String passwd, String filename) {

		String basePath = request.getRealPath("/image/storage");
		Map map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		boolean flag = dao.passwd(map);
		if (flag) {
			if (dao.delete(no))
				Utility.deleteFile(basePath, filename);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);

			return "redirect:./list";
		} else {

			return "passwdError";
		}

	}

	@RequestMapping(value = "/image/delete", method = RequestMethod.GET)
	public String delete(int no, Model model) {

		model.addAttribute("flag", dao.checkRefno(no));

		return "/image/delete";
	}

	@RequestMapping(value = "/image/reply", method = RequestMethod.POST)
	public String reply(ImageDTO dto, String col, String word, String nowPage, Model model,
			HttpServletRequest request) {

		String basePath = request.getRealPath("/image/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);
		
		Map map = new HashMap();
		map.put("gno", dto.getGno());
		map.put("ano", dto.getAno());
		
		dao.upAno(map);

		if (dao.createReply(dto)) {
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/image/reply", method = RequestMethod.GET)
	public String reply(int no, Model model) {

		model.addAttribute("dto", dao.readReply(no));

		return "/image/reply";
	}

	@RequestMapping(value = "/image/update", method = RequestMethod.POST)
	public String update(int no, ImageDTO dto, Model model, String col, String word, String nowPage,
			HttpServletRequest request, String oldfile, String passwd) {

		String basePath = request.getRealPath("/image/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			Utility.deleteFile(basePath, oldfile);
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}
		dto.setFilename(filename);
		Map map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		if (dao.passwd(map)) {
			boolean flag = dao.update(dto);

			if (flag) {
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				model.addAttribute("nowPage", nowPage);
				return "redirect:./list";
			} else {
				return "error";
			}
		}else{
			return "passwdError";
		}
	}

	@RequestMapping(value = "/image/update", method = RequestMethod.GET)
	public String update(int no, Model model) {

		model.addAttribute("dto", dao.read(no));

		return "/image/update";
	}

	@RequestMapping(value = "/image/create", method = RequestMethod.POST)
	public String create(ImageDTO dto, HttpServletRequest request, String oldfile, String col, String word,
			String nowPage, Model model) {

		String basePath = request.getRealPath("/image/storage");
		int filesize = (int) dto.getFileMF().getSize();
		String filename = "";
		if (filesize > 0) {
			filename = Utility.saveFile(dto.getFileMF(), basePath);
		}

		dto.setFilename(filename);
		boolean flag = dao.create(dto);
		if (flag) {
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			return "redirect:./list";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/image/create", method = RequestMethod.GET)
	public String create() {

		return "/image/create";
	}

	@RequestMapping("/image/read")
	public String read(Model model, int no, String col, String word, 
			String nowPage, HttpServletRequest request) {
		dao.upViewcnt(no);
		
		ImageDTO dto = dao.read(no);

		Map map = dao.sumnail(no);
		BigDecimal[] noArr = {((BigDecimal)map.get("PRE_NO2")), 
		((BigDecimal)map.get("PRE_NO1")),
		((BigDecimal)map.get("NO")),
		((BigDecimal)map.get("NEX_NO1")),
		((BigDecimal)map.get("NEX_NO2"))
		};

		String[] files = {
		((String)map.get("PRE_FILE2")),
		((String)map.get("PRE_FILE1")),
		((String)map.get("FILENAME")),
		((String)map.get("NEX_FILE1")),
		((String)map.get("NEX_FILE2"))

		};
		
		dto.setContent(dto.getContent().replaceAll("\r\n", "<br>"));

		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("dto", dto);
		model.addAttribute("no", no);
		model.addAttribute("files", files);
		model.addAttribute("noArr", noArr);

		return "/image/read";
		}

	@RequestMapping("/image/list")
	public String list(HttpServletRequest request) {

		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if (col.equals("total")) {
			word = "";
		}

		int nowPage = 1;
		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		int recordPerPage = 5;

		int totalRecord = dao.total(col, word);
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		List<ImageDTO> list = dao.list(map);

		String paging = Utility.paging3(totalRecord, nowPage, recordPerPage, col, word);

		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("recordPerPage", recordPerPage);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);

		return "/image/list";
	}

}
