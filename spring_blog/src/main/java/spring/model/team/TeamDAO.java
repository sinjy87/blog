package spring.model.team;

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
public class TeamDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	public boolean updateFile(Map map) {
		boolean flag = false;
		int cnt = sqlSession.update("team.updateFile", map);
		if(cnt > 0)flag = true;
		return flag;
	}

	public int total(String col, String word) {
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		
		return sqlSession.selectOne("team.total", map);
	}

	public boolean delete(int no) {
		boolean flag = false;
		int cnt = sqlSession.delete("team.delete", no);
		if(cnt > 0)flag = true;
		return flag;
	}

	public boolean update(TeamDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.update("team.update", dto);
		if(cnt > 0)flag = true;
		return flag;
	}

	public TeamDTO read(int no) {
		
		return sqlSession.selectOne("team.read", no);
	}

	public boolean create(TeamDTO dto) {
		boolean flag = false;
		int cnt = sqlSession.insert("team.create", dto);
		if(cnt > 0)flag = true;
		return flag;
	}

	public List<TeamDTO> list(Map map) {
		

		return sqlSession.selectList("team.list", map);
	}
}
