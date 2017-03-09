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
//      ���������� Batch �۾��� �����̾ȵ�
//      sql.append(" INSERT INTO category(categoryno, title, seqno, visible, ids)");
//      sql.append(" VALUES((SELECT NVL(MAX(categoryno), 0)+1 as categoryno FROM category),");
//      sql.append("   ?, ? , ?, ?)");
      sql.append(" INSERT INTO category(categoryno, title, seqno, visible, ids)");
      sql.append(" VALUES(?, ?, ?, ?, ?)");
      pstmt = con.prepareStatement(sql.toString());
      
      for (int i = 0; i < 5; i++) {
        pstmt.setInt(1, count++);
        pstmt.setString(2, "����");
        pstmt.setInt(3, i);
        pstmt.setString(4, "Y");
        pstmt.setString(5, "admin");
        pstmt.addBatch(); // ��ġ�� ���
 
        pstmt.clearParameters(); // �Ķ���� ����
 
      }
      pstmt.executeBatch(); // ����
      pstmt.clearBatch(); // Batch �ʱ�ȭ
      con.commit(); // DBMS�� ����
 
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