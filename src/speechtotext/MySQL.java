package speechtotext;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQL {
	String driver;// JDBC�h���C�o�̓o�^
    String server, dbname, url, user, password;// �f�[�^�x�[�X�̎w��
    Connection con;
    Statement stmt;
    Map<String, Object> lng = new HashMap<>();
    private String id;
    
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
		this.server = "sangi2018.sist.ac.jp";
		this.dbname = "sangi2018";
		this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
        this.user = "sangi2018";
        this.password = "sistsangi2018";
        this.id = "1618042";

        try {
            this.con = DriverManager.getConnection(url, user, password);
            this.stmt = con.createStatement ();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Class.forName (driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
	}
		
	//�l�̖������S��
	public ResultSet getID() {
		ResultSet rs = null;
		String sql = "SELECT * FROM  `images` WHERE  `age_min` = -1";
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	

	
	public void updateImage(String transcript, double confidence) {
		StringBuffer buf = new StringBuffer();
		buf.append("INSERT INTO  `speeches` (`user_id`, `transcript` ,`confidence`) VALUES ("+ id+ ","+ transcript +","+ confidence+");");
		String sql = buf.toString();
		try {
			stmt.execute (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}