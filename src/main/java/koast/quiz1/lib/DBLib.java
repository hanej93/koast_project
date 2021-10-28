package koast.quiz1.lib;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component("dBLib")
public class DBLib extends NamedParameterJdbcDaoSupport {
	
	@Autowired
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	public List<VrfyVO> getVrfyData(String d, String centre, String[] sc, String[] dom, String[] par, String t, String[] s) {
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		// ==== @@@@ ====
		// parameters 객체에 query 의 where 조건을 삽입하고 그에 맞는 query 생성
		// ==== @@@@ ====
		
		parameters.addValue("d", d);
		parameters.addValue("centre", centre);
		parameters.addValue("sc", sc);
		parameters.addValue("dom", dom);
		parameters.addValue("par", par);
		parameters.addValue("t", t);
		parameters.addValue("s", s);
		
		final String query = "select * from tb_vrfy where d = :d and centre = :centre and sc in (:sc) and dom in (:dom) and par in (:par) and t = :t and s in (:s)";
			
		List<VrfyVO> resultList = getNamedParameterJdbcTemplate().query(
				
				query, 
				parameters,
				new RowMapper<VrfyVO>() {		
			
			public VrfyVO mapRow(ResultSet rs, int rowNum) {
				
				try {
							
					return new VrfyVO(							
							rs.getString("d"),
							rs.getString("centre"),
							rs.getString("sc"),
							rs.getString("dom"),
							rs.getString("par"),
							rs.getInt("t"),
							rs.getInt("s"),
							rs.getDouble("v")
					);
					
				} catch (SQLException se) {
					return null;
				}
			}
		});
		
		return resultList;
	}
}
