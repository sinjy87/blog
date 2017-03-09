package spring.model.calendar;
 
import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Repository
public class CalendarDAO {
  @Autowired
  private SqlSessionTemplate ibatis;
  
  public CalendarDAO(){
    System.out.println("--> CalendarDAO created.");
  }
  
  /**
   * �벑濡�
   * @param dto ���옣�븷 �뜲�씠�꽣 媛앹껜
   * @return �꽦怨듭떆 PK(calendarno 而щ읆) 媛믪쓣 Object ���엯�쑝濡� 由ы꽩
   */
  public Object create(Object dto){
    return ibatis.insert("calendar.create", dto);
  }
  
  /**
   * 紐⑸줉, ArrayList return
   * @return SELECT�쓽 �젅肄붾뱶�닔媛� 2媛쒖씠�긽�씠硫� queryForList() 硫붿냼�뱶 �씠�슜
   */
  public List list(){
    return ibatis.selectList("calendar.list");
  }
  
  /**
   * 議고쉶
   * @param calendarno
   * @return queryForObject �븳嫄댁쓽 �젅肄붾뱶 Select
   * @throws SQLException
   */
  public Object read(Object calendarno){
    return ibatis.selectOne("calendar.read", calendarno);
  }
  
  /**
   * �닔�젙
   * @param dto
   * @return obj 蹂�寃쎈맂 �젅肄붾뱶 媛��닔
   * @throws SQLException
   */
  public int update(Object dto){
    return ibatis.update("calendar.update", dto);
  }
  
  public Object delete(Object calendarno){
    return ibatis.delete("calendar.delete", calendarno);
  }
  
  /**
   * �젅�씠釉� 紐⑸줉
   * @return ArrayList媛� Object ���엯�쑝濡� ���옣�릺�뼱 由ы꽩
   * @throws SQLException
   */
  public List labelList(Object labeldate){
    return ibatis.selectList("calendar.label", labeldate);
    
  }  
}
 
 