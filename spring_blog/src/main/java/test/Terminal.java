package test;
 
public class Terminal {
  private String id;
  private String pw;
  private String msg;
 
  public void netConnect(){
 System.out.println("�͹̳� ����");
  }
  public void netDisConnect(){
 System.out.println("�͹̳� ���� ����");
  }
  public void logon(String id, String pw){
 this.id = id;
 this.pw = pw;
 System.out.println("�α���");
 
  }
  
  public void logoff(){
 System.out.println("�α׿���");
  }
  
  public boolean isLogon(){
 if(id!=null && pw!=null){
 return true;
 }else{
 return false;
 }
  }
public void sendMessage(String msg) {
this.msg = msg;
 
}
public Object getReturnMsg() {
return msg;
}
  
 
}