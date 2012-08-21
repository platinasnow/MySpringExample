package phase.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import sun.security.krb5.internal.crypto.crc32;

public class CrcJdbcDaoImpl extends JdbcDaoImpl{

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		String crcCode = String.valueOf(new crc32().byte2crc32(username.getBytes()));
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[]{crcCode,username},new RowMapper<UserDetails>(){
			public UserDetails mapRow(ResultSet rs,int rowNum)throws SQLException{
				String username = rs.getString(1);
				String password = rs.getString(2);
				boolean enabled = rs.getBoolean(3);
				return new User(username,password,enabled,true,true,true,AuthorityUtils.NO_AUTHORITIES);
			}
		});
	}
	
	
}


