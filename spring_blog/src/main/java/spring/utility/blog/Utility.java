package spring.utility.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import spring.model.IReplyDAO;
import spring.model.bbs.ReplyDAO;

public class Utility {
	private static final String root ="/blog";
	 
	  public static synchronized String getRoot(){
	    return root;
	  }
	  
	     public static int rcount(int no,IReplyDAO rdao){
	          return rdao.rcount(no);
	     }
	  
	 
	  /**
	   * 2010-12-14 Çü½ÄÀÇ ³¯Â¥¸¦ ¸®ÅÏÇÕ´Ï´Ù.
	   * @return 2008-01-30 Çü½ÄÀÇ ¹®ÀÚ¿­ ¸®ÅÏ
	   */
	  public static String getDate6(int year, int month, int day){
	      SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	      
	      new Date();
	      String date = sd.format(new Date(year-1900, month, day));
	 
	      // System.out.println(date);        
	      return date;
	  }
	
	public static String getCodeValue(String code){
		String value = null;
		Hashtable codes = new Hashtable();
		codes.put("A01", "È¸»ç¿ø");
		codes.put("A02", "Àü»ê°ü·ÃÁ÷");
		codes.put("A03", "¿¬±¸Àü¹®Á÷");
		codes.put("A04", "°¢Á¾ÇĞ±³ÇĞ»ı");
		codes.put("A05", "ÀÏ¹İÀÚ¿µ¾÷");
		codes.put("A06", "°ø¹«¿ø");
		codes.put("A07", "ÀÇ·áÀÎ");
		codes.put("A08", "¹ıÁ¶ÀÎ");
		codes.put("A09", "Á¾±³/¾ğ·Ğ/¿¹¼úÀÎ");
		codes.put("A10", "±âÅ¸");
		
		value = (String)codes.get(code);
		
		return value;
	}
	/**
     * ?˜¤?Š˜,?–´? œ,ê·¸ì œ ?‚ ì§? ê°?? ¸?˜¤ê¸?
     * @return List- ?‚ ì§œë“¤ ???¥
     */
    public static List<String> getDay(){
        List<String> list = new ArrayList<String>();
        
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        for(int j = 0; j < 3; j++){
            list.add(sd.format(cal.getTime()));
            cal.add(Calendar.DATE, -1);
        }
        
        return list;
    }
    /**
     * ?“±ë¡ë‚ ì§œì? ?˜¤?Š˜,?–´? œ,ê·¸ì œ?‚ ì§œì? ë¹„êµ
     * @param wdate - ?“±ë¡ë‚ ì§?
     * @return - true:?˜¤?Š˜,?–´? œ,ê·¸ì œì¤? ?“±ë¡ë‚ ì§œì? ê°™ìŒ
     *           false:?˜¤?Š˜,?–´? œ,ê·¸ì œ ?‚ ì§œê? ?“±ë¡ë‚ ì§œì? ?‹¤ ?‹¤ë¦?
     */
    public static boolean compareDay(String wdate){
        boolean flag = false;
        List<String> list = getDay();
        if(wdate.equals(list.get(0)) 
           || wdate.equals(list.get(1))
           || wdate.equals(list.get(2))){
            flag = true;
        }
          
        return flag;
    }

