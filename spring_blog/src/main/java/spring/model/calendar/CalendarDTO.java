package spring.model.calendar;
 
public class CalendarDTO {
  /** 글 번호 */
  private int calendarno;
  /** 레이블 출력 날짜 */
  private String labeldate;
  /** 레이블 */
  private String label;  
  /** 제목 */
  private String title;
  /** 내용 */
  private String content;
  /** 조회수 */
  private int cnt;
  /** 글 등록일 */
  private String rdate;
  
  public int getCalendarno() {
    return calendarno;
  }
  public void setCalendarno(int calendarno) {
    this.calendarno = calendarno;
  }
  public String getLabeldate() {
    return labeldate;
  }
  public void setLabeldate(String labeldate) {
    this.labeldate = labeldate;
  }
  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public int getCnt() {
    return cnt;
  }
  public void setCnt(int cnt) {
    this.cnt = cnt;
  }
  public String getRdate() {
    return rdate;
  }
  public void setRdate(String rdate) {
    this.rdate = rdate;
  }
 
}