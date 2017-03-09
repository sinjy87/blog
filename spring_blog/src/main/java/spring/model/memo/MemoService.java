package spring.model.memo;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service 
public class MemoService {
       @Autowired
        private MemoDAO dao;
         // private BbsDAO_Mybatis dao;
       @Autowired
        private MemoreplyDAO rdao;
 
      public void delete(int memono) throws Exception{
 
             rdao.bdelete(memono); //댓글들 삭제(자식레코드)
             dao.delete(memono); //댓글을 가진 부모글 삭제(부모레코드)
      }
}