package test;
 
public class Account {
private int account;
 
public Account(int i){
    account = i;
    System.out.println("������ ȣ��:"+account);
}
public int getBalance(){
    return account;
}
 
/** ��� **/
public void withdraw(int i) {
    account =account- i;
 
}
/** ���� **/
public void deposit(int i) {
    account=account+i;
 
}
}