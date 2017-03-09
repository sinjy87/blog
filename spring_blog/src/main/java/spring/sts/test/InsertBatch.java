package spring.sts.test;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
 
import spring.utility.blog.DBOpen;
import spring.utility.blog.DBClose;
 
public class InsertBatch {
 
  public static void main(String[] args) {
    Connection con = null;
    PreparedStatement pstmt = null;
    StringBuffer sql = null;
    int count = 1;
 
    try {
      con = DBOpen.open();
      sql = new StringBuffer();
//      서브쿼리는 Batch 작업이 진행이안됨
//      sql.append(" INSERT INTO category(categoryno, title, seqno, visible, ids)");
//      sql.append(" VALUES((SELECT NVL(MAX(categoryno), 0)+1 as categoryno FROM category),");
//      sql.append("   ?, ? , ?, ?)");
      sql.append(" INSERT INTO category(categoryno, title, seqno, visible, ids)");
      sql.append(" VALUES(?, ?, ?, ?, ?)");
      pstmt = con.prepareStatement(sql.toString());
      
      for (int i = 0; i < 5; i++) {
        pstmt.setInt(1, count++);
        pstmt.setString(2, "여행");
        pstmt.setInt(3, i);
        pstmt.setString(4, "Y");
        pstmt.setString(5, "admin");
        pstmt.addBatch(); // 배치에 담기
 
        pstmt.clearParameters(); // 파라미터 삭제
 
      }
      pstmt.executeBatch(); // 실행
      pstmt.clearBatch(); // Batch 초기화
      con.commit(); // DBMS에 적용
 
    } catch (Exception e) {
      e.printStackTrace();
 
      try {
        con.rollback();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
 
    } finally {
     DBClose.close(con, pstmt);
    }
  }
}