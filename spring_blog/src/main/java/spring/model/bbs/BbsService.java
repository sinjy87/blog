package spring.model.bbs;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service 
public class BbsService {
       @Autowired
        private BbsDAO dao;
         // private BbsDAO_Mybatis dao;
       @Autowired
        private ReplyDAO rdao;
 
      public void delete(int bbsno) throws Exception{
 
             rdao.bdelete(bbsno); //��۵� ����(�ڽķ��ڵ�)
             dao.delete(bbsno); //����� ���� �θ�� ����(�θ��ڵ�)
      }
}