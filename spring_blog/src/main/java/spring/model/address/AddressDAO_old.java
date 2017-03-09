package spring.model.address;

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
public class AddressDAO_old {
	public int total(String col, String word){
		int total = 1;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(*) from address ");
		
		if(word.trim().length()>0){
		sql.append(" where "+col+" like '%'||?||'%' ");
		}
		
		try {
			pstmt = con.prepareStatement(sql.toString());
			
			if(word.trim().length()>0){
				pstmt.setString(1, word);
			}
			
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
	public boolean delete(int no) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" delete from address ");
		sql.append(" where no = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}

		return flag;
	}

	public boolean update(AddressDTO dto) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" update address ");
		sql.append(" set phone = ?, ");
		sql.append(" zipcode = ?, ");
		sql.append(" address1 = ?, ");
		sql.append(" address2 = ? ");
		sql.append(" where no = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());

			pstmt.setString(1, dto.getPhone());
			pstmt.setString(2, dto.getZipcode());
			pstmt.setString(3, dto.getAddress1());
			pstmt.setString(4, dto.getAddress2());
			pstmt.setInt(5, dto.getNo());

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}

		return flag;
	}

	public AddressDTO read(int no) {
		AddressDTO dto = null;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from address ");
		sql.append(" where no = ? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new AddressDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddress1(rs.getString("address1"));
				dto.setAddress2(rs.getString("address2"));
				dto.setWdate(rs.getString("wdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}

		return dto;

	}

	public boolean create(AddressDTO dto) {
		boolean flag = false;
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();

		sql.append(" insert into address (no,name,phone,zipcode,address1,address2,wdate) ");
		sql.append(" values((select nvl(max(no),0) +1 from address), ");
		sql.append(" ?,?,?,?,?,sysdate) ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setString(3, dto.getZipcode());
			pstmt.setString(4, dto.getAddress1());
			pstmt.setString(5, dto.getAddress2());

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				flag = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt);
		}

		return flag;
	}

	public List<AddressDTO> list(Map map) {
		List<AddressDTO> list = new ArrayList<AddressDTO>();
		Connection con = DBOpen.open();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String col = (String) map.get("col");
		String word = (String) map.get("word");
		int sno = (Integer) map.get("sno");
		int eno = (Integer) map.get("eno");

		StringBuffer sql = new StringBuffer();

		sql.append(" select no,name,phone,to_char(wdate,'yyyy-dd-mm') as wdate ,r ");
		sql.append(" 	from( ");
		sql.append(" 	select no,name,phone,wdate ,rownum r ");
		sql.append(" 		from( ");
		sql.append(" 			select no,name,phone,wdate from address ");
		if (word.trim().length() > 0) {
			sql.append(" where "+col+" like '%'||?||'%' ");
		}
		sql.append(" 			order by no desc ");
		sql.append(" 	) ");
		sql.append(" )where r>=? and r<=? ");

		try {
			pstmt = con.prepareStatement(sql.toString());
			int i = 0;
			if (word.trim().length() > 0) {
				pstmt.setString(++i, word);
			}
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				AddressDTO dto = new AddressDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setWdate(rs.getString("wdate"));

				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(con, pstmt, rs);
		}

		return list;

	}

}
