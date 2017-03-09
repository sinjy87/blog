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
 
             rdao.bdelete(memono); //��۵� ����(�ڽķ��ڵ�)
             dao.delete(memono); //����� ���� �θ�� ����(�θ��ڵ�)
      }
}