package spring.model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public String getGrade(String id) {
		
		return sqlSession.selectOne("member.getGrade", id);
	}

	public boolean loginCheck(String id, String passwd) {
		boolean flag = false;
		Map map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		int cnt = sqlSession.selectOne("member.loginCheck", map);
		if(cnt>0)flag = true;

		return flag;
	}

	public boolean delete(String id) {
		boolean flag = false;
		int cnt = sqlSession.delete("member.delete", id);
		if(cnt>0)flag = true;
		return flag;
	}

	public String getFname(String id) {

		return sqlSession.selectOne("member.gerFname", id);
	}

	public boolean update(MemberDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.update("member.update", dto);
		if(cnt>0)flag = true;
		return flag;
	}

	public boolean updatePw(String id, String passwd) {
		boolean flag = false;
		Map map = new HashMap();
		map.put("id", id);
		map.put("passwd", passwd);
		int cnt = sqlSession.update("member.updatePw", map);
		if(cnt>0)flag = true;
		return flag;
	}

	public boolean updateFile(String id, String fname) {
		boolean flag = false;
		Map map = new HashMap();
		map.put("id", id);
		map.put("fname", fname);
		int cnt = sqlSession.update("member.updateFile", map);
		if(cnt>0)flag = true;
		return flag;
	}

	public MemberDTO read(String id) {
		
		return sqlSession.selectOne("member.read", id);
	}

	public List<MemberDTO> list(Map map) {
		
		return sqlSession.selectList("member.list", map);
	}

	public int total(String col, String word) {
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		return sqlSession.selectOne("member.total", map);
	}

	public boolean create(MemberDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.insert("member.create", dto);
		if(cnt>0)flag = true;

		return flag;
	}

	public boolean duplicateEmail(String email) {
		boolean flag = false;
		int cnt = sqlSession.selectOne("member.duplicateEmail", email);
		if(cnt>0)flag = true;

		return flag;
	}

	public boolean duplicateId(String id) {
		boolean flag = false;
		int cnt = sqlSession.selectOne("member.duplicateId", id);
		if(cnt>0)flag = true;

		return flag;
	}

}
