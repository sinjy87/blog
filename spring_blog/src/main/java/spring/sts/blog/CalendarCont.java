package spring.sts.blog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.model.calendar.CalendarDAO;
import spring.model.calendar.CalendarDTO;
import spring.utility.blog.Utility;

@Controller
public class CalendarCont {
	@Autowired
	CalendarDAO dao = null;

	public CalendarCont() {
		// System.out.println("--> CalendarCont created.");
	}

	/**
	   * ��� ��
	   * @return
	   */
	  @RequestMapping(value="/admin/cal/create", method=RequestMethod.GET)
	  public ModelAndView create(){
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/calendar/create"); // /calendar/createForm.jsp
	    return mav;
	  }
	  
	  /**
	   * ��� ó��
	   * @param dto �ڵ� ������ �� ��ü
	   * @return
	   */
	  @RequestMapping(value="/admin/cal/create", method=RequestMethod.POST)
	  public ModelAndView create(CalendarDTO dto){
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("calendar/msgView"); // /calendar/msgView.jsp
	    
	    if ((Integer)(dao.create(dto)) >= 1){
	      mav.addObject("msg1", "������ ��� �߽��ϴ�.");
	      mav.addObject("link1", "<input type='button' value='��� ���' onclick=\"location.href='./create.do'\">");
	      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do'\">");
	    }else{
	      mav.addObject("msg1", "���� ��Ͽ� ���� �߽��ϴ�.");
	      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='history.back()'>");
	      mav.addObject("link2", "<input type='button' value='���' onclick=\"location.href='./list.do'\">");
	    }
	    
	    return mav;
	  }
	  
	  @RequestMapping(value="/admin/cal/list", method=RequestMethod.GET)
	  public String list(Model model){
	    
	    List list = (List)dao.list();
	    System.out.println("size:"+list.size());
	    model.addAttribute("list", list);
	    
	    return "/calendar/list";
	  }
	 
	  /**
	   * ���� ��
	   * @return
	   */
	  @RequestMapping(value = "/admin/cal/update"
	                             , method = RequestMethod.GET)
	  public ModelAndView updateForm(int calendarno) throws Exception{
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/calendar/update"); // updateForm.jsp
	    
	    mav.addObject("dto", dao.read(calendarno));
	    
	    return mav;
	  }
	  
	  /**
	   * ���� ó��
	   * @param dto
	   * @return
	   */
	  @RequestMapping(value = "/admin/cal/update",
	                             method = RequestMethod.POST)
	  public ModelAndView updateProc(CalendarDTO dto) throws Exception{
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("calendar/msgView"); // msgView.jsp
	    
	    if ((Integer)(dao.update(dto)) == 1){
	      mav.addObject("msg1", "������ �����߽��ϴ�.");
	      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list'\">");
	    }else{
	      mav.addObject("msg1", "���� ������ ���еǾ����ϴ�.");
	      mav.addObject("msg2", "��õ����ּ���.");
	      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='history.back();'>");
	    }
	   
	    mav.addObject("root", Utility.getRoot());
	    
	    return mav;
	  }
	  
	  @RequestMapping(value="/admin/cal/delete", 
	                             method=RequestMethod.POST)
	  public ModelAndView delete(int calendarno){
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("calendar/msgView");
	    
	    if ((Integer)(dao.delete(calendarno)) >= 1){
	      mav.addObject("msg1", "������ �����߽��ϴ�.");
	      mav.addObject("link1", "<input type='button' value='���' onclick=\"location.href='./list.do'\">");
	    }else{
	      mav.addObject("msg1", "���� ������ ���еǾ����ϴ�.");
	      mav.addObject("msg2", "��õ����ּ���.");
	      mav.addObject("link1", "<input type='button' value='�ٽ� �õ�' onclick='history.back();'>");
	    }
	    
	    return mav;
	  }
	  