	public static String checkNull(String str){
		if(str==null){
			str="";
		}
		
		return str;
	}
	/** 
	   * SPAN?ƒœê·¸ë?? ?´?š©?•œ ë°•ìŠ¤ ëª¨ë¸?˜ ì§??›, 1 ?˜?´ì§?ë¶??„° ?‹œ?‘ 
	   * ?˜„?¬ ?˜?´ì§?: 11 / 22   [?´? „] 11 12 13 14 15 16 17 18 19 20 [?‹¤?Œ] 
	   *  
	   * @param totalRecord ? „ì²? ? ˆì½”ë“œ?ˆ˜ 
	   * @param nowPage     ?˜„?¬ ?˜?´ì§? 
	   * @param recordPerPage ?˜?´ì§??‹¹ ? ˆì½”ë“œ ?ˆ˜  
	   * @param col ê²??ƒ‰ ì»¬ëŸ¼  
	   * @param word ê²??ƒ‰?–´
	   * @return ?˜?´ì§? ?ƒ?„± ë¬¸ì?—´
	   */ 
	  public static String paging1(int totalRecord, int nowPage, int recordPerPage, String col, String word){ 
	    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜ 
	    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ? „ì²? ?˜?´ì§?  
	    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹ 
	    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹ 
	    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘  
	    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ   
	     
	    StringBuffer str = new StringBuffer(); 
	     
	    str.append("<DIV id='container'>"); 
//	    str.append("?˜„?¬ ?˜?´ì§?: " + nowPage + " / " + totalPage + "  "); 
	 
	    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™ 
	    if (nowGrp >= 2){ 
	      str.append("<ul class='pager'><li><A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?´? „</A></li></ul>"); 
	    } 
	 
	    for(int i=startPage; i<=endPage; i++){ 
	      if (i > totalPage){ 
	        break; 
	      } 
	  
	      if (nowPage == i){ 
	        str.append("<ul class='pagination'><li>"+i+"</li></ul>"); 
	      }else{ 
	        str.append("<ul class='pagination'><li><A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></li></ul>");   
	      } 
	    } 
	     
	    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™ 
	    if (nowGrp < totalGrp){ 
	      str.append("<ul class='pager'><li><A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?‹¤?Œ</A></li></ul>"); 
	    } 
	    str.append("</DIV>"); 
	     
	    return str.toString(); 
	  } 

	  public static String paging3(int totalRecord, int nowPage, int recordPerPage, String col, String word){ 
		    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜ 
		    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ? „ì²? ?˜?´ì§?  
		    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹ 
		    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹ 
		    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘  
		    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ   
		     
		    StringBuffer str = new StringBuffer(); 
		     
		    str.append("<style type='text/css'>"); 
		    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
		    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
		    str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}"); 
		    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
		    str.append("  .span_box_1{"); 
		    str.append("    text-align: center;");    
		    str.append("    font-size: 1em;"); 
		    str.append("    border: 1px;"); 
		    str.append("    border-style: solid;"); 
		    str.append("    border-color: #cccccc;"); 
		    str.append("    padding:1px 6px 1px 6px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/"); 
		    str.append("    margin:1px 2px 1px 2px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/"); 
		    str.append("  }"); 
		    str.append("  .span_box_2{"); 
		    str.append("    text-align: center;");    
		    str.append("    background-color: #339999;"); 
		    str.append("    color: #FFFFFF;"); 
		    str.append("    font-size: 1em;"); 
		    str.append("    border: 1px;"); 
		    str.append("    border-style: solid;"); 
		    str.append("    border-color: #cccccc;"); 
		    str.append("    padding:1px 6px 1px 6px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/"); 
		    str.append("    margin:1px 2px 1px 2px; /*?œ„, ?˜¤ë¥¸ìª½, ?•„?˜, ?™¼ìª?*/"); 
		    str.append("  }"); 
		    str.append("</style>"); 
		    str.append("<DIV id='paging'>"); 
//		    str.append("?˜„?¬ ?˜?´ì§?: " + nowPage + " / " + totalPage + "  "); 
		 
		    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™ 
		    if (nowGrp >= 2){ 
		      str.append("<span class='span_box_1'><A href='./list?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?´? „</A></span>"); 
		    } 
		 
		    for(int i=startPage; i<=endPage; i++){ 
		      if (i > totalPage){ 
		        break; 
		      } 
		  
		      if (nowPage == i){ 
		        str.append("<span class='span_box_2'>"+i+"</span>"); 
		      }else{ 
		        str.append("<span class='span_box_1'><A href='./list?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A></span>");   
		      } 
		    } 
		     
		    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™ 
		    if (nowGrp < totalGrp){ 
		      str.append("<span class='span_box_1'><A href='./list?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?‹¤?Œ</A></span>"); 
		    } 
		    str.append("</DIV>"); 
		     
