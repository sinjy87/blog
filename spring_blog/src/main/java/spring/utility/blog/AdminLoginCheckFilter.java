package spring.utility.blog;
 
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
public class AdminLoginCheckFilter implements Filter {
 
    public void init(FilterConfig arg0) throws ServletException {
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain)
    throws IOException, ServletException {
        
        // Filterê°? ?š”ì²? ì¤‘ê°„?—?„œ request ê°ì²´ë¥? ì¶”ì¶œ?•©?‹ˆ?‹¤.
        HttpServletRequest httpRequest = (HttpServletRequest)request;
 
        // ?ƒˆë¡œìš´ ?„¸?…˜?„ ?ƒ?„±?•˜ì§??•Šê³? request ê°ì²´?—?„œ ê¸°ì¡´?˜ ?„¸?…˜ ë°˜í™˜
        HttpSession session = httpRequest.getSession(false);
        
        // ë¡œê·¸?¸ ?•˜ì§? ?•Š?•˜?‹¤ê³? ê°?? •
        boolean login = false;
        
        if (session != null) { // session ê°ì²´ê°? ?ƒ?„±?˜?–´ ?ˆ?Š”ì§? ?™•?¸
            // ë¡œê·¸?¸?„ ?–ˆ?œ¼ë©´ì„œ ê´?ë¦¬ì?¸ì§? ?™•?¸?•©?‹ˆ?‹¤.
            if (session.getAttribute("id") != null 
                    && session.getAttribute("grade").equals("A")) {
                login = true;  // ê´?ë¦¬ì?¼ë©?
            }
        }
        
        // ? •?ƒ? ?œ¼ë¡? ë¡œê·¸?¸?´ ?˜?—ˆ?‹¤ë©? ?š”ì²? ?˜?´ì§?ë¡? ?´?™?•©?‹ˆ?‹¤.
        if (login) {
            chain.doFilter(request, response); //?š”ì²? ?˜?´ì§?ë¡? ?´?™
        } else {
            // ë¡œê·¸?¸?´ ?•ˆ?˜?—ˆ?‹¤ë©? ë¡œê·¸?¸ ?˜?´ì§?ë¡? ?´?™
            RequestDispatcher dispatcher = 
                request.getRequestDispatcher("/member/login.do");
            dispatcher.forward(request, response);
        }
    }
 
    public void destroy() {
    }
}