	  /**
	   * home �޷� ���
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value = "/cal/calendar_home", method = RequestMethod.GET)
	  public ModelAndView calendar_home(HttpServletRequest request) throws Exception{
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("calendar/calendar_home"); // calendar_home.jsp
	    
	    Calendar cal = Calendar.getInstance();
	    int year = 0;
	    int month = 0;
	 
	    if (request.getParameter("y") == null){
	      year = cal.get(Calendar.YEAR); // �о�� ���� ���� ��� ���� �⵵ ����
	    }else{
	      year = Integer.parseInt(request.getParameter("y"));
	    }
	 
	    if (request.getParameter("m") == null){
	      month = cal.get(Calendar.MONTH); // �о�� ���� ���� ��� ���� �⵵ ����
	    }else{
	      month = (Integer.parseInt(request.getParameter("m")) - 1);
	    }
	 
	    // - Calendar MONTH�� 0-11������
	    cal.set(year, month, 1); // ��¥�� ���� ���� ù ��¥�� ����
	    int bgnWeek = cal.get(Calendar.DAY_OF_WEEK); // 1������ ���� ��  
	 
	    System.out.println("Calendar.DAY_OF_WEEK: " + bgnWeek); // 1(��) ~ 7(��)
	 
	    // ����/������ ���
	    // - MONTH ���� ǥ����� ����ϱ� ������ +1�� �� ���¿��� �����
	    int prevYear = year;              // ���� ����
	    int prevMonth = (month + 1) - 1;  // ������
	    int nextYear = year;              // ���� �⵵ 
	    int nextMonth = (month  + 1) + 1; // ������
	 
	    // 1���� ��� ������ 12���� ����
	    if (prevMonth < 1) {
	      prevYear--;      // �⵵ ����
	      prevMonth = 12;  // ���� ���� 12����
	    }
	 
	    // 12���� ��� ������ 1���� ����
	    if (nextMonth > 12) {
	      nextYear++;      // ���� �⵵
	      nextMonth = 1;   // ù���� 1���� ����
	    }
	    
	    mav.addObject("year", year);
	    mav.addObject("month", month);
	    mav.addObject("prevYear", prevYear);
	    mav.addObject("prevMonth", prevMonth);
	    mav.addObject("nextYear", nextYear);
	    mav.addObject("nextMonth", nextMonth);
	 
	    StringBuffer sb = new StringBuffer();
	    sb.append("<tr>");
	    // ���ۿ��ϱ��� �̵�, 1(��) ~ 7(��)
	    for (int i=1; i < bgnWeek; i++){
	      sb.append("<td class='calendar_td'>&nbsp;</td>");
	    }
	 
	    // ù��~������������ ó��
	    // ��¥�� �Ϸ羿 �̵��Ͽ� ���� �ٲ𶧱��� �׸���
	    String str="";
	    ArrayList list = null;
	 
	    // ���� ���� ��� ��� ��ȯ
	    while (cal.get(Calendar.MONTH) == month) {
	      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
	        sb.append("<td class='calendar_td' valign='top'><span style='color:blue;'>" + cal.get(Calendar.DATE) + "</span>"); // ������ΰ��
	      }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
	        sb.append("<td class='calendar_td' valign='top'><span style='color:red;'>" + cal.get(Calendar.DATE) + "</span>");  // �Ͽ����� ���
	      }else{
	        sb.append("<td class='calendar_td' valign='top'>" + cal.get(Calendar.DATE)); // �� ~ �ݿ���
	      }
	  
	      // str = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE);
	      str = Utility.getDate6(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	      list = (ArrayList)dao.labelList(str); // ��¥�� �ش��ϴ� ���̺� �÷� ����
	   
	      if (list != null){ // ��¥�� ���� ����
	        for(int i=0; i<list.size(); i++){
	          CalendarDTO dto = (CalendarDTO)list.get(i);
	          sb.append("<img src='"+Utility.getRoot()+"/images/bu5.gif'>");
	          sb.append("<a href='./cal/update?calendarno="+dto.getCalendarno()+"'>"+dto.getLabel()+"</a><br>");
	        }
	    
	      }
	      sb.append("</td>");
	  
	      // �Ѵ��� ������ ���� �ƴϸ鼭 ������� ��� �����ٷ� ����
	      // System.out.println(cal.getActualMaximum ( Calendar.DAY_OF_MONTH ));
	      if ((cal.getActualMaximum ( Calendar.DAY_OF_MONTH ) != cal.get(Calendar.DATE))) {
	        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){ // ������� ���
	          sb.append("</tr><tr>");
	        }
	      }
	 
	      // ��¥ ������Ű��
	      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
	      // �������� ����ϱ��� ��ĭ���� ó��
	  
	    }
	    
	    System.out.println("cal.get(Calendar.MONTH): " + cal.get(Calendar.MONTH));
	    System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
	 
	    if (cal.get(Calendar.DATE) == 1 && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
	      // �Ѵ��� ������ ��¥ ������̸� �ƹ��ϵ� ����
	    }else{
	      // ������ ��¥�� ����ϰ� �������� �� ���ڷ� ä��
	      for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++){
	        sb.append("<td class='calendar_td'>&nbsp;</td>");
	      }
	    }
	 
	    sb.append("</tr>");    
	    
	    mav.addObject("calendar", sb);
	    
	    return mav;
	  }
	  
	  /**
	   * �޷� ���
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value = "/admin/cal/calendar", method = RequestMethod.GET)
	  public ModelAndView calendar(HttpServletRequest request) throws Exception{
	    ModelAndView mav = new ModelAndView();
	    mav.setViewName("/calendar/calendar"); // calendar.jsp
	    
	    Calendar cal = Calendar.getInstance();
	    int year = 0;
	    int month = 0;
	 
	    if (request.getParameter("y") == null){
	      year = cal.get(Calendar.YEAR); // �о�� ���� ���� ��� ���� �⵵ ����
	    }else{
	      year = Integer.parseInt(request.getParameter("y"));
	    }
	 
	    if (request.getParameter("m") == null){
	      month = cal.get(Calendar.MONTH); // �о�� ���� ���� ��� ���� �⵵ ����
	    }else{
	      month = (Integer.parseInt(request.getParameter("m")) - 1);
	    }
	 
	    // - Calendar MONTH�� 0-11������
	    cal.set(year, month, 1); // ��¥�� ���� ���� ù ��¥�� ����
	    int bgnWeek = cal.get(Calendar.DAY_OF_WEEK); // 1������ ���� ��  
	 
	    System.out.println("Calendar.DAY_OF_WEEK: " + bgnWeek); // 1(��) ~ 7(��)
	 
	    // ����/������ ���
	    // - MONTH ���� ǥ����� ����ϱ� ������ +1�� �� ���¿��� �����
	    int prevYear = year;              // ���� ����
	    int prevMonth = (month + 1) - 1;  // ������
	    int nextYear = year;              // ���� �⵵ 
	    int nextMonth = (month  + 1) + 1; // ������
	 
	    // 1���� ��� ������ 12���� ����
	    if (prevMonth < 1) {
	      prevYear--;      // �⵵ ����
	      prevMonth = 12;  // ���� ���� 12����
	    }
	 
	    // 12���� ��� ������ 1���� ����
	    if (nextMonth > 12) {
	      nextYear++;      // ���� �⵵
	      nextMonth = 1;   // ù���� 1���� ����
	    }
	    
	    mav.addObject("year", year);
	    mav.addObject("month", month);
	    mav.addObject("prevYear", prevYear);
	    mav.addObject("prevMonth", prevMonth);
	    mav.addObject("nextYear", nextYear);
	    mav.addObject("nextMonth", nextMonth);
	 
	    StringBuffer sb = new StringBuffer();
	    sb.append("<tr>");
	    // ���ۿ��ϱ��� �̵�, 1(��) ~ 7(��)
	    for (int i=1; i < bgnWeek; i++){
	      sb.append("<td class='calendar_td'>&nbsp;</td>");
	    }
	 
	    // ù��~������������ ó��
	    // ��¥�� �Ϸ羿 �̵��Ͽ� ���� �ٲ𶧱��� �׸���
	    String str="";
	    ArrayList list = null;
	 
	    // ���� ���� ��� ��� ��ȯ
	    while (cal.get(Calendar.MONTH) == month) {
	      if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
	        sb.append("<td class='calendar_td' valign='top'><span style='color:blue;'>" + cal.get(Calendar.DATE) + "</span>"); // ������ΰ��
	      }else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
	        sb.append("<td class='calendar_td' valign='top'><span style='color:red;'>" + cal.get(Calendar.DATE) + "</span>");  // �Ͽ����� ���
	      }else{
	        sb.append("<td class='calendar_td' valign='top'>" + cal.get(Calendar.DATE)); // �� ~ �ݿ���
	      }
	  
	      // str = cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DATE);
	      str = Utility.getDate6(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
	      list = (ArrayList)dao.labelList(str); // ��¥�� �ش��ϴ� ���̺� �÷� ����
	   
	      if (list != null){ // ��¥�� ���� ����
	        for(int i=0; i<list.size(); i++){
	          CalendarDTO dto = (CalendarDTO)list.get(i);
	          sb.append("<img src='"+Utility.getRoot()+"/images/bu5.gif'>");
	          sb.append("<a href='./update?calendarno="+dto.getCalendarno()+"'>"+dto.getLabel()+"</a><br>");
	        }
	    
	      }
	      sb.append("</td>");
	  
	      // �Ѵ��� ������ ���� �ƴϸ鼭 ������� ��� �����ٷ� ����
	      // System.out.println(cal.getActualMaximum ( Calendar.DAY_OF_MONTH ));
	      if ((cal.getActualMaximum ( Calendar.DAY_OF_MONTH ) != cal.get(Calendar.DATE))) {
	        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){ // ������� ���
	          sb.append("</tr><tr>");
	        }
	      }
	 
	      // ��¥ ������Ű��
	      cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)+1);
	      // �������� ����ϱ��� ��ĭ���� ó��
	  
	    }
	    
	    System.out.println("cal.get(Calendar.MONTH): " + cal.get(Calendar.MONTH));
	    System.out.println("cal.get(Calendar.DATE): " + cal.get(Calendar.DATE));
	 
	    if (cal.get(Calendar.DATE) == 1 && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
	      // �Ѵ��� ������ ��¥ ������̸� �ƹ��ϵ� ����
	    }else{
	      // ������ ��¥�� ����ϰ� �������� �� ���ڷ� ä��
	      for (int i=cal.get(Calendar.DAY_OF_WEEK); i<=7; i++){
	        sb.append("<td class='calendar_td'>&nbsp;</td>");
	      }
	    }
	 
	    sb.append("</tr>");    
	    
	    mav.addObject("calendar", sb);
	    
	    return mav;
	  }
}