		    return str.toString(); 
		  } 	 
	
	/** 
	   * ?ˆ«? ?˜•?ƒœ?˜ ?˜?´ì§?, 1 ?˜?´ì§?ë¶??„° ?‹œ?‘ 
	   * ?˜„?¬ ?˜?´ì§?: 11 / 22   [?´? „] 11 12 13 14 15 16 17 18 19 20 [?‹¤?Œ] 
	   *  
	   * @param totalRecord ? „ì²? ? ˆì½”ë“œ?ˆ˜ 
	   * @param nowPage     ?˜„?¬ ?˜?´ì§? 
	   * @param recordPerPage ?˜?´ì§??‹¹ ì¶œë ¥?•  ? ˆì½”ë“œ ?ˆ˜
	   * @param col ê²??ƒ‰ ì»¬ëŸ¼  
	   * @param word ê²??ƒ‰?–´
	   * @return ?˜?´ì§? ?ƒ?„± ë¬¸ì?—´
	   */ 
	  public static String paging(int totalRecord, int nowPage, int recordPerPage, String col, String word){ 
	    int pagePerBlock = 10; // ë¸”ëŸ­?‹¹ ?˜?´ì§? ?ˆ˜ 
	    int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage));// ? „ì²? ?˜?´ì§?  
	    int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ? „ì²? ê·¸ë£¹ 
	    int nowGrp = (int)(Math.ceil((double)nowPage/pagePerBlock));    // ?˜„?¬ ê·¸ë£¹ 
	    int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ?‹œ?‘  
	    int endPage = (nowGrp * pagePerBlock);             // ?Š¹? • ê·¸ë£¹?˜ ?˜?´ì§? ëª©ë¡ ì¢…ë£Œ   
	     
	    StringBuffer str = new StringBuffer(); 
	     
	    str.append("<style type='text/css'>"); 
	    str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
	    str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
	    str.append("  #paging A:hover{text-decoration:underline; background-color: #ffffff; color:black; font-size: 1em;}"); 
	    str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
	    str.append("</style>"); 
	    str.append("<DIV id='paging'>"); 
	    str.append("ÇöÀç ÆäÀÌÁö " + nowPage + " / " + totalPage + "  "); 
	 
	    int _nowPage = (nowGrp-1) * pagePerBlock; // 10ê°? ?´? „ ?˜?´ì§?ë¡? ?´?™ 
	    if (nowGrp >= 2){ 
	      str.append("[<A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?´? „</A>]"); 
	    } 
	 
	    for(int i=startPage; i<=endPage; i++){ 
	      if (i > totalPage){ 
	        break; 
	      } 
	  
	      if (nowPage == i){ // ?˜„?¬ ?˜?´ì§??´ë©? ê°•ì¡° ?š¨ê³? 
	        str.append("<span style='font-size: 1.2em; font-weight: bold;'>"+i+"</span> ");   
	      }else{ 
	        str.append("<A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+i+"'>"+i+"</A> "); 
	      } 
	       
	    } 
	     
	    _nowPage = (nowGrp * pagePerBlock)+1; // 10ê°? ?‹¤?Œ ?˜?´ì§?ë¡? ?´?™ 
	    if (nowGrp < totalGrp){ 
	      str.append("[<A href='./list.jsp?col="+col+"&word="+word+"&nowPage="+_nowPage+"'>?‹¤?Œ</A>]"); 
	    } 
	    str.append("</DIV>"); 
	     
	    return str.toString(); 
	  }
	public static String saveFile(MultipartFile fileMF, String basePath) {
		 // input form's parameter name 
        String fileName = ""; 
        // original file name 
        String originalFileName = fileMF.getOriginalFilename(); 
        // file content type 
        String contentType = fileMF.getContentType(); 
        // file size 
        long fileSize = fileMF.getSize(); 
         
        System.out.println("fileSize: " + fileSize); 
        System.out.println("originalFileName: " + originalFileName); 
         
        InputStream inputStream = null; 
        OutputStream outputStream = null; 
 
        try { 
            if( fileSize > 0 ) { // ÆÄÀÏÀÌ Á¸ÀçÇÑ´Ù¸é 
                // ÀÎÇ² ½ºÆ®¸²À» ¾ò´Â´Ù. 
                inputStream = fileMF.getInputStream(); 
 
                File oldfile = new File(basePath, originalFileName); 
             
                if ( oldfile.exists()){ 
                    for(int k=0; true; k++){ 
                        //ÆÄÀÏ¸í Áßº¹À» ÇÇÇÏ±â À§ÇÑ ÀÏ·Ã ¹øÈ£¸¦ »ı¼ºÇÏ¿© 
                        //ÆÄÀÏ¸íÀ¸·Î Á¶ÇÕ 
                        oldfile = new File(basePath,"("+k+")"+originalFileName); 
                     
                        //Á¶ÇÕµÈ ÆÄÀÏ¸íÀÌ Á¸ÀçÇÏÁö ¾Ê´Â´Ù¸é, ÀÏ·Ã¹øÈ£°¡ 
                        //ºÙÀº ÆÄÀÏ¸í ´Ù½Ã »ı¼º 
                        if(!oldfile.exists()){ //Á¸ÀçÇÏÁö ¾Ê´Â °æ¿ì 
                            fileName = "("+k+")"+originalFileName; 
                            break; 
                        } 
                    } 
                }else{ 
                    fileName = originalFileName; 
                } 
                //make server full path to save 
                String serverFullPath = basePath + "\\" + fileName; 
                 
                System.out.println("fileName: " + fileName); 
                System.out.println("serverFullPath: " + serverFullPath); 
                 
                outputStream = new FileOutputStream( serverFullPath ); 
  
                // ¹öÆÛ¸¦ ¸¸µç´Ù. 
                int readBytes = 0; 
                byte[] buffer = new byte[8192]; 
 
                while((readBytes = inputStream.read(buffer, 0, 8192)) != -1 ) { 
                    outputStream.write( buffer, 0, readBytes ); 
                } 
                outputStream.close(); 
                inputStream.close(); 
                         
            } 
 
        } catch(Exception e) { 
            e.printStackTrace();   
        }finally{ 
             
        } 
         
        return fileName; 
	}
        public static void deleteFile(String upDir,String oldfile){
        	File file = new File(upDir,oldfile);
        	if(file.exists()){
        		file.delete();
        }
    }


