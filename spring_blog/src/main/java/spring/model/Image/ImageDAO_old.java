package spring.model.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;

@Repository
public class ImageDAO_old {
	
	public void upViewcnt(int no){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update image ");
		sql.append(" set viewcnt = viewcnt + 1 ");
		sql.append(" where no = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con, pstmt);
			
		}
		
		
		
	}
	
	public boolean checkRefno(int no){
		boolean flag = false;
		Connection con =DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(refno) from image ");
		sql.append(" where refno = ? ");
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				int cnt = rs.getInt(1);
				if(cnt>0){
					flag = true;//ë¶?ëª¨ê??´?‹¤.
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		
		
		return flag;
	}
	public void upAno(Map map){
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		int gno = (Integer)map.get("gno");
		int ano = (Integer)map.get("ano");
		sql.append(" update image ");
		sql.append(" set ano = ano + 1 ");
		sql.append(" where gno = ? and ano > ?");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, gno);
			pstmt.setInt(2, ano);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
	}
	
	public boolean createReply(ImageDTO dto){
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" INSERT INTO image(no, name, title, content, passwd, wdate, gno, ino, ano,refno,  filename) ");
		sql.append(" VALUES((SELECT NVL(MAX(no), 0) + 1 FROM image), ");
		sql.append(" ?, ?, ?, ?,  sysdate, ");
		sql.append(" ?, ?, ?, ? , ? )  ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGno()); //ë¶?ëª¨ì˜ grpno
			pstmt.setInt(6, dto.getIno()+1); //ë¶?ëª¨ì˜ indent + 1
			pstmt.setInt(7, dto.getAno()+1); //ë¶?ëª¨ì˜ ansnum + 1
			pstmt.setInt(8, dto.getNo());
			pstmt.setString(9, dto.getFilename()); 
		
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) flag=true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt);
		}
		return flag;
	}
	
	public ImageDTO readReply(int no){
		ImageDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT no, title, gno, ino, ano ");
		sql.append(" FROM image ");
		sql.append(" WHERE no = ? ");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto = new ImageDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setGno(rs.getInt("gno"));
				dto.setIno(rs.getInt("ino"));
				dto.setAno(rs.getInt("ano"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	public int total(String col, String word){
		int total = 0;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) ");
		sql.append(" from image ");
		if(word.trim().length()>0)
			sql.append(" where "+col+" like '%'||?||'%' ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			if(word.trim().length()>0)
				pstmt.setString(1, word);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		
		return total;
	}
	
	public List<ImageDTO> list(Map map){
		List<ImageDTO> list = new ArrayList<ImageDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String col = (String)map.get("col");
		String word = (String)map.get("word");
		int sno = (Integer)map.get("sno");
		int eno = (Integer)map.get("eno");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select no, name, title, content, viewcnt, wdate, filename, ino,gno, r ");
		sql.append(" from( ");
		sql.append(" select no, name, title, content, viewcnt, wdate, filename, ino ,gno, rownum r ");
		sql.append(" from( ");
		sql.append(" 		select no, name, title, content, viewcnt, to_char(wdate, 'yyyy-mm-dd') wdate, filename, ino ,gno ");
		sql.append(" 		from image ");
		
		if(word.trim().length()>0)			
			sql.append(" 	where "+col+" like '%'||?||'%' ");
		
		sql.append(" order by gno desc, ano ASC ");
		sql.append(" ) ");
		sql.append(" )where r>=? and r<=? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			int i = 0;
			if(word.trim().length()>0)
				pstmt.setString(++i, word);
			
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ImageDTO dto = new ImageDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setFilename(rs.getString("filename"));
				dto.setIno(rs.getInt("ino"));
				dto.setGno(rs.getInt("gno"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBClose.close(con, pstmt, rs);
		}
		return list;
	}
	
	public boolean passwd(Map map){
		boolean flag=false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt= null;
		StringBuffer sql= new StringBuffer();
		int no = (Integer) map.get("no");
		String passwd = (String) map.get("passwd");
		ResultSet rs= null;
		sql.append(" select count(no) from image ");
		sql.append(" where no=? and passwd=? ");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1,no);
			pstmt.setString(2,passwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				int cnt = rs.getInt(1);
				if(cnt>0){
					flag=true;
				}
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return flag;
		
	}
	public boolean delete(int no){
		boolean flag=false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt= null;
		StringBuffer sql= new StringBuffer();
		sql.append(" delete from image ");
		sql.append(" where no=? ");
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1,no);
			int cnt=pstmt.executeUpdate();
			if(cnt>0){
				flag=true;
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
		
		return flag;
	}
	
	public boolean update(ImageDTO dto){
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update image set  ");
		sql.append(" name = ?,  ");
		sql.append(" title = ?,  ");
		sql.append(" content = ?,  ");
		sql.append(" passwd = ?,  ");
		sql.append(" filename = ?  ");
		sql.append(" where no = ?  ");		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setString(5, dto.getFilename());
			pstmt.setInt(6, dto.getNo());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con, pstmt);
		}
		
		
		return flag;
		
	}

	
	
	public boolean updateFile(ImageDTO dto){
		boolean flag = false;
		
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update image set  ");
		
		sql.append(" filename = ?  ");
		sql.append(" where no = ?  ");		
		
		try {
			pstmt = con.prepareStatement(sql.toString());
		
			pstmt.setString(1, dto.getFilename());
			pstmt.setInt(2, dto.getNo());
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBClose.close(con, pstmt);
		}
		
		
		return flag;
		
	}
	
	public List sumnail(int no) {
		List list = new ArrayList();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" SELECT * FROM  ");
		sql.append("   (  ");
		sql.append("      select    ");
		sql.append("          lag(no,2)     over (order by no) pre_no2,    ");
		sql.append("          lag(no,1)     over (order by no ) pre_no1,   ");
		sql.append("          no,  ");
		sql.append("          lead(no,1)    over (order by no) nex_no1,    ");
		sql.append("          lead(no,2)    over (order by no) nex_no2,    ");
		
		sql.append("          lag(filename,2)  over (order by no) pre_file2,     ");
		sql.append("          lag(filename,1)  over (order by no ) pre_file1,  ");
		sql.append("          filename,   ");
		sql.append("          lead(filename,1) over (order by no) nex_file1,  ");
		sql.append("          lead(filename,2) over (order by no) nex_file2   ");
		sql.append("          from (  ");
		sql.append("               SELECT no, filename   ");
		sql.append("               FROM image ");
		sql.append("               ORDER BY no DESC  ");
		sql.append("          )  ");
		sql.append("   )  ");
		sql.append("   WHERE no = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int[] noArr = { rs.getInt("pre_no2"), 
						        rs.getInt("pre_no1"), 
						        rs.getInt("no"), 
						        rs.getInt("nex_no1"),
						        rs.getInt("nex_no2") 
						};
				
				String[] files = {
								rs.getString("pre_file2"), 
								rs.getString("pre_file1"), 
								rs.getString("filename"),
								rs.getString("nex_file1"),
								rs.getString("nex_file2") 
						};
				
				list.add(files);
				list.add(noArr);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}

		return list;

	}

	public ImageDTO read(int no) {
		ImageDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" select no, name, title, content, viewcnt, to_char(wdate,'yyyy-mm-dd') wdate, filename ");
		sql.append(" from image ");
		sql.append(" where no = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new ImageDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setFilename(rs.getString("filename"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}

		return dto;

	}
	public boolean create(ImageDTO dto) {
		boolean flag = false;

		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" insert into image ( no , name , title , content ,passwd  ,wdate ,filename ,gno  ) ");
		sql.append(
				" values((select nvl(max(no),0)+1 from image) , ? , ? , ? , ? , sysdate, ?,(select nvl(max(gno),0)+1 from image) )");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setString(5, dto.getFilename());

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}

		return flag;

	}

}
