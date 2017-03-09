package spring.model.memo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;
@Repository
public class MemoDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	public int total(String col, String word){
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		
		return sqlSession.selectOne("memo.total",map);
	}
	public void upViewcnt(int memono) {

		sqlSession.update("memo.upViewcnt", memono);
		
	}

	public boolean delete(int memono) {
		boolean flag = false;
		int cnt = sqlSession.delete("memo.delete", memono);
		if(cnt>0)flag = true;
		return flag;
	}

	public boolean update(MemoDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.update("memo.update", dto);
		if(cnt > 0)flag = true;
		return flag;
	}

	public MemoDTO read(int memono) {
		
		return sqlSession.selectOne("memo.read", memono);
	}

	public boolean create(MemoDTO dto) {
		boolean flag = false;
		
		int cnt = sqlSession.insert("memo.create", dto);
		if(cnt > 0) flag=true;
		
		return flag;
	}

	public List<MemoDTO> list(Map map) {
		
		return sqlSession.selectList("memo.list", map);
	}

}