/** 
 * ´ñ±Û¿ë ÆäÀÌÂ¡ ¸Ş¼Òµå
 * @param totalRecord ÀüÃ¼ ·¹ÄÚµå¼ö 
 * @param nowPage     ´ñ±ÛÀÇ ÇöÀç ÆäÀÌÁö 
 * @param recordPerPage ´ñ±ÛÀÇ ÆäÀÌÁö´ç ·¹ÄÚµå ¼ö 
 * @param url ÀÌµ¿ÇÒ ÆäÀÌÁö - read
 * @param bbsno bbs ±Û¹øÈ£
 * @param nowPage bbsÀÇ ÇöÀç ÆäÀÌÁö
 * @param col bbsÀÇ °Ë»ö ÄÃ·³
 * @param word bbsÀÇ °Ë»ö¾î
 * @return ÆäÀÌÂ¡ »ı¼º ¹®ÀÚ¿­
 */ 
public static String paging(String noname, int totalRecord, int nPage, int recordPerPage,String url,int bbsno,int nowPage, String col, String word){ 
  int pagePerBlock = 10; // ºí·°´ç ÆäÀÌÁö ¼ö 
  int totalPage = (int)(Math.ceil((double)totalRecord/recordPerPage)); // ÀüÃ¼ ÆäÀÌÁö  
  int totalGrp = (int)(Math.ceil((double)totalPage/pagePerBlock));// ÀüÃ¼ ±×·ì 
  int nowGrp = (int)(Math.ceil((double)nPage/pagePerBlock));    // ÇöÀç ±×·ì 
  int startPage = ((nowGrp - 1) * pagePerBlock) + 1; // Æ¯Á¤ ±×·ìÀÇ ÆäÀÌÁö ¸ñ·Ï ½ÃÀÛ  
  int endPage = (nowGrp * pagePerBlock);             // Æ¯Á¤ ±×·ìÀÇ ÆäÀÌÁö ¸ñ·Ï Á¾·á   
   
  StringBuffer str = new StringBuffer(); 
   
  str.append("<style type='text/css'>"); 
  str.append("  #paging {text-align: center; margin-top: 5px; font-size: 1em;}"); 
  str.append("  #paging A:link {text-decoration:none; color:black; font-size: 1em;}"); 
  str.append("  #paging A:hover{text-decoration:none; background-color: #CCCCCC; color:black; font-size: 1em;}"); 
  str.append("  #paging A:visited {text-decoration:none;color:black; font-size: 1em;}"); 
  str.append("  .span_box_1{"); 
  str.append("    text-align: center;");    
  str.append("    font-size: 1em;"); 
  str.append("    border: 1px;"); 
  str.append("    border-style: solid;"); 
  str.append("    border-color: #cccccc;"); 
  str.append("    padding:1px 6px 1px 6px; /*À§, ¿À¸¥ÂÊ, ¾Æ·¡, ¿ŞÂÊ*/"); 
  str.append("    margin:1px 2px 1px 2px; /*À§, ¿À¸¥ÂÊ, ¾Æ·¡, ¿ŞÂÊ*/"); 
  str.append("  }"); 
  str.append("  .span_box_2{"); 
  str.append("    text-align: center;");    
  str.append("    background-color: #668db4;"); 
  str.append("    color: #FFFFFF;"); 
  str.append("    font-size: 1em;"); 
  str.append("    border: 1px;"); 
  str.append("    border-style: solid;"); 
  str.append("    border-color: #cccccc;"); 
  str.append("    padding:1px 6px 1px 6px; /*À§, ¿À¸¥ÂÊ, ¾Æ·¡, ¿ŞÂÊ*/"); 
  str.append("    margin:1px 2px 1px 2px; /*À§, ¿À¸¥ÂÊ, ¾Æ·¡, ¿ŞÂÊ*/"); 
  str.append("  }"); 
  str.append("</style>"); 
  str.append("<DIV id='paging'>"); 
//    str.append("ÇöÀç ÆäÀÌÁö: " + nowPage + " / " + totalPage + "  "); 

  int _nowPage = (nowGrp-1) * pagePerBlock; // 10°³ ÀÌÀü ÆäÀÌÁö·Î ÀÌµ¿ 
  if (nowGrp >= 2){ 
    str.append("<span class='span_box_1'><A href='./"+url+"?nowPage="+nowPage+"&col="+col+"&word="+word+"&"+noname+"="+bbsno+"&nPage="+_nowPage+"'>ÀÌÀü</A></span>"); 
  } 

  for(int i=startPage; i<=endPage; i++){ 
    if (i > totalPage){ 
      break; 
    } 

    if (nPage == i){ 
      str.append("<span class='span_box_2'>"+i+"</span>"); 
    }else{ 
      str.append("<span class='span_box_1'><A href='./"+url+"?nowPage="+nowPage+"&col="+col+"&word="+word+"&"+noname+"="+bbsno+"&nPage="+i+"'>"+i+"</A></span>");   
    } 
  } 
   
  _nowPage = (nowGrp * pagePerBlock)+1; // 10°³ ´ÙÀ½ ÆäÀÌÁö·Î ÀÌµ¿ 
  if (nowGrp < totalGrp){ 
    str.append("<span class='span_box_1'><A href='./"+url+"?nowPage="+nowPage+"&col="+col+"&word="+word+"&"+noname+"="+bbsno+"&nPage="+_nowPage+"'>´ÙÀ½</A></span>"); 
  } 
  str.append("</DIV>"); 
   
  return str.toString(); 
} 